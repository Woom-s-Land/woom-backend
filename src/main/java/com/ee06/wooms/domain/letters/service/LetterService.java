package com.ee06.wooms.domain.letters.service;

import com.ee06.wooms.domain.enrollments.repository.EnrollmentRepository;
import com.ee06.wooms.domain.letters.dto.NewLetterRequest;
import com.ee06.wooms.domain.letters.entity.Letter;
import com.ee06.wooms.domain.letters.entity.LetterStatus;
import com.ee06.wooms.domain.letters.exception.ex.NotInSameWoomsException;
import com.ee06.wooms.domain.letters.repository.LetterRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.global.common.CommonResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class LetterService {

    private final LetterRepository letterRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CommonResponse createNewLetter(CustomUserDetails customUserDetails, NewLetterRequest newLetterRequest) {
        User currentUser = fetchUser(customUserDetails.getUuid());
        User targetUser = fetchUser(newLetterRequest.getTargetUserUuid());

        if (!enrollmentRepository.areUsersInSameGroup(currentUser.getUuid(), targetUser.getUuid())) {
            throw new NotInSameWoomsException();
        }

        Letter letter = Letter.builder()
                .sender(currentUser)
                .receiver(targetUser)
                .content(newLetterRequest.getContent())
                .sentDate(LocalDateTime.now())
                .receiveDate(newLetterRequest.getReceiveDateTime())
                .status(LetterStatus.UNREAD)
                .build();
        letterRepository.save(letter);

        return new CommonResponse("ok");
    }


    private User fetchUser(String userUuidStr) {
        UUID userUuid = UUID.fromString(userUuidStr);
        return userRepository.findById(userUuid)
                .orElseThrow(UserNotFoundException::new);
    }

}
