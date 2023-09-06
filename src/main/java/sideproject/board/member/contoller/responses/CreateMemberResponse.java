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
public class CreateMemberResponse {
	private Long id;
	@NotEmpty(message = "email를 입력해주세요")
	private String email;
	@NotEmpty(message = "password를 입력해주세요")
	private String password;
	@NotNull
	private String name;
	@NotNull
	private int age;

	public static CreateMemberResponse convertToCreateMemberRes(MemberEntity MemberEntity) {
		return CreateMemberResponse.builder()
			.id(MemberEntity.getId())
			.email(MemberEntity.getEmail())
			.password(MemberEntity.getPassword())
			.name(MemberEntity.getName())
			.age(MemberEntity.getAge())
			.build();
	}
}
