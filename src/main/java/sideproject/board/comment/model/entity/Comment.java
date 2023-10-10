package sideproject.board.comment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;



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

}
