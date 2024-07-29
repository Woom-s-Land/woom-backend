package com.ee06.wooms.domain.users.service;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.auth.ModifyPasswordInfo;
import com.ee06.wooms.domain.users.dto.auth.UserGameInfo;
import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.domain.users.entity.Mail;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.*;
import com.ee06.wooms.domain.users.repository.MailRepository;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.util.RandomHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailRepository mailRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String configEmail;

    public CommonResponse join(Join join) {
        boolean isExists = userRepository.existsByEmail(join.getEmail());
        if (isExists) throw new UserExistException();

        join.setPassword(bCryptPasswordEncoder.encode(join.getPassword()));
        User user = User.of(join);
        userRepository.save(user);

        return new CommonResponse("ok");
    }

    public CommonResponse sendEmail(Mail email) {
        String code = RandomHelper.generateRandomMailAuthenticationCode();
        String title = "[WOOMS] 회원 가입 인증 이메일 입니다.";
        String content = RandomHelper.getEmailAuthContent(code);

        if(mailRepository.existsById(email.getEmail())) mailRepository.deleteById(email.getEmail());
        email.modifyCode(code);
        mailRepository.save(email);

        return sendEmailToRequestUser(configEmail, email.getEmail(), title, content)
                .map(sendResult -> new CommonResponse("ok"))
                .orElseThrow(UserNotSentEmailException::new);
    }

    public CommonResponse verifyEmailCode(Mail email) {
        return mailRepository.findById(email.getEmail()).map(findMail -> {
                    if(Objects.equals(email.getCode(), findMail.getCode())) return new CommonResponse("ok");
                    throw new UserEmailCodeNotMatchedException();
                })
                .orElseThrow(UserEmailExpiredException::new);
    }

    public CommonResponse reIssuePassword(Mail email) {
        String password = RandomHelper.generateRandomPassword();
        String title = "[WOOMS] 비밀번호 재발급 이메일 입니다.";
        String content = RandomHelper.getEmailReIssueContent(password);

        userRepository.findByEmail(email.getEmail()).ifPresentOrElse(
                user -> {
                    user.modifyPassword(bCryptPasswordEncoder.encode(RandomHelper.generateRandomPassword()));
                    sendEmailToRequestUser(configEmail, email.getEmail(), title, content)
                            .orElseThrow(UserNotSentEmailException::new);
                    userRepository.save(user);
                },
                () -> {throw new UserEmailNotFoundException();}
        );
        return new CommonResponse("ok");
    }

    public UserGameInfo userInfo(CustomUserDetails currentUser) {
        return userRepository.findById(UUID.fromString(currentUser.getUuid()))
                .map(user -> UserGameInfo.builder()
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .costume(user.getCostume())
                        .build())
                .orElseThrow(UserNotFoundException::new);
    }

    public CommonResponse modifyPassword(CustomUserDetails currentUser, ModifyPasswordInfo passwordInfo) {
        User user = userRepository.findById(UUID.fromString(currentUser.getUuid()))
                .orElseThrow(UserNotFoundException::new);

        if (Objects.equals(user.getPassword(), passwordInfo.getOldPassword())) {
            return updateUser(currentUser, userToUpdate -> userToUpdate.modifyPassword(bCryptPasswordEncoder.encode(passwordInfo.getNewPassword())));
        } else throw new UserPasswordNotMatchedException();
    }

    public CommonResponse modifyUserInfo(CustomUserDetails currentUser, UserGameInfo userGameInfo) {
        return updateUser(currentUser, user -> {
            user.modifyNickname(userGameInfo.getNickname());
            user.modifyCostume(userGameInfo.getCostume());
        });
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException(email));

        return new CustomUserDetails(user.get(), Map.of());
    }

    private CommonResponse updateUser(CustomUserDetails currentUser, Consumer<User> userUpdater) {
        return userRepository.findById(UUID.fromString(currentUser.getUuid()))
                .map(user -> {
                    userUpdater.accept(user);
                    userRepository.save(user);
                    return new CommonResponse("ok");
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public Optional<Integer> sendEmailToRequestUser(String configEmail, String email, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
            helper.setFrom(new InternetAddress(configEmail));
            helper.setTo(new InternetAddress(email));
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            return Optional.empty();
        }

        return Optional.of(1);
    }
}
