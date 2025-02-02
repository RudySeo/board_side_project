package sideproject.board.member.contoller.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.member.domain.Entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberResponse {
	private Long id;

	private String name;

	private int age;

	@Builder
	public UpdateMemberResponse(Member member) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

}
