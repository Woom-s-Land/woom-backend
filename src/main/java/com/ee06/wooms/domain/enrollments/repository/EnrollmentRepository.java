package com.ee06.wooms.domain.enrollments.repository;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import com.ee06.wooms.domain.enrollments.entity.EnrollmentStatus;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Enrollment.Pk> {
    List<Enrollment> findByUserUuid(UUID userUuid);

    Optional<Enrollment> findByUserAndWooms(User user, Wooms wooms);

    Optional<Enrollment> findByPkUserUuidAndWoomsId(UUID userUuid, Long woomsId);

    List<Enrollment> findByPkWoomIdAndStatus(Long woomId, EnrollmentStatus status);
}
