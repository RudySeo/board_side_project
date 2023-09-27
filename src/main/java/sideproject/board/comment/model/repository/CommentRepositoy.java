package sideproject.board.comment.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.comment.model.entity.Comment;

public interface CommentRepositoy extends JpaRepository<Comment, Long> {
}
