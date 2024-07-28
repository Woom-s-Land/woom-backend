package com.ee06.wooms.domain.wooms.service;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import com.ee06.wooms.domain.enrollments.entity.EnrollmentStatus;
import com.ee06.wooms.domain.enrollments.repository.EnrollmentRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.domain.wooms.dto.WoomCreateRequestDto;
import com.ee06.wooms.domain.wooms.dto.WoomDto;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.domain.wooms.repository.WoomRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WoomService {
    private final UserRepository userRepository;

    private final WoomRepository woomRepository;

    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public WoomDto createWoomGroup(CustomUserDetails currentUser, WoomCreateRequestDto woomCreateRequestDto) {
        User user = fetchUser(currentUser.getUuid());
        Wooms savedWoom = createAndSaveWoom(user, woomCreateRequestDto);
        createAndSaveEnrollment(user, savedWoom);

        return WoomDto.builder()
                .woomInviteCode(UUID.randomUUID().toString())
                .woomId(savedWoom.getUuid())
                .woomTitle(savedWoom.getTitle())
                .build();
    }

    private Wooms createAndSaveWoom(User user, WoomCreateRequestDto request) {
        Wooms newWoom = Wooms.builder()
                .user(user)
                .title(request.getWoomTitle())
                .build();
        return woomRepository.save(newWoom);
    }

    private void createAndSaveEnrollment(User user, Wooms woom) {
        Enrollment newEnrollment = Enrollment.builder()
                .pk(new Enrollment.Pk(user.getUuid(), woom.getUuid()))
                .user(user)
                .wooms(woom)
                .status(EnrollmentStatus.ACCEPT)
                .build();
        enrollmentRepository.save(newEnrollment);
    }

    private User fetchUser(String userUuidStr) throws UserNotFoundException {
        UUID userUuid = UUID.fromString(userUuidStr);
        return userRepository.findById(userUuid)
                .orElseThrow(UserNotFoundException::new);
    }
}