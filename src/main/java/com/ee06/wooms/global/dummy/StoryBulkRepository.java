package com.ee06.wooms.global.dummy;


import com.ee06.wooms.domain.stories.entity.Story;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StoryBulkRepository {
    private final JdbcTemplate jdbcTemplate;

    // 현재 날짜 및 시간 구하기
    LocalDateTime now = LocalDateTime.now();

    // 8개월 전 날짜 및 시간 구하기
    LocalDateTime eightMonthsAgo = now.minusMonths(8);

    // 랜덤한 날짜 및 시간 생성

    @Transactional
    public void saveAll(List<Story> storyList) {
        String sql =
                "INSERT INTO wooms_stories(wooms_story_id, user_uuid, wooms_id, wooms_story_content," +
                        " wooms_story_file_name, created_date, modified_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Story story = storyList.get(i);
                        ps.setLong(1, story.getId());
                        ps.setBytes(2, uuidToBytes(story.getUser().getUuid()));
                        ps.setLong(3, story.getWooms().getId());
                        ps.setString(4, story.getContent());
                        ps.setString(5, story.getFileName());

                        LocalDateTime randomDateTime = getRandomDateTimeBetween(eightMonthsAgo, now);
                        ZoneOffset offset = ZoneOffset.of("+09:00");
                        ps.setTimestamp(6, new Timestamp(randomDateTime.toEpochSecond(offset)));
                        ps.setTimestamp(7, new Timestamp(randomDateTime.toEpochSecond(offset)));
                    }

                    @Override
                    public int getBatchSize() {
                        return storyList.size();
                    }
                });
    }
    private byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }
    public static LocalDateTime getRandomDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // 특정 시간대 설정 (예: 한국 시간대)
        ZoneOffset zoneOffset = ZoneOffset.of("+09:00");

        // 시작과 끝 시간을 epoch 초로 변환
        long startSeconds = startDateTime.toEpochSecond(zoneOffset);
        long endSeconds = endDateTime.toEpochSecond(zoneOffset);

        // 랜덤 초 생성
        long randomSeconds = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds);

        // 랜덤 초를 LocalDateTime으로 변환
        return LocalDateTime.ofEpochSecond(randomSeconds, 0, zoneOffset);
    }
}