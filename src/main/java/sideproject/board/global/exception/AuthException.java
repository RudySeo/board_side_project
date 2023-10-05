package sideproject.board.global.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String message;

	public AuthException(final ErrorCode errorCode) {
		this.httpStatus = errorCode.getHttpStatus();
		this.message = errorCode.getMessage();
	}
}
