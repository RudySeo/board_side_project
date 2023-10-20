package sideproject.board.board.domain.entity.repository;

import static sideproject.board.board.domain.entity.QBoard.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.entity.BoardType;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;


	@Override
	public List<Board> findBoardTypeAll(BoardType type) {
		return queryFactory.selectFrom(board)
			.where(board.type.eq(type))
			.fetch();
	}
}
