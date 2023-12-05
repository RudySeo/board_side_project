package sideproject.board.order.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import sideproject.board.common.BaseEntity;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.product.domain.Entity.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "OrderItem")
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	public static Order updateOder(Member member, Product product) {
		return Order.builder()
			.member(member)
			.product(product)
			.build();
	}
}
