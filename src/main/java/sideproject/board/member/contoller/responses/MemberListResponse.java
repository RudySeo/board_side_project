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
public class MemberListResponse {
	private Long id;
	@NotEmpty(message = "email를 입력해주세요")
	private String email;
	@NotNull
	private String name;
	@NotNull
	private int age;

	public static MemberListResponse convertToGetMemberRes(Member Member) {
		return MemberListResponse.builder()
			.id(Member.getId())
			.email(Member.getEmail())
			.name(Member.getName())
			.age(Member.getAge())
			.build();
	}
}
