package sideproject.board.member.contoller.responses;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sideproject.board.member.domain.Entity.Member;

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

	public static CreateMemberResponse convertToCreateMemberRes(Member Member) {
		return CreateMemberResponse.builder()
			.id(Member.getId())
			.email(Member.getEmail())
			.password(Member.getPassword())
			.name(Member.getName())
			.age(Member.getAge())
			.build();
	}
}
