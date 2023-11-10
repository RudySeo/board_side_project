package sideproject.board.board.domain.repository;

import static sideproject.board.board.domain.QBoard.*;
import static sideproject.board.comment.model.entity.QComment.*;

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

	@Override
	public Board findBoardDetail(Long id) {
		return queryFactory.select(board)
			.from(board)
			.leftJoin(board.comments, comment)
			.fetchJoin()
			.where(board.id.eq(id))
			.fetchOne();
	}
}
