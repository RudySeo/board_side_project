package sideproject.board.comment.controller.dto.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sideproject.board.comment.model.entity.Comment;

@Data
@AllArgsConstructor
@Builder
public class CreateCommentRequest {

	private Long id;

	@NotEmpty(message = "content 빈값 일 수 없습니다")
	@NotNull(message = "content Null 일 수 없습니다")
	@Size(min = 10, max = 1000)
	private String content;

	public Comment toEntity() {
		return Comment.builder()
			.id(getId())
			.content(getContent())
			.build();
	}

}
