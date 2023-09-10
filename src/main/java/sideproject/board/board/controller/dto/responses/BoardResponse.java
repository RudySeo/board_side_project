package sideproject.board.board.controller.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.board.domain.entity.BoardEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

	private Long id;

	private String title;

	private String content;

	private Long view;

	private Long likes;

	@Builder
	public BoardResponse(BoardEntity board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.view = board.getView();
		this.likes = board.getLikes();
	}
}
