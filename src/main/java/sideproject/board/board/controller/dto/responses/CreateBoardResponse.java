package sideproject.board.board.controller.dto.responses;

import lombok.Builder;
import lombok.Data;
import sideproject.board.board.domain.entity.BoardEntity;

@Data
public class CreateBoardResponse {
	private Long id;
	private String title;
	private String content;

	@Builder
	public CreateBoardResponse(BoardEntity board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
	}
}
