package sideproject.board.global.exception;

import org.springframework.http.HttpStatus;

public class LoginException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String message;

	public LoginException(final ErrorCode errorCode) {
		this.httpStatus = errorCode.getHttpStatus();
		this.message = errorCode.getMessage();
	}
}

