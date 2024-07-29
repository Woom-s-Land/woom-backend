package com.ee06.wooms.domain.wooms.repository;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WoomsRepository  extends JpaRepository<Wooms, UUID> {
    List<Wooms> findByUserUuid(UUID userUuid);
}
