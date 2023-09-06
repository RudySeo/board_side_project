package sideproject.board.member.contoller.responses;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.member.Entity.MemberEntity;

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

	public static UpdateMemberResponse convertToUpdateMemberRes(MemberEntity MemberEntity) {
		return UpdateMemberResponse.builder()
			.id(MemberEntity.getId())
			.name(MemberEntity.getName())
			.age(MemberEntity.getAge())
			.build();
	}
}
