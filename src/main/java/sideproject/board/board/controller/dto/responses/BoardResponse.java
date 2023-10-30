package sideproject.board.board.controller.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.board.domain.entity.Board;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

	private Long id;

	private String type;

	private String writer;

	private String title;

	private String content;

	private Long view;

	private Long like;

	private Long price;


	@Builder
	public BoardResponse(Board board) {
		this.id = board.getId();
		this.type = board.getType();
		this.writer = board.getWriter();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.view = board.getView();
		this.like = board.getLike();
		this.price = board.getPrice();
	}
}
