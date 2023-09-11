package sideproject.board.comment.controller.dto.requests;

import lombok.Builder;
import lombok.Data;
import sideproject.board.comment.model.entity.CommentEntity;

@Data
public class UpdateCommentRequest {

	private Long id;

	private String content;

	@Builder
	public UpdateCommentRequest(CommentEntity comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
	}
}
