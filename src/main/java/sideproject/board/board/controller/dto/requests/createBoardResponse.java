package sideproject.board.board.controller.dto.requests;

import lombok.Builder;
import lombok.Data;
import sideproject.board.board.domain.entity.BoardEntity;

@Data
public class createBoardResponse {
	private Long id;
	private String title;
	private String content;

	@Builder
	public createBoardResponse(BoardEntity board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
	}

}
