package sideproject.board.point.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.point.domain.Entity.PointHistory;

public interface PointRepository extends JpaRepository<PointHistory, Long> {
	Page<PointHistory> findAllByMemberId(Long userId, Pageable pageable);
}
