package com.ee06.wooms.domain.enrollments.repository;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import com.ee06.wooms.domain.enrollments.entity.EnrollmentStatus;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Enrollment.Pk> {
    List<Enrollment> findByUserUuid(UUID userUuid);

    Optional<Enrollment> findByUserAndWooms(User user, Wooms wooms);

    Optional<Enrollment> findByPkUserUuidAndWoomsId(UUID userUuid, Long woomsId);

    List<Enrollment> findByPkWoomsIdAndStatus(Long woomsId, EnrollmentStatus status);

    Page<Enrollment> findByPkWoomsIdAndStatus(Long woomsId, EnrollmentStatus status, Pageable pageable);

    boolean existsByPkUserUuidAndPkWoomsId(UUID userUuid, Long woomsId);

    @Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.wooms.id IN " +
            "(SELECT e1.wooms.id FROM Enrollment e1 WHERE e1.user.uuid = :userUuid1) AND " +
            "e.wooms.id IN (SELECT e2.wooms.id FROM Enrollment e2 WHERE e2.user.uuid = :userUuid2) AND " +
            "e.user.uuid = :userUuid1")
    boolean areUsersInSameGroup(@Param("userUuid1") UUID userUuid1, @Param("userUuid2") UUID userUuid2);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.wooms.id = :woomsId AND e.status = :status")
    Integer countByWoomsIdAndStatus(@Param("woomsId") Long woomsId, @Param("status") EnrollmentStatus status);
}
