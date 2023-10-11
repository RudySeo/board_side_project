package sideproject.board.board.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.member.domain.Entity.Member;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String title;

	@Column(nullable = false, length = 10000)
	private String content;

	@Column
	private Long view;
	@Column(name = "likes")
	private Long like;
	@Column
	private Long price;

	@ManyToOne
	@JoinColumn(name = "memberId")
	private Member member;

	@OneToMany(mappedBy = "board")
	private List<Comment> comments = new ArrayList<>();


	public void create(Member member, String title, String content, Long price) {
		this.member = member;
		this.title = title;
		this.content = content;
		this.price = price;

	}

	public void update(Long id, String title, String content, Long price) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
	}
}
