package com.ee06.wooms.domain.letters.repository;

import com.ee06.wooms.domain.letters.entity.Letter;
import com.ee06.wooms.domain.letters.entity.LetterStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {

    @Query("SELECT l " +
            " FROM Letter l " +
            "WHERE l.receiver.uuid = :userUuid AND l.receiveDate < :cutoffDate " +
            "ORDER BY CASE WHEN l.status = 0 THEN 1 ELSE 2 END, l.receiveDate DESC")
    Page<Letter> findByReceiverUuidAndReceiveDateBefore(@Param("userUuid") UUID userUuid,
                                                        @Param("cutoffDate") LocalDateTime cutoffDate,
                                                        Pageable pageable);

    @Query("SELECT l FROM Letter l WHERE l.id = :id AND l.receiver.uuid = :userUuid")
    Optional<Letter> findByIdAndReceiverUuid(@Param("id") Long id, @Param("userUuid") UUID userUuid);

    int countByReceiverUuidAndReceiveDateAfter(UUID receiverUuid, LocalDateTime dateTime);

    int countByReceiverUuidAndStatus(UUID receiverUuid, LetterStatus status);

}
