package sideproject.board.board.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.product.controller.dto.requrests.CreateProduct;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBoardRequest {

	private String title;
	private String content;
	private String type;

	private CreateProduct product;

	public Board toEntity() {
		return Board.builder()
			.title(getTitle())
			.type(getType())
			.content(getContent())
			.build();
	}

}
