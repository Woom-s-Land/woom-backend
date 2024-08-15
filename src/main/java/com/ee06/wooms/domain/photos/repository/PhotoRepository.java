package com.ee06.wooms.domain.photos.repository;

import com.ee06.wooms.domain.photos.dto.MapResponse;
import com.ee06.wooms.domain.photos.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p FROM Photo p WHERE p.wooms.id = :woomsId AND p.createdDate >= :startDate AND p.createdDate < :endDate")
    Page<Photo> findAllByMonth(@Param("woomsId") Long woomsId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Query("SELECT p FROM Photo p WHERE p.createdDate = (" +
            "SELECT MAX(p2.createdDate) FROM Photo p2 " +
            "WHERE FUNCTION('YEAR', p2.createdDate) = FUNCTION('YEAR', p.createdDate) " +
            "AND FUNCTION('MONTH', p2.createdDate) = FUNCTION('MONTH', p.createdDate) " +
            "AND p2.wooms.id = :woomsId " +
            "GROUP BY FUNCTION('YEAR', p2.createdDate), FUNCTION('MONTH', p2.createdDate)) " +
            "AND p.wooms.id = :woomsId " +
            "ORDER BY FUNCTION('YEAR', p.createdDate) DESC, FUNCTION('MONTH', p.createdDate) DESC")
    Page<Photo> findLatestPhotosByMonth(@Param("woomsId") Long woomsId, Pageable pageable);

    @Query("SELECT new com.ee06.wooms.domain.photos.dto.MapResponse(p.mapId, COUNT(p)) " +
            "FROM Photo p where p.wooms.id = :woomsId GROUP BY p.mapId")
    List<MapResponse> findPhotoCounts(@Param("woomsId") Long woomsId);
}
