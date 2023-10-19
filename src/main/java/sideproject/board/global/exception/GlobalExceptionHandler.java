package sideproject.board.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(ClientException e) {
		HttpStatus status = e.getHttpStatus();
		log.warn(e.getMessage(), e);
		return ResponseEntity.status(e.getHttpStatus())
			.body(new ErrorResponse(status.value(), e.getMessage()));
	}


}
