package sideproject.board.board.domain.entity.repository;

import java.util.List;

import sideproject.board.board.Sort;
import sideproject.board.board.domain.entity.Board;

public interface BoardRepositoryCustom {
	List<Board> findBoardTypeAll(String type, Sort orderBy);
}
