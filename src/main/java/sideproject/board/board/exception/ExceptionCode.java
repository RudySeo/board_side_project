package sideproject.board.board.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

	NOT_FOUND_BOARD_ID(1001, "요청한 ID에 해당하는 게시물이 존재하지 않습니다.");

	private final int code;
	private final String message;

}
