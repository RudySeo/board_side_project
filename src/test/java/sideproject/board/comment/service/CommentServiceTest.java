package sideproject.board.comment.service;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.repository.BoardRepository;
import sideproject.board.comment.controller.dto.requests.CreateCommentRequest;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.comment.model.repository.CommentRepositoy;
import sideproject.board.member.domain.Entity.Member;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
	@Mock
	CommentRepositoy commentRepositoy;

	@Mock
	BoardRepository boardRepository;

	@InjectMocks
	CommentService commentService;

	Member saveMember;
	Board saveBoard;
	CreateCommentRequest createCommentRequest;
	Comment saveComment;

	@BeforeEach
	void setUp() {
		Member saveMember = Member.builder()
			.id(1L)
			.name("홍길동")
			.build();

		Board saveBoard = Board.builder()
			.id(1L)
			.title("헬로")
			.content("크크크")
			.price(100L)
			.build();

		CreateCommentRequest createCommentRequest = CreateCommentRequest.builder()
			.id(1L)
			.content("테스트")
			.build();

		Comment saveComment = Comment.builder()
			.id(1L)
			.content("테스트")
			.member(saveMember)
			.build();
	}


	@Test
	void createComment() {
		// given
		when(boardRepository.findById(saveBoard.getId())).thenReturn(Optional.of(saveBoard));
		when(commentRepositoy.save(saveComment)).thenReturn(saveComment);

		// when
		commentService.createComment(saveMember, saveMember.getId(), createCommentRequest);

		// then
		verify(commentRepositoy).save(saveComment);
	}

	@Test
	void getAllComment() {
	}

	@Test
	void getOneComment() {
	}

	@Test
	void updateComment() {
	}

	@Test
	void deleteBoard() {
	}
}