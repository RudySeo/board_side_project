package sideproject.board.comment.controller.dto.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sideproject.board.comment.model.entity.Comment;

@Data
@AllArgsConstructor
public class CommentResponse {

	private Long id;

	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Builder
	public CommentResponse(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
	}
}
