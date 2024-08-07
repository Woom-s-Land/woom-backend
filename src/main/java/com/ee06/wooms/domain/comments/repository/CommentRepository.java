package com.ee06.wooms.domain.comments.repository;

import com.ee06.wooms.domain.comments.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByWoomsId(Long woomsId, Pageable pageable);

    @Query("SELECT COUNT(c) > 0 FROM Comment c WHERE c.createdDate >= :today AND c.wooms.id = :woomsId AND c.user.uuid = :userUuid")
    boolean isWriteByToday(@Param("today") LocalDateTime today, @Param("woomsId")Long woomsId, @Param("userUuid")UUID userUuid);
}
