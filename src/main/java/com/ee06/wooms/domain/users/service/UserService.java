package com.ee06.wooms.domain.users.service;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.UserGameInfo;
import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.domain.users.dto.auth.Mail;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.entity.UserStatus;
import com.ee06.wooms.domain.users.exception.UserExistException;
import com.ee06.wooms.domain.users.exception.UserNotFoundException;
import com.ee06.wooms.domain.users.exception.UserNotSentEmailException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.exception.ErrorCode;
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

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String configEmail;

    public CommonResponse join(Join join) {
        String email = join.getEmail();
        String password = bCryptPasswordEncoder.encode(join.getPassword());
        String name = join.getName();

        boolean isExists = userRepository.existsByEmail(email);
        if (isExists) throw new UserExistException(ErrorCode.EXIST_USER);

        User user = setUser(email, password, name);
        userRepository.save(user);

        return new CommonResponse("ok");
    }

    public CommonResponse sendEmail(Mail email) {
        String code = RandomHelper.generateRandomMailAuthenticationCode();
        String title = "회원 가입 인증 이메일 입니다.";
        String content =
                "Wooms를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + code + "입니다." +
                        "<br>" +
                        "회원가입 창에 해당 인증번호를 입력해주세요 ";

        return sendEmailToRequestUser(configEmail, email.getEmail(), title, content)
                .map(sendResult -> new CommonResponse("ok"))
                .orElseThrow(() -> new UserNotSentEmailException(ErrorCode.NOT_SENT_EMAIL_USER));
    }

    public UserGameInfo userInfo(CustomUserDetails currentUser) {
        return userRepository.findById(UUID.fromString(currentUser.getUuid()))
                .map(user -> UserGameInfo.builder()
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .costume(user.getCostume())
                        .build())
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.NOT_FOUND_USER));
    }

    public CommonResponse modifyPassword(CustomUserDetails currentUser, String password) {
        return updateUser(currentUser, user -> {
            user.modifyPassword(bCryptPasswordEncoder.encode(password));
        });
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

        return new CustomUserDetails(user.get());
    }

    private static User setUser(String email, String password, String name) {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .status(UserStatus.ACTIVE)
                .costume(RandomHelper.generateCostumeNumber())
                .nickname(RandomHelper.generateNickname())
                .build();
    }

    private CommonResponse updateUser(CustomUserDetails currentUser, Consumer<User> userUpdater) {
        return userRepository.findById(UUID.fromString(currentUser.getUuid()))
                .map(user -> {
                    userUpdater.accept(user);
                    userRepository.save(user);
                    return new CommonResponse("ok");
                })
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.NOT_FOUND_USER));
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
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(1);
    }
}
