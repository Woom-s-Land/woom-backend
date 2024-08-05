package com.ee06.wooms.domain.users.repository;

import com.ee06.wooms.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query("SELECT u.costume FROM User u WHERE u.uuid = :userUuid")
    Integer findCostumeById(@Param("userUuid") UUID userUuid);
}
