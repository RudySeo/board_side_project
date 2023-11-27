package sideproject.board.member.domain.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.common.BaseEntity;
import sideproject.board.order.domain.entity.Order;
import sideproject.board.point.domain.Entity.PointHistory;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false)
	private int money = 0;

	private LocalDate lastLoginDate = null;

	@Enumerated(EnumType.STRING)
	private Role status;

	@OneToMany(mappedBy = "member")
	private List<Comment> comment = new ArrayList<>();


	@OneToMany(mappedBy = "member")
	private List<Board> boards = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<PointHistory> points = new ArrayList<>();

	public LocalDate setLastLoginDate(LocalDate time) {
		return this.lastLoginDate = time;
	}

	public int addMoney(int money) {
		return this.money += money;
	}

	public void update(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int charge(int amount) {
		return this.money += amount;
	}
}
