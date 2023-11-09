package sideproject.board.point.domain.repository;

import static sideproject.board.member.domain.Entity.QMember.*;
import static sideproject.board.point.domain.Entity.QPointHistory.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import sideproject.board.point.domain.Entity.PointHistory;


@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<PointHistory> findAllByMemberId(Long memberId, Pageable pageable) {
		QueryResults<PointHistory> results = queryFactory
			.selectFrom(pointHistory)
			.join(pointHistory.member, member)
			.on(member.id.eq(memberId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetchResults();
		
		List<PointHistory> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
	}
}
