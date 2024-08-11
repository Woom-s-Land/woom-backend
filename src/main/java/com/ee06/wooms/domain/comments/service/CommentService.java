package com.ee06.wooms.domain.comments.service;

import com.ee06.wooms.domain.comments.dto.CommentRequest;
import com.ee06.wooms.domain.comments.dto.CommentResponse;
import com.ee06.wooms.domain.comments.entity.Comment;
import com.ee06.wooms.domain.comments.exception.ex.ExistWroteCommentException;
import com.ee06.wooms.domain.comments.repository.CommentRepository;
import com.ee06.wooms.domain.users.dto.CustomUserDetails;
import com.ee06.wooms.domain.users.entity.User;
import com.ee06.wooms.domain.users.exception.ex.UserNotFoundException;
import com.ee06.wooms.domain.users.repository.UserRepository;
import com.ee06.wooms.domain.wooms.entity.Wooms;
import com.ee06.wooms.domain.wooms.exception.ex.WoomsNotValidException;
import com.ee06.wooms.domain.wooms.repository.WoomsRepository;
import com.ee06.wooms.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final WoomsRepository woomsRepository;
    private final UserRepository userRepository;

    public List<CommentResponse> getComments(Long woomsId, Pageable pageable) {
        return commentRepository.findAllByWoomsId(woomsId, pageable)
                .stream()
                .map(Comment::toDto)
                .toList();
    }


    public CommonResponse create(CustomUserDetails userDetails, Long woomsId, CommentRequest commentRequest) {

        if(isWroteToday(userDetails, woomsId)) throw new ExistWroteCommentException();

        User user = userRepository.findById(UUID.fromString(userDetails.getUuid())).orElseThrow(UserNotFoundException::new);

        Wooms wooms = woomsRepository.findById(woomsId).orElseThrow(WoomsNotValidException::new);

        commentRepository.save(Comment.of(wooms, user, commentRequest));

        return new CommonResponse("ok");
    }

    public boolean isWroteToday(CustomUserDetails userDetails, Long woomsId){
        return commentRepository.isWriteByToday(LocalDate.now().atStartOfDay(),
                woomsId, UUID.fromString(userDetails.getUuid()));
    }
}
