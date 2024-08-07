package com.ee06.wooms.domain.photos.repository;

import com.ee06.wooms.domain.photos.dto.MapResponse;
import com.ee06.wooms.domain.photos.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p FROM Photo p WHERE p.createdDate >= :startDate AND p.createdDate < :endDate")
    Page<Photo> findAllByMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);

    @Query("SELECT p FROM Photo p WHERE p.id IN (" +
            "SELECT p2.id FROM Photo p2 " +
            "GROUP BY FUNCTION('YEAR', p2.createdDate), FUNCTION('MONTH', p2.createdDate) " + // 연도와 월로 그룹화
            "HAVING p2.createdDate = MAX(p2.createdDate)) " + // 각 그룹의 가장 최근 사진 선택
            "ORDER BY p.createdDate DESC")
    Page<Photo> findLatestPhotosByMonth(Pageable pageable);

    @Query("SELECT new com.ee06.wooms.domain.photos.dto.MapResponse(p.mapId, COUNT(p)) " +
            "FROM Photo p GROUP BY p.mapId")
    List<MapResponse> findPhotoCounts();
}
