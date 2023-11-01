package sideproject.board.comment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.comment.controller.dto.requests.CreateCommentRequest;
import sideproject.board.common.BaseEntity;
import sideproject.board.member.domain.Entity.Member;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardId")
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;



	public static Comment toEntity(CreateCommentRequest request) {
		return Comment.builder()
			.id(request.getId())
			.content(request.getContent())
			.build();
	}

	public void update(Long id, String content) {
		this.id = id;
		this.content = content;
	}

	public static Comment create(String content, Board board, Member member) {
		return Comment.builder()
			.board(board)
			.member(member)
			.content(content)
			.build();
	}

}
