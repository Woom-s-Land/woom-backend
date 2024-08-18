package com.ee06.wooms.domain.stories.repository;

import com.ee06.wooms.domain.stories.entity.Story;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findAllByWoomsId(Long woomsId, Pageable pageable);

    @Query("SELECT s FROM Story s WHERE s.wooms.id = :woomsId " +
            "AND s.createdDate BETWEEN :startDate AND :endDate " +
            "AND (:keyword IS NULL OR s.content LIKE %:keyword%)")
    List<Story> findAllBySpecificDateStories(
            @Param("woomsId") Long woomsId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("keyword") String keyword,
            Pageable pageable
    );
    Integer countByWoomsId(Long woomsId);
}