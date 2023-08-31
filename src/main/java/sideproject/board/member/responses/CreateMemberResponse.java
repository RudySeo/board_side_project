package sideproject.board.member.responses;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateMemberResponse {
	private String email;
	@NotEmpty(message = "password를 입력해주세요")
	private String password;
	@NotNull
	private String name;
	@NotNull
	private int age;
}
