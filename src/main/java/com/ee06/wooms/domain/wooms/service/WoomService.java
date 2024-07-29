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
import com.ee06.wooms.domain.wooms.repository.WoomsRepository;
import com.ee06.wooms.global.common.CommonResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WoomService {
    private final UserRepository userRepository;
    private final WoomsRepository woomsRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public CommonResponse createWoomGroup(CustomUserDetails currentUser, WoomCreateRequestDto woomCreateRequestDto) {
        User user = fetchUser(currentUser.getUuid());

        Wooms savedWoom = createAndSaveWoom(user, woomCreateRequestDto);

        Enrollment newEnrollment = Enrollment.of(user, savedWoom, EnrollmentStatus.ACCEPT);
        enrollmentRepository.save(newEnrollment);

        return new CommonResponse("ok");
    }

    private Wooms createAndSaveWoom(User user, WoomCreateRequestDto request) {
        Wooms newWoom = Wooms.of(user, request);
        return woomsRepository.save(newWoom);
    }

    private User fetchUser(String userUuidStr) {
        UUID userUuid = UUID.fromString(userUuidStr);
        return userRepository.findById(userUuid)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<WoomDto> findAllWoomsByUser(UUID userUuid) {
        List<Wooms> wooms = woomsRepository.findByUserUuid(userUuid);
        return wooms.stream()
                .map(Wooms::toDto)
                .collect(Collectors.toList());
    }

    public CommonResponse createWoomParticipationRequest(CustomUserDetails currentUser) {
        User user = fetchUser(currentUser.getUuid());
//        Wooms targetWoom = woomRepository.findByInviteCode();

//        Enrollment newEnrollment = Enrollment.of(user, targetWoom, EnrollmentStatus.ACCEPT);



        return new CommonResponse("ok");
    }
}