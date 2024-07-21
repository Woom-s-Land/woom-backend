package com.ee06.wooms.domain.users.repository;

import com.ee06.wooms.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserEmail(String userEmail);
}
