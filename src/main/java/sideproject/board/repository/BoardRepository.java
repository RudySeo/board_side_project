package sideproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.board.model.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
