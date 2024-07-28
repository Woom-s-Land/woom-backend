package com.ee06.wooms.domain.users.service;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.UserGameInfo;
import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.entity.UserStatus;
import com.ee06.wooms.domain.users.exception.UserExistException;
import com.ee06.wooms.domain.users.exception.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.RandomHelper;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public UserGameInfo userInfo(CustomUserDetails currentUser) {
        return userRepository.findById(UUID.fromString(currentUser.getUuid()))
                .map(user -> UserGameInfo.builder()
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .costume(user.getCostume())
                        .build())
                .orElseThrow(UserNotFoundException::new);
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
                .orElseThrow(UserNotFoundException::new);
    }
}
