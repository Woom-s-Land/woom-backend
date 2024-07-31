package com.ee06.wooms.global.jwt.repository;

import com.ee06.wooms.global.jwt.dto.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    @Transactional
    void deleteByRefreshToken(String refreshToken);
}
