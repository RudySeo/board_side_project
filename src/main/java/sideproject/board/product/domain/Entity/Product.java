package sideproject.board.product.domain.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import sideproject.board.board.domain.entity.Board;
import sideproject.board.common.BaseEntity;

@Entity
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long price;

	private boolean stock;

	private String location;

	@OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
	private Board board;

}
