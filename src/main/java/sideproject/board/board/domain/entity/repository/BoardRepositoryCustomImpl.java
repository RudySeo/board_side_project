package sideproject.board.board.domain.entity.repository;

import static sideproject.board.board.domain.entity.QBoard.*;

import java.util.List;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.entity.QBoard;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;


	@Override
	public List<Board> findBoardTypeAll(String type, String orderBy) {
		OrderSpecifier<?> orderSpecifier = getOrderSpecifier(orderBy, board);

		return queryFactory.selectFrom(board)
			.where(board.type.eq(type))
			.orderBy(board.price.desc())
			.fetch();
	}

	private OrderSpecifier<?> getOrderSpecifier(String orderBy, QBoard board) {
		if ("price".equalsIgnoreCase(orderBy)) {
			return board.price.desc();
		} else if ("views".equalsIgnoreCase(orderBy)) {
			return board.view.desc();
		} else {
			return board.id.asc(); // 기본 정렬은 ID 오름차순
		}
	}
}
