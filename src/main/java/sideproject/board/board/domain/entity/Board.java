package sideproject.board.board.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.common.BaseEntity;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.product.domain.Entity.Product;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String title;

	@Column(nullable = false, length = 10000)
	private String content;

	@Column(nullable = false)
	private String type;

	@Column
	private Long view;
	@Column(name = "likes")
	private Long like;
	@Column
	private Long price;

	@Column
	private String writer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;

	@OneToMany(mappedBy = "board")
	private List<Comment> comments = new ArrayList<>();

	//
	public static Board create(String username, String type, String title, String content, Long price,
		Product test) {
		Product product = Product.builder()
			.price(price)
			.stock(true)
			.location(test.getLocation())
			.build();

		return Board.builder()
			.writer(username)
			.type(type)
			.title(title)
			.content(content)
			.price(price)
			.product(product)
			.build();
	}

	public static Board update(Long id, String title, String content, Long price) {
		return Board.builder()
			.id(id)
			.title(title)
			.content(content)
			.price(price)
			.build();
	}

}
