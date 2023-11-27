package sideproject.board.product.domain.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.common.BaseEntity;
import sideproject.board.order.domain.entity.Order;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;

	private Long price;

	private boolean stock = false;

	private String location;

	@OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
	private Board board;

	@OneToMany(mappedBy = "product")
	private List<Order> orders = new ArrayList<>();

	public static Product create(Product request) {
		return Product.builder()
			.type(request.type)
			.price(request.price)
			.stock(true)
			.location(request.location)
			.build();
	}
}
