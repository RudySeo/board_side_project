package sideproject.board.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String message;

	public ClientException(final ErrorCode errorCode) {
		this.httpStatus = errorCode.getHttpStatus();
		this.message = errorCode.getMessage();
	}
}
