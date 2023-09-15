package sideproject.board.board.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.board.domain.entity.Board;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBoardRequest {

	private Long id;
	private String title;
	private String content;
	private Long price;

	public Board toEntity() {
		return Board.builder()
			.id(getId())
			.title(getTitle())
			.content(getTitle())
			.build();
	}
}
