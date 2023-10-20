package sideproject.board.board.domain.entity.repository;

import java.util.List;

import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.entity.BoardType;

public interface BoardRepositoryCustom {
	List<Board> findBoardTypeAll(BoardType type);
}
