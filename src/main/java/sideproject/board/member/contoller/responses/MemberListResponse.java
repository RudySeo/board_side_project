package sideproject.board.member.contoller.responses;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sideproject.board.member.Entity.MemberEntity;

@Data
@AllArgsConstructor
@Builder
public class MemberListResponse {
	private Long id;
	@NotEmpty(message = "email를 입력해주세요")
	private String email;
	@NotNull
	private String name;
	@NotNull
	private int age;

	public static MemberListResponse convertToGetMemberRes(MemberEntity MemberEntity) {
		return MemberListResponse.builder()
			.id(MemberEntity.getId())
			.email(MemberEntity.getEmail())
			.name(MemberEntity.getName())
			.age(MemberEntity.getAge())
			.build();
	}
}
