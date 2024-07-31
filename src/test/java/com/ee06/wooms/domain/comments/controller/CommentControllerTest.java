package com.ee06.wooms.domain.comments.controller;

import com.ee06.wooms.domain.comments.dto.CommentRequest;
import com.ee06.wooms.domain.comments.dto.CommentResponse;
import com.ee06.wooms.domain.comments.service.CommentService;
import com.ee06.wooms.global.common.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@ExtendWith(SpringExtension.class)
@WithMockUser
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CommentService commentService;

    CommentResponse commentResponse;
    CommentRequest commentRequest;

    @BeforeEach
    void init() {
        commentRequest = CommentRequest.builder()
                .content("월요일엔 커피를 먹자")
                .build();
        commentResponse = CommentResponse.builder()
                .nickname("송송송")
                .content("월요일엔 커피를 먹자")
                .costume(1)
                .createdDate(LocalDateTime.now())
                .build();
    }


    @Test
    @DisplayName("방명록 목록 가져오기!")
    void 방명록가져오기() throws Exception {
        List<CommentResponse> result = new ArrayList<>();

        result.add(commentResponse);
        result.add(commentResponse);
        result.add(commentResponse);

        given(commentService.getComments(any(), any())).willReturn(result);

        mockMvc.perform(get("/api/comments/wooms/{woomsId}",1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nickname").value("송송송"))
                .andExpect(jsonPath("$[1].content").value("월요일엔 커피를 먹자"))
                .andExpect(jsonPath("$[2].costume").value(1))
                .andDo(print());
    }
    @Test
    @DisplayName("방명록 생성")
    void 방명록생성() throws Exception {
        given(commentService.create(any(), any(), any())).willReturn(new CommonResponse("ok"));

        mockMvc.perform(post("/api/comments/wooms/{woomsId}",1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("ok"))
                .andDo(print());
    }

    @Test
    @DisplayName("오늘 방명록 작성 여부")
    void 방명록작성여부() throws Exception {
        given(commentService.create(any(), any(), any())).willReturn(new CommonResponse("ok"));

        mockMvc.perform(get("/api/comments/wooms/{woomsId}/today",1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("ok"))
                .andDo(print());
    }
}