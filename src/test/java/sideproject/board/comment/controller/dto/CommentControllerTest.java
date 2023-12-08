package sideproject.board.comment.controller.dto;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import sideproject.board.board.domain.entity.Board;
import sideproject.board.comment.controller.dto.requests.CreateCommentRequest;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.comment.service.CommentService;
import sideproject.board.global.exception.configuration.ThreadLocalContext;
import sideproject.board.member.domain.Entity.Member;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

	@MockBean
	CommentService commentService;
	@Autowired
	private MockMvc mockMvc;

	@DisplayName("댓글 작성하기")
	@Test
	// @WithMockUser(username = "testUser", authorities = {"ROLE_USER"})
	public void createComment() throws Exception {

		// Given
		Long boardId = 1L;
		CreateCommentRequest request = new CreateCommentRequest(1L, "안녕하세요");
		String json = new ObjectMapper().writeValueAsString(request);
		Member member = new Member();
		Board board = new Board();
		ThreadLocalContext.set(member);
		Member threadLocal = ThreadLocalContext.get();

		// 초기화된 Comment 객체 생성
		Comment comment = new Comment();
		comment.create(request.getContent(), board, threadLocal);

		given(commentService.createComment(member, boardId, request)).willReturn(comment);

		// When
		mockMvc.perform(post("/comment/{boardId}", boardId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isOk())
			.andDo(print());

		// Then
		verify(commentService).createComment(member, boardId, request);

	}
}