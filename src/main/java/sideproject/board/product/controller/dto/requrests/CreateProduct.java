package sideproject.board.product.controller.dto.requrests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.product.domain.Entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {

	private Long id;

	private String type;

	private Long price;

	private String location;

	public Product toEntity() {
		return Product.builder()
			.id(getId())
			.type(getType())
			.price(getPrice())
			.location(getLocation())
			.build();
	}

}
