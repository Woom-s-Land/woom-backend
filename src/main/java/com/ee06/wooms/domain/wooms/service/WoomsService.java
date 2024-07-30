package com.ee06.wooms.domain.wooms.service;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import com.ee06.wooms.domain.enrollments.entity.EnrollmentStatus;
import com.ee06.wooms.domain.enrollments.repository.EnrollmentRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.dto.UserInfoDto;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.domain.wooms.dto.*;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.domain.wooms.exception.ex.*;
import com.ee06.wooms.domain.wooms.repository.WoomsRepository;
import com.ee06.wooms.global.common.CommonResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class WoomsService {

    private final UserRepository userRepository;
    private final WoomsRepository woomsRepository;
    private final EnrollmentRepository enrollmentRepository;

    public WoomsDto createWooms(CustomUserDetails currentUser, WoomsCreateRequestDto woomsCreateRequestDto) {

        User user = fetchUser(currentUser.getUuid());

        Wooms savedWooms = createAndSaveWooms(user, woomsCreateRequestDto);

        Enrollment newEnrollment = Enrollment.of(user, savedWooms, EnrollmentStatus.ACCEPT);
        enrollmentRepository.save(newEnrollment);

        return savedWooms.toDto();

    }

    public List<WoomsDto> findAllWooms(UUID userUuid) {
        List<Wooms> wooms = woomsRepository.findByUserUuid(userUuid);
        return wooms.stream()
                .map(Wooms::toDto)
                .collect(Collectors.toList());
    }

    public CommonResponse createWoomsParticipationRequest(CustomUserDetails currentUser, String woomsInviteCode) {

        User user = fetchUser(currentUser.getUuid());

        Wooms targetWooms = findWoomsByInviteCode(woomsInviteCode);

        enrollmentRepository.findByUserAndWooms(user, targetWooms)
                .ifPresent(WoomsService::checkEnrollmentStatus);

        Enrollment newEnrollment = Enrollment.of(user, targetWooms, EnrollmentStatus.WAITING);
        enrollmentRepository.save(newEnrollment);

        return new CommonResponse("ok");
    }

    public WoomsDetailInfoDto findWoomsDetail(CustomUserDetails currentUser, Long woomsId) {
        UUID currentUserUuid = UUID.fromString(currentUser.getUuid());

        Enrollment enrollment = enrollmentRepository.findByPkUserUuidAndWoomsId(currentUserUuid, woomsId)
                .orElseThrow(WoomsUserNotEnrolledException::new);

        if (!EnrollmentStatus.ACCEPT.equals(enrollment.getStatus())) {
            throw new WoomsUserNotEnrolledException();
        }

        List<UserInfoDto> userInfoDtos = enrollmentRepository.findByPkWoomIdAndStatus(woomsId, EnrollmentStatus.ACCEPT).stream()
                .map(Enrollment::getUser)
                .map(User::toDto)
                .collect(Collectors.toList());

        Wooms wooms = woomsRepository.findById(woomsId)
                .orElseThrow(WoomsNotValidInviteCodeException::new);

        WoomsDto woomsDto = wooms.toDto();
        return new WoomsDetailInfoDto(woomsDto, userInfoDtos);
    }


    public List<UserInfoDto> getEnrolledUsers(CustomUserDetails currentUser, Long woomsId) {
        UUID currentUserUuid = UUID.fromString(currentUser.getUuid());
        if (!woomsRepository.existsByUserUuidAndId(currentUserUuid, woomsId)) {
            throw new WoomsUserNotLeaderException();
        }
        List<Enrollment> enrollments = enrollmentRepository.findByPkWoomIdAndStatus(woomsId, EnrollmentStatus.WAITING);

        return enrollments.stream()
                .map(Enrollment::getUser)
                .distinct()
                .map(this::createUserDto)
                .collect(Collectors.toList());
    }

    public CommonResponse patchEnrolledUsers(CustomUserDetails currentUser, Long woomsId, String userUuid, WoomsEnrollRequest updateRequest) {
        UUID currentUserUuid = UUID.fromString(currentUser.getUuid());
        UUID targetUserUuid = UUID.fromString(userUuid);

        if (!woomsRepository.existsByUserUuidAndId(currentUserUuid, woomsId)) {
            throw new WoomsUserNotLeaderException();
        }

        Enrollment enrollment = enrollmentRepository.findByPkUserUuidAndWoomsId(targetUserUuid, woomsId)
                .orElseThrow(WoomsUserNotEnrolledException::new);

        EnrollmentStatus newStatus = validateAndUpdateStatus(enrollment, updateRequest.getStatus());
        enrollment.modifyEnrollmentStatus(newStatus);

        enrollmentRepository.save(enrollment);

        return new CommonResponse("ok");
    }

    public CommonResponse patchWoomsAdmin(CustomUserDetails currentUser, Long woomsId, WoomsMandateAdminRequest mandateRequest) {
        User user = fetchUser(currentUser.getUuid());
        User targetUser = fetchUser(mandateRequest.getUserId());

        if (!woomsRepository.existsByUserUuidAndId(user.getUuid(), woomsId)) {
            throw new WoomsUserNotLeaderException();
        }

        if (!enrollmentRepository.existsByPkUserUuidAndPkWoomId(targetUser.getUuid(), woomsId)) {
            throw new WoomsNotValidEnrollmentException();
        }

        Wooms wooms = woomsRepository.findWoomsById(woomsId)
                .orElseThrow(WoomsNotValidException::new);

        wooms.modifyUser(targetUser);
        woomsRepository.save(wooms);
        return new CommonResponse("ok");
    }

    private EnrollmentStatus validateAndUpdateStatus(Enrollment enrollment, EnrollmentStatus newStatus) {
        if (enrollment.getStatus() == newStatus) {
            throw new WoomsNotValidEnrollmentException();
        }
        if (enrollment.getStatus() != EnrollmentStatus.WAITING) {
            throw new WoomsNotValidEnrollmentException();
        }
        return newStatus;
    }


    private static void checkEnrollmentStatus(Enrollment enrollment) {
        if (enrollment.getStatus() == EnrollmentStatus.WAITING) {
            throw new WoomsAlreadyWaitingException();
        }
        if (enrollment.getStatus() == EnrollmentStatus.ACCEPT) {
            throw new WoomsAlreadyMemberException();
        }
    }

    private User fetchUser(String userUuidStr) {
        UUID userUuid = UUID.fromString(userUuidStr);
        return userRepository.findById(userUuid)
                .orElseThrow(UserNotFoundException::new);
    }

    private Wooms findWoomsByInviteCode(String woomsInviteCode) {
        UUID inviteCode = UUID.fromString(woomsInviteCode);
        return woomsRepository.findByInviteCode(inviteCode)
                .orElseThrow(WoomsNotValidInviteCodeException::new);
    }

    private Wooms createAndSaveWooms(User user, WoomsCreateRequestDto request) {
        Wooms newWooms = Wooms.of(user, request);
        return woomsRepository.save(newWooms);
    }

    private UserInfoDto createUserDto(User user) {
        return UserInfoDto.builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .nickname(user.getNickname())
                .costume(user.getCostume())
                .build();
    }

}