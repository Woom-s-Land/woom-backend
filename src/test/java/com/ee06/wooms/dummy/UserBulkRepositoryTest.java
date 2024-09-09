package com.ee06.wooms.dummy;

import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.global.dummy.UserBulkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Rollback(value = false)
class UserBulkRepositoryTest {

    private static final int COUNT = 1000000;
    @Autowired
    private UserBulkRepository userBulkRepository;

    @Test
    @DisplayName("bulk insert")
    void 벌크_insert() {
        long startTime = System.currentTimeMillis();
        List<User> products = new ArrayList<>();
        for (long i = 0; i < COUNT; i++) {
            long price = i + 1L;
            User product = User.builder()
                    .uuid(UUID.randomUUID())
                    .name("테스트유저" + i)
                    .password("votmdnjem")
                    .email("votmdnjem@gmail.com" + i)
                    .build();
            products.add(product);
        }
        userBulkRepository.saveAll(products);
        long endTime = System.currentTimeMillis();
        System.out.println("---------------------------------");
        System.out.printf("수행시간: %d\n", endTime - startTime);
        System.out.println("---------------------------------");
    }
}