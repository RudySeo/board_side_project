package sideproject.board.board.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class boardResponse {

	private Long id;

	private String title;

	private String content;

	private Long view;

	private Long likes;


}
