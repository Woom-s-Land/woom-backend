package com.ee06.wooms.domain.enrollments.repository;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Enrollment.Pk> {

}
