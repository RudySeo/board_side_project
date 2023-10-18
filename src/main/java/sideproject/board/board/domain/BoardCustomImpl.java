package sideproject.board.board.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardCustomImpl implements BoardCustom {

	private final JPAQueryFactory queryFactory;

	

}
