package sideproject.board.member.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class createMemberRequest {

	@NotEmpty(message = "email를 입력해주세요")
	private String email;
	@NotEmpty(message = "password를 입력해주세요")
	private String password;
	@NotNull
	private String name;
	@NotNull
	private int age;
}
