package sideproject.board.point.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sideproject.board.point.domain.Entity.PointHistory;

public interface PointRepositoryCustom {

	Page<PointHistory> findAllByMemberId(Long memberId, Pageable pageable);


}
