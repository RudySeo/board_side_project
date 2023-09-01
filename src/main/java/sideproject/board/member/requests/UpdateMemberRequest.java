package sideproject.board.member.requests;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateMemberRequest {
	
	@NotNull
	private String name;
	@NotNull
	private int age;
}
