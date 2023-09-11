package sideproject.board.comment.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.comment.model.entity.CommentEntity;

public interface CommentRepositoy extends JpaRepository<CommentEntity, Long> {
}
