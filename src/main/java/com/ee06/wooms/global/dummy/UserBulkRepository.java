package com.ee06.wooms.global.dummy;

import com.ee06.wooms.domain.users.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserBulkRepository {

    private final JdbcTemplate jdbcTemplate;
//    String sql = "INSERT INTO users(uuid, email, password, name, status, costume, nickname) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Transactional
    public void saveAll(List<User> users) {
        String sql = "INSERT INTO users(user_uuid, user_email, user_password, user_name) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        User user = users.get(i);
                        byte[] uuidBytes = uuidToBytes(user.getUuid());
                        ps.setBytes(1, uuidBytes);
                        ps.setString(2, user.getEmail());
                        ps.setString(3, user.getPassword());
                        ps.setString(4, user.getName());
                    }

                    @Override
                    public int getBatchSize() {
                        return users.size();
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