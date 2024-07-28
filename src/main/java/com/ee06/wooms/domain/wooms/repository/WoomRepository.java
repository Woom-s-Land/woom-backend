package com.ee06.wooms.domain.wooms.repository;

import com.ee06.wooms.domain.wooms.entity.Wooms;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WoomRepository  extends JpaRepository<Wooms, UUID> {
}
