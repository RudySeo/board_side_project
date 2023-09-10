package sideproject.board.board.controller.dto.requests;

import lombok.Data;

@Data
public class UpdateRequest {
	private Long id;
	private String title;
	private String content;
}
