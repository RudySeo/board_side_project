package sideproject.board.board.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardType {

	BOARD("BOARD"), PRODUCT("PRODUCT");

	private final String boardType;
}
