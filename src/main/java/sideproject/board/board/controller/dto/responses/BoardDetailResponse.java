package sideproject.board.board.controller.dto.responses;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.comment.controller.dto.responses.CommentResponse;

@Data
@NoArgsConstructor
public class BoardDetailResponse {
	private Long id;

	private String type;

	private String writer;

	private String title;

	private String content;

	private Long view;

	private Long like;

	private Long price;

	private List<CommentResponse> comment;

	// @Builder
	// public BoardDetailResponse(Long id, String type, String writer, String title, String content, Long view, Long like,
	// 	Long price) {
	// 	this.id = id;
	// 	this.type = type;
	// 	this.writer = writer;
	// 	this.title = title;
	// 	this.content = content;
	// 	this.view = view;
	// 	this.like = like;
	// 	this.price = price;
	// }

	@Builder
	public BoardDetailResponse(Long id, String type, String writer, String title, String content, Long view, Long like,
		Long price, List<CommentResponse> comment) {
		this.id = id;
		this.type = type;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.view = view;
		this.like = like;
		this.price = price;
		this.comment = comment;
	}
}
