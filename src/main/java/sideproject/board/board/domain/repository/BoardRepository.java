package sideproject.board.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.board.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
