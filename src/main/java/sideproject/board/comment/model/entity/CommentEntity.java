package sideproject.board.comment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.board.comment.controller.dto.requests.CreateCommentRequest;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;




	public static CommentEntity toEntity(CreateCommentRequest request) {
		return CommentEntity.builder()
			.id(request.getId())
			.content(request.getContent())
			.build();
	}
}
