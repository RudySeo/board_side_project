package sideproject.board.comment.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sideproject.board.comment.controller.dto.requests.CreateCommentRequest;
import sideproject.board.comment.controller.dto.responses.CommentResponse;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.comment.service.CommentService;
import sideproject.board.global.exception.configuration.ThreadLocalContext;
import sideproject.board.member.domain.Entity.Member;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/comment/{boardId}")
	public CommentResponse createComment(@PathVariable Long boardId, @RequestBody CreateCommentRequest request) {
		Member memberLocal = ThreadLocalContext.get();

		Comment comment = commentService.createComment(memberLocal, boardId, request);
		return CommentResponse.builder().comment(comment).build();
	}

	@GetMapping("/comment")
	public List<CommentResponse> getAllComment() {

		List<Comment> comment = commentService.getAllComment();

		List<CommentResponse> result = comment.stream()
			.map(m -> new CommentResponse(m.getId(), m.getMember().getName(), m.getBoard().getId(), m.getContent(),
				m.getCreatedAt(),
				m.getUpdatedAt()))
			.collect(Collectors.toList());

		return result;
	}

	@GetMapping("/comment/{id}")
	public CommentResponse getOneComment(@PathVariable Long id) {

		Comment comment = commentService.getOneComment(id);

		return CommentResponse.builder().comment(comment).build();
	}

	@PutMapping("/comment/{id}")
	public CommentResponse updateComment(@PathVariable Long id, @RequestBody CreateCommentRequest request) {

		Comment comment = commentService.updateComment(id, request.toEntity());

		return CommentResponse.builder().comment(comment).build();
	}

	@DeleteMapping("/comment/{id}")
	public String deleteComment(@PathVariable Long id) {

		commentService.deleteBoard(id);

		return "댓글이 삭제되었습니다.";
	}
}
