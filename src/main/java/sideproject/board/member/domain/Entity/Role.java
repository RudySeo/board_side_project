package sideproject.board.member.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

	private final String role;
}
