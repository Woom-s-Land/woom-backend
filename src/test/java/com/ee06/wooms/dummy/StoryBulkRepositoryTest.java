package com.ee06.wooms.dummy;


import com.ee06.wooms.domain.stories.entity.Story;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.global.dummy.StoryBulkRepository;
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
class StoryBulkRepositoryTest {

    @Autowired
    private StoryBulkRepository storyBulkRepository;

    @Test
    @DisplayName("Photo_bulk insert")
    void 벌크_insert() {
        List<Story> storyList = new ArrayList<>();
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
        int count = 0;
        long i = 64;

        for (long j = 35; j < 400_35; j++) {
            for (int k = 0; k < 20; k++) {
                Story story = Story.builder()
                        .id(i++)
                        .user(User.builder().uuid(UUID.fromString(uuidArray[count++ % 13])).build())
                        .wooms(Wooms.builder().id(j).build())
                        .content("아무 내용")
                        .fileName("https://test.com/testAudioFile.tts")
                        .build();
                storyList.add(story);
            }
        }
        storyBulkRepository.saveAll(storyList);
        storyList.clear();
        for (long j = 400_35; j < 800_35; j++) {
            for (int k = 0; k < 20; k++) {
                Story story = Story.builder()
                        .id(i++)
                        .user(User.builder().uuid(UUID.fromString(uuidArray[count++ % 13])).build())
                        .wooms(Wooms.builder().id(j).build())
                        .content("아무 내용")
                        .fileName("https://test.com/testAudioFile.tts")
                        .build();
                storyList.add(story);
            }
        }
        storyBulkRepository.saveAll(storyList);
        storyList.clear();
        for (long j = 800_35; j < 1200_35; j++) {
            for (int k = 0; k < 20; k++) {
                Story story = Story.builder()
                        .id(i++)
                        .user(User.builder().uuid(UUID.fromString(uuidArray[count++ % 13])).build())
                        .wooms(Wooms.builder().id(j).build())
                        .content("아무 내용")
                        .fileName("https://test.com/testAudioFile.tts")
                        .build();
                storyList.add(story);
            }
        }
        storyBulkRepository.saveAll(storyList);
        storyList.clear();
        for (long j = 1200_35; j < 1600_35; j++) {
            for (int k = 0; k < 20; k++) {
                Story story = Story.builder()
                        .id(i++)
                        .user(User.builder().uuid(UUID.fromString(uuidArray[count++ % 13])).build())
                        .wooms(Wooms.builder().id(j).build())
                        .content("아무 내용")
                        .fileName("https://test.com/testAudioFile.tts")
                        .build();
                storyList.add(story);
            }
        }
        storyBulkRepository.saveAll(storyList);
    }
}