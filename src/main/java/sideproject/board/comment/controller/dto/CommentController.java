package sideproject.board.comment.controller.dto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import sideproject.board.comment.controller.dto.responses.UpdateCommentResponse;
import sideproject.board.comment.controller.dto.responses.createCommentResponse;
import sideproject.board.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/comment")
	public createCommentResponse createComment(@RequestBody CreateCommentRequest request) {

		createCommentResponse response = commentService.createComment(request);
		return response;
	}


	@GetMapping("/comment")
	public List<CommentResponse> getAllComment() {

		List<CommentResponse> response = commentService.getAllComment();
		return response;
	}

	@GetMapping("/comment/{id}")
	public CommentResponse getOneComment(@PathVariable Long id) {

		CommentResponse response = commentService.getOneComment(id);
		return response;
	}

	@PutMapping("/comment/{id}")
	public UpdateCommentResponse updateComment(@PathVariable Long id, @RequestBody CreateCommentRequest request) {
		UpdateCommentResponse response = commentService.updateComment(id, request);
		return response;
	}


	@DeleteMapping("/comment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Long id) {
		try {
			commentService.deleteBoard(id);
			return ResponseEntity.ok("게시물이 삭제되었습니다.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을 수 없습니다.");
		}

	}



}
