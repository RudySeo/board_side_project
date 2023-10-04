package sideproject.board.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public record ErrorResponse(HttpStatus httpStatus, String message) {

}