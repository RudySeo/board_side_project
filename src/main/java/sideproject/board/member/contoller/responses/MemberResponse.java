package sideproject.board.member.contoller.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sideproject.board.member.domain.Entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponse {

	private Long id;

	private String email;

	private String name;

	private int age;

	private int money;

	public MemberResponse(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.name = member.getName();
		this.age = member.getAge();
		this.money = member.getMoney();
	}

}
