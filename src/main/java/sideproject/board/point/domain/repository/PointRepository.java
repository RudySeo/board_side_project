package sideproject.board.point.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.point.domain.Entity.PointHistory;

public interface PointRepository extends JpaRepository<PointHistory, Long> {
	List<PointHistory> findAllByMemberId(Long memberId);
}
