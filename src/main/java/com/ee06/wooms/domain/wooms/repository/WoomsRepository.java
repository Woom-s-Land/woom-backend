package com.ee06.wooms.domain.wooms.repository;

import com.ee06.wooms.domain.wooms.entity.Wooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WoomsRepository  extends JpaRepository<Wooms, Long> {
    List<Wooms> findByUserUuid(UUID userUuid);
    Optional<Wooms> findByInviteCode(UUID inviteCode);
    Optional<Wooms> findWoomsById(Long woomsId);
    boolean existsByUserUuidAndId(UUID userUuid, Long id);
}