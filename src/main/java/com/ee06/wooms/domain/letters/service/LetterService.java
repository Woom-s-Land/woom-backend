package com.ee06.wooms.domain.letters.service;

import com.ee06.wooms.domain.enrollments.repository.EnrollmentRepository;
import com.ee06.wooms.domain.letters.dto.LetterDetailDto;
import com.ee06.wooms.domain.letters.dto.LetterDto;
import com.ee06.wooms.domain.letters.dto.LetterUnreadDto;
import com.ee06.wooms.domain.letters.dto.NewLetterRequest;
import com.ee06.wooms.domain.letters.entity.Letter;
import com.ee06.wooms.domain.letters.entity.LetterStatus;
import com.ee06.wooms.domain.letters.exception.ex.NotInSameWoomsException;
import com.ee06.wooms.domain.letters.exception.ex.NotValidLetterException;
import com.ee06.wooms.domain.letters.repository.LetterRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.UserNotAllowedException;
import com.ee06.wooms.domain.users.exception.ex.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.global.common.CommonResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
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

        LocalDateTime adjustedReceiveDateTime = newLetterRequest.getReceiveDateTime()
                .withHour(12);

        Letter letter = Letter.builder()
                .sender(currentUser)
                .receiver(targetUser)
                .content(newLetterRequest.getContent())
                .sentDate(LocalDateTime.now())
                .receiveDate(adjustedReceiveDateTime)
                .status(LetterStatus.UNREAD)
                .build();

        letterRepository.save(letter);

        return new CommonResponse("ok");
    }

    public Page<LetterDto> getSortedLettersBeforeToday(CustomUserDetails customUserDetails, Pageable pageable) {
        UUID userUuid = UUID.fromString(customUserDetails.getUuid());

        Page<Letter> letters = letterRepository.findByReceiverUuidAndReceiveDateBefore(userUuid, LocalDateTime.now(), pageable);

        return letters.map(this::toLetterDto);
    }

    public LetterDetailDto getLetterDetail(CustomUserDetails customUserDetails, Long letterId) {
        UUID userUuid = UUID.fromString(customUserDetails.getUuid());


        Letter letter = letterRepository.findByIdAndReceiverUuid(letterId, userUuid)
                .orElseThrow(NotValidLetterException::new);

        letter.modifyLetterStatusRead();

        return toLetterDetailDto(letter);
    }

    public int countFutureLetters(CustomUserDetails customUserDetails) {
        UUID userUuid = UUID.fromString(customUserDetails.getUuid());
        return letterRepository.countByReceiverUuidAndReceiveDateAfter(userUuid, LocalDateTime.now());
    }

    public CommonResponse deleteLetter(CustomUserDetails customUserDetails, Long letterId) {
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(NotValidLetterException::new);

        if (!Objects.equals(letter.getReceiver().getUuid(), UUID.fromString(customUserDetails.getUuid()))) {
            throw new UserNotAllowedException();
        }

        letterRepository.delete(letter);
        return new CommonResponse("ok");
    }

    public LetterUnreadDto getTotalUnreadLetter(CustomUserDetails customUserDetails) {
        UUID userUuid = UUID.fromString(customUserDetails.getUuid());

        int unreadCount = letterRepository.countByReceiverUuidAndStatus(userUuid, LetterStatus.UNREAD);
        return LetterUnreadDto.builder()
                .totalUnreadCount(unreadCount)
                .build();
    }


    private User fetchUser(String userUuidStr) {
        UUID userUuid = UUID.fromString(userUuidStr);
        return userRepository.findById(userUuid)
                .orElseThrow(UserNotFoundException::new);
    }

    private LetterDto toLetterDto(Letter letter) {
        return LetterDto.builder()
                .id(letter.getId())
                .senderName(letter.getSender().getName())
                .sentDate(letter.getSentDate())
                .receiveDate(letter.getReceiveDate())
                .status(letter.getStatus())
                .build();
    }

    private LetterDetailDto toLetterDetailDto(Letter letter) {
        return LetterDetailDto.builder()
                .content(letter.getContent())
                .id(letter.getId())
                .sentDate(letter.getSentDate())
                .senderName(letter.getSender().getName())
                .build();
    }


}
