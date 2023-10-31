package sideproject.board.comment.controller.dto.responses;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResponse {

	private Long id;

	private String writer;

	private Long boardId;

	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;


	@Builder
	public CommentResponse(Long id, String writer, Long boardId, String content, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.id = id;
		this.writer = writer;
		this.boardId = boardId;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
