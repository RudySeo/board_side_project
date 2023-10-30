package sideproject.board.board.controller.dto.responses;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	public BoardResponse(Long id, String type, String writer, String title, String content, Long view, Long like,
		Long price) {
		this.id = id;
		this.type = type;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.view = view;
		this.like = like;
		this.price = price;
	}
}
