package sideproject.board.board.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRequest {
	private Long id;
	private String title;
	private String content;
	
	private Long price;
}
