package sideproject.board.product.controller.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponse {

	private Long id;

	private String type;

	private Long price;

	private boolean stock;

	private String location;

}
