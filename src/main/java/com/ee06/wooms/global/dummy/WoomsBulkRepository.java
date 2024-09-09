package com.ee06.wooms.global.dummy;

import com.ee06.wooms.domain.wooms.entity.Wooms;
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
public class WoomsBulkRepository {

    private final JdbcTemplate jdbcTemplate;
//    String sql = "INSERT INTO users(uuid, email, password, name, status, costume, nickname) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Transactional
    public void saveAll(List<Wooms> woomsList) {
        String sql = "INSERT INTO wooms(wooms_id, invite_code, user_uuid, created_date, modified_date) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Wooms wooms = woomsList.get(i);
                        ps.setLong(1, wooms.getId());
                        ps.setBytes(2, uuidToBytes(wooms.getInviteCode()));
                        ps.setBytes(3, uuidToBytes(wooms.getUser().getUuid()));
                        long timestamp = System.currentTimeMillis();
                        ps.setTimestamp(4, new Timestamp(timestamp));
                        ps.setTimestamp(5, new Timestamp(timestamp));
                    }

                    @Override
                    public int getBatchSize() {
                        return woomsList.size();
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