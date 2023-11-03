package sideproject.board.point.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.point.domain.Entity.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
}
