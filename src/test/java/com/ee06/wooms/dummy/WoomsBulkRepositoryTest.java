package com.ee06.wooms.dummy;

import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.global.dummy.WoomsBulkRepository;
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
class WoomsBulkRepositoryTest {

    private static final int COUNT = 200_035;
    @Autowired
    private WoomsBulkRepository woomsBulkRepository;



    @Test
    @DisplayName("bulk insert")
    void 벌크_insert() {
        long startTime = System.currentTimeMillis();
        List<Wooms> woomsList = new ArrayList<>();
        int counter = 0;

        String[] uuidArray = {
                "000024B9-7B53-4CD2-83FF-35EE574B0C92",
                "00002AA7-2584-4A11-9F55-A9312416A048",
                "00002AB8-C020-490E-8B1C-AA655F58E149",
                "00002B83-E63C-4ED6-9697-DBCC39A700C1",
                "00004B4C-4C7A-49A1-A43D-A6F68BE5AF44",
                "00005003-610E-45D5-B763-E444A1005C1A",
                "00005251-81E2-46F0-A607-E189C6FB1A8E",
                "00007D30-085D-498B-9EC7-0CA494983F3E",
                "00008A22-062A-4533-BEB6-ED89349353A7",
                "0000C78C-A433-4552-B08D-5E8F0C4EBB51",
                "0000F42C-6DD9-4F60-86F5-8B018E95ED62",
                "000103AD-7BD0-4D75-BCE8-94DDAA629DC9",
                "000105EF-991E-4AD3-9CFD-9FAD4C681ADB"
        };

        for (long i = 35; i < COUNT; i++) {
            long price = i + 1L;

            Wooms wooms = Wooms.builder()
                    .id(i)
                    .title("wooms_test_" + i)
                    .inviteCode(UUID.randomUUID())
                    .user(User.builder().uuid(UUID.fromString(uuidArray[counter % 13])).build())
                    .build();

            woomsList.add(wooms);
        }
        woomsBulkRepository.saveAll(woomsList);
        long endTime = System.currentTimeMillis();
        System.out.println("---------------------------------");
        System.out.printf("수행시간: %d\n", endTime - startTime);
        System.out.println("---------------------------------");
    }
}