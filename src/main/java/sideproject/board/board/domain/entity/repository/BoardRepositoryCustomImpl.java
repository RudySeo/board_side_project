package sideproject.board.board.domain.entity.repository;

import static sideproject.board.board.domain.entity.QBoard.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.Sort;
import sideproject.board.board.domain.entity.Board;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Board> findBoardTypeAll(String type, Sort orderBy) {
		JPAQuery<Board> query = queryFactory.selectFrom(board);

		query.where(board.type.eq(type));
		switch (orderBy) {
			case VIEW:
				query.orderBy(board.view.desc());
				break;
			case LIKE:
				query.orderBy(board.like.desc());
				break;
			case CREATED_AT:
				query.orderBy(board.createdAt.desc());
				break;
			case PRICE:
				query.orderBy(board.price.desc());
				break;
			default:
				break;
		}
		return query.fetch();

	}
}
