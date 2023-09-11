package sideproject.board.comment.controller.dto.responses;

import lombok.Builder;
import lombok.Data;
import sideproject.board.comment.model.entity.CommentEntity;

@Data
public class createCommentResponse {

	private Long id;

	private String content;

	@Builder
	public createCommentResponse(CommentEntity comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
	}
}
