package com.ee06.wooms.global.dummy;

import com.ee06.wooms.domain.enrollments.entity.Enrollment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EnrollmentBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<Enrollment> enrollmentList) {
        String sql = "INSERT INTO wooms_enrollments(user_uuid, wooms_id, status, created_date, modified_date) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Enrollment enrollment = enrollmentList.get(i);
                        ps.setBytes(1,uuidToBytes(enrollment.getUser().getUuid()));
                        ps.setLong(2, enrollment.getWooms().getId());
                        ps.setInt(3, 1);
                        long timestamp = System.currentTimeMillis();
                        ps.setTimestamp(4, new Timestamp(timestamp));
                        ps.setTimestamp(5, new Timestamp(timestamp));
                    }

                    @Override
                    public int getBatchSize() {
                        return enrollmentList.size();
                    }
                });
    }
    private byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

}