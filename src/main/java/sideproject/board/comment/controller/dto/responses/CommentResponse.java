package sideproject.board.comment.controller.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sideproject.board.comment.model.entity.CommentEntity;

@Data
@AllArgsConstructor
public class CommentResponse {

	private Long id;

	private String content;

	@Builder
	public CommentResponse(CommentEntity comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
	}
}
