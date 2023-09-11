package sideproject.board.comment.controller.dto.responses;

import lombok.Builder;
import lombok.Data;
import sideproject.board.comment.model.entity.CommentEntity;

@Data
public class UpdateCommentResponse {

	private Long id;

	private String content;

	@Builder
	public UpdateCommentResponse(CommentEntity comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
	}
}
