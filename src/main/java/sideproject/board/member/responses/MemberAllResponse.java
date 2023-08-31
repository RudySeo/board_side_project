package sideproject.board.member.responses;

import lombok.Data;

@Data
public class MemberAllResponse<T> {

	private T data;
}
