package com.ee06.wooms.domain.wooms.repository;

import com.ee06.wooms.domain.wooms.entity.Wooms;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WoomsRepository  extends JpaRepository<Wooms, Long> {
    List<Wooms> findByUserUuid(UUID userUuid);
    Page<Wooms> findByUserUuid(UUID userUuid, Pageable pageable);
    Optional<Wooms> findByInviteCode(UUID inviteCode);
    Optional<Wooms> findWoomsById(Long woomsId);
    boolean existsByUserUuidAndId(UUID userUuid, Long id);
    boolean existsUserUuidByUserUuidAndId(UUID userUuid, Long id);
}