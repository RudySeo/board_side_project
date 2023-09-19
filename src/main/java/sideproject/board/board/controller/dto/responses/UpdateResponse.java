package sideproject.board.board.controller.dto.responses;

import lombok.Builder;
import lombok.Data;
import sideproject.board.board.domain.entity.Board;

@Data
public class UpdateResponse {
	private Long id;
	private String title;
	private String content;

	@Builder
	public UpdateResponse(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
	}
}
