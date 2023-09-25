package sideproject.board.comment.controller.dto.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sideproject.board.comment.model.entity.Comment;

@Data
@AllArgsConstructor
public class CreateCommentResponse {
	private Long id;

	private String content;

	private LocalDateTime createdAt;

	@Builder
	public CreateCommentResponse(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.createdAt = comment.getCreatedAt();
	}
}
