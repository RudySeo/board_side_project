package sideproject.board.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

	NOT_FOUND_MEMBER_ID(HttpStatus.NOT_FOUND, "요청한 ID에 해당하는 유저 아이디가 존재하지 않습니다."),
	NOT_FOUND_EMAIL_ID(HttpStatus.NOT_FOUND, "요청한 ID에 해당하는 이메일이 존재하지 않습니다."),
	NOT_FOUND_BOARD_ID(HttpStatus.NOT_FOUND, "요청한 ID에 해당하는 게시물이 존재하지 않습니다."),
	NOT_FOUND_PRODUCT_ID(HttpStatus.NOT_FOUND, "요청한 ID에 해당하는 상품이 존재하지 않습니다."),
	NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
	PRODUCT_SOLD_OUT(HttpStatus.BAD_REQUEST, "상품이 품절 입니다."),
	NOT_FOUND_POINT_HISTORY_ID(HttpStatus.NOT_FOUND, "요청한 ID에 해당하는 거래내역이 존재하지 않습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다. 관리자에게 문의하세요."),
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 사용자를 찾을 수 없습니다."),
	ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 상품을 찾을 수 없습니다."),
	//401
	INVALID_AUTHORIZATION_CODE(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 코드입니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;

}
