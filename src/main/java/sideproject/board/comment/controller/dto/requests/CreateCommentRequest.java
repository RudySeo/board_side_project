package sideproject.board.comment.controller.dto.requests;

import lombok.Data;

@Data
public class CreateCommentRequest {

	private Long id;

	private String content;
}
