package com.ee06.wooms.domain.stories.service;

import com.ee06.wooms.domain.stories.Script;
import com.ee06.wooms.domain.stories.dto.StoryWriteRequest;
import com.ee06.wooms.domain.stories.dto.StoryResponse;
import com.ee06.wooms.domain.stories.entity.Story;
import com.ee06.wooms.domain.stories.repository.StoryRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidException;
import com.ee06.wooms.domain.wooms.repository.WoomsRepository;
import com.ee06.wooms.global.ai.exception.FailedRequestToGptException;
import com.ee06.wooms.global.ai.service.AIService;
import com.ee06.wooms.global.aws.service.S3Service;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StoryService {
    private final AIService aiService;
    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final StoryRepository storyRepository;
    private final WoomsRepository woomsRepository;

    @Transactional(readOnly = true)
    public StoryResponse getStories(Long woomsId, Pageable pageable) {
        woomsRepository.findWoomsById(woomsId).orElseThrow(WoomsNotValidException::new);

        List<StoryWriteRequest> stories = storyRepository.findAllByWoomsId(woomsId, pageable)
                .stream()
                .map((story) -> StoryWriteRequest.builder()
                        .id(story.getId())
                        .userNickname(story.getUser().getNickname())
                        .content(story.getContent())
                        .fileName(String.valueOf(story.getFileName()))
                        .build())
                .toList();
        String message = pageable.getPageNumber() + "페이지";
        if (stories.isEmpty()) message = message.concat(Script.NOT_FOUND_STORIES.getScript());

        return StoryResponse.builder()
                .stories(stories)
                .message(message)
                .build();
    }

    public CommonResponse writeStory(CustomUserDetails userDetails, StoryWriteRequest storyWriteRequest, Long woomsId) {
        String fileName = String.valueOf(UUID.randomUUID());
        User user = userRepository.findById(UUID.fromString(userDetails.getUuid())).orElseThrow(UserNotFoundException::new);
        Wooms wooms = woomsRepository.findById(woomsId).orElseThrow(WoomsNotValidException::new);

        String script =
                Optional.ofNullable(aiService.convertScript(storyWriteRequest.getContent(), storyWriteRequest.getUserNickname()))
                        .orElseThrow(FailedRequestToGptException::new);
        File file = aiService.convertMP3File(script, fileName);
        s3Service.save(file, "stories");

        storyRepository.save(Story.of(wooms, user, storyWriteRequest, fileName));

        return new CommonResponse("ok");
    }
}