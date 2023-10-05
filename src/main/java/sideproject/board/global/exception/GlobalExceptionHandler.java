package sideproject.board.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(ClientException e) {

		log.warn(e.getMessage(), e);
		return ResponseEntity.status(e.getHttpStatus())
			.body(new ErrorResponse(e.getHttpStatus(), e.getMessage()));
	}


}
