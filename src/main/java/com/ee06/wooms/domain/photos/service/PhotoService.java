package com.ee06.wooms.domain.photos.service;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import com.ee06.wooms.domain.enrollments.entity.EnrollmentStatus;
import com.ee06.wooms.domain.enrollments.repository.EnrollmentRepository;
import com.ee06.wooms.domain.photos.dto.MapResponse;
import com.ee06.wooms.domain.photos.dto.PhotoDetailsResponse;
import com.ee06.wooms.domain.photos.dto.PhotoResponse;
import com.ee06.wooms.domain.photos.entity.Photo;
import com.ee06.wooms.domain.photos.exception.ex.FailedCreatePhotoException;
import com.ee06.wooms.domain.photos.exception.ex.NotFoundPhotoException;
import com.ee06.wooms.domain.photos.exception.ex.NotMatchedUserException;
import com.ee06.wooms.domain.photos.exception.ex.NotMatchedWoomsException;
import com.ee06.wooms.domain.photos.repository.PhotoRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidEnrollmentException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidException;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsUserNotEnrolledException;
import com.ee06.wooms.domain.wooms.repository.WoomsRepository;
import com.ee06.wooms.global.aws.service.S3Service;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class PhotoService {
    private final S3Service s3Service;
    private final PhotoRepository photoRepository;
    private final WoomsRepository woomsRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private static final String DIR = "photos";

    public CommonResponse save(CustomUserDetails userDetails, Long woomsId, String summary, Integer mapId, MultipartFile file) {
        User user = fetchUser(userDetails.getUuid());

        Wooms wooms = woomsRepository.findById(woomsId).orElseThrow(WoomsNotValidException::new);

        Enrollment enrollment = getEnrollments(userDetails, woomsId);

        try {
            String fileName = String.valueOf(UUID.randomUUID());
            String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));

            s3Service.save(file.getInputStream(), DIR, fileName, extension, file.getContentType());

            photoRepository.save(Photo.of(user, wooms, s3Service.getFilePath(DIR, fileName, extension), summary, mapId));
        } catch (Exception e) {
            throw new FailedCreatePhotoException();
        }
        return new CommonResponse("OK");
    }

    @Transactional(readOnly = true)
    public List<PhotoResponse> getMonthList(CustomUserDetails userDetails, Long woomsId, Pageable pageable) {

        Enrollment enrollment = getEnrollments(userDetails, woomsId);

        return photoRepository.findLatestPhotosByMonth(woomsId, pageable)
                .stream()
                .map(photo -> PhotoResponse.builder()
                        .id(photo.getId())
                        .path(photo.getPath())
                        .date(photo.getCreatedDate().toLocalDate())
                        .flipped(photo.getFlipped())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PhotoResponse> getPhotoList(CustomUserDetails userDetails, Long woomsId, LocalDate startDate, Pageable pageable) {

        Enrollment enrollment = getEnrollments(userDetails, woomsId);
        LocalDateTime date = startDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime endDate = date.plusMonths(1);

        return photoRepository.findAllByMonth(woomsId, date, endDate, pageable)
                .stream()
                .map(photo -> PhotoResponse.builder()
                        .id(photo.getId())
                        .path(photo.getPath())
                        .date(photo.getCreatedDate().toLocalDate())
                        .flipped(photo.getFlipped())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public PhotoDetailsResponse getPhotoDetails(CustomUserDetails userDetails, Long woomsId, Long photoId) {
        Enrollment enrollment = getEnrollments(userDetails, woomsId);

        Photo photo = photoRepository.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        return PhotoDetailsResponse.builder()
                .id(photo.getId())
                .nickname(photo.getNickname())
                .summary(photo.getSummary())
                .path(photo.getPath())
                .flipped(photo.getFlipped())
                .build();
    }

    public CommonResponse photoFlip(CustomUserDetails userDetails, Long woomsId, Long photoId) {
        Enrollment enrollment = getEnrollments(userDetails, woomsId);

        Photo photo = getPhoto(userDetails, woomsId, photoId);

        photo.flip();

        return new CommonResponse("ok");
    }

    public List<MapResponse> getMap(CustomUserDetails userDetails, Long woomsId) {
       Enrollment enrollment = getEnrollments(userDetails, woomsId);

       return photoRepository.findPhotoCounts(woomsId);
    }

    private Photo getPhoto(CustomUserDetails userDetails, Long woomsId, Long photoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        // 사진 올린 유저가 맞는지
        if (Objects.equals(photo.getUser().getUuid(), userDetails.getUuid())) throw new NotMatchedUserException();

        Wooms wooms = woomsRepository.findWoomsById(woomsId).orElseThrow(WoomsNotValidException::new);

        // 해당 그룹의 사진인지 확인
        if (Objects.equals(photo.getWooms().getId(), wooms.getId())) throw new NotMatchedWoomsException();

        return photo;
    }

    private Enrollment getEnrollments(CustomUserDetails userDetails, Long woomsId) {
        UUID userUuid = UUID.fromString(userDetails.getUuid());

        // enrollment 없을 경우
        Enrollment enrollment = enrollmentRepository.findByPkUserUuidAndWoomsId(userUuid, woomsId).orElseThrow(WoomsNotValidEnrollmentException::new);

        // 상태가 ACCEPT 가 아닌 경우
        if (enrollment.getStatus() != EnrollmentStatus.ACCEPT) throw new WoomsUserNotEnrolledException();

        return enrollment;
    }

    private User fetchUser(String userUuidStr) {
        return userRepository.findById(UUID.fromString(userUuidStr)).orElseThrow(UserNotFoundException::new);
    }

}
