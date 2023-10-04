package sideproject.board.member.contoller.responses;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.member.domain.Entity.Member;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberResponse {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private int age;

	public static UpdateMemberResponse convertToUpdateMemberRes(Member Member) {
		return UpdateMemberResponse.builder()
			.id(Member.getId())
			.name(Member.getName())
			.age(Member.getAge())
			.build();
	}
}
