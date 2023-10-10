package sideproject.board.member.contoller.requests;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberRequest {

	@NotEmpty(message = "email 입력해주세요")
	private String email;

	@NotEmpty(message = "password 입력해주세요")
	private String password;

}
