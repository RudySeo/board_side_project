package sideproject.board.member.contoller.requests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberRequest {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private int age;

	// public static UpdateMemberRequest convertToUpdateMemberReq(MemberEntity MemberEntity) {
	// 	return UpdateMemberRequest.builder()
	// 		.id(MemberEntity.getId())
	// 		.name(MemberEntity.getName())
	// 		.age(MemberEntity.getAge())
	// 		.build();
	// }
}


