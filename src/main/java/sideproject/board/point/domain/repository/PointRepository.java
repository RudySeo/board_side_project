package sideproject.board.point.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.point.domain.Entity.PointHistory;

public interface PointRepository extends JpaRepository<PointHistory, Long>, PointRepositoryCustom {
}
