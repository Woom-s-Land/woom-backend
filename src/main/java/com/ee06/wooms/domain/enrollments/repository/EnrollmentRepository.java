package com.ee06.wooms.domain.enrollments.repository;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Enrollment.Pk> {
    List<Enrollment> findByUserUuid(UUID userUuid);

}
