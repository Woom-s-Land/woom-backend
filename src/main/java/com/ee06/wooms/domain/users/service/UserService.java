package com.ee06.wooms.domain.users.service;

import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.auth.Join;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.entity.UserStatus;
import com.ee06.wooms.domain.users.exception.UserExistException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.global.common.CommonResponse;
import com.ee06.wooms.global.exception.ErrorCode;
import com.ee06.wooms.global.util.RandomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
