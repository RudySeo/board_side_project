package sideproject.board.board.controller.dto.responses;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateResponse {
	private Long id;
	private String title;
	private String content;

	@Builder
	public UpdateResponse(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
}
