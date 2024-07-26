package sideproject.board.member.contoller.requests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMemberRequest {

	@NotNull
	private String name;
	
	@NotNull
	private int age;

}


