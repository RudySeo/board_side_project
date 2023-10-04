package sideproject.board.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String message;

	public BadRequestException(final ErrorCode errorCode) {
		this.httpStatus = errorCode.getHttpStatus();
		this.message = errorCode.getMessage();
	}
}
