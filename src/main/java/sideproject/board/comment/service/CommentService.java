package sideproject.board.comment.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.entity.repository.BoardRepository;
import sideproject.board.comment.controller.dto.requests.CreateCommentRequest;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.comment.model.repository.CommentRepositoy;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.domain.Entity.Member;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

	private final CommentRepositoy commentRepositoy;

	private final BoardRepository boardRepository;

	@Transactional
	public Comment createComment(Member member, Long boardId, CreateCommentRequest request) {

		Board board = boardRepository.findById(boardId).orElseThrow(() -> new ClientException(NOT_FOUND_BOARD_ID));
		Comment comment = Comment.create(request.getContent(), board, member);
		return commentRepositoy.save(comment);
	}

	@Transactional(readOnly = true)
	public List<Comment> getAllComment() {

		return commentRepositoy.findAll();

	}

	@Transactional(readOnly = true)
	public Comment getOneComment(Long id) {

		return commentRepositoy.findById(id).orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

	}

	@Transactional
	public Comment updateComment(Long id, Comment request) {

		Comment comment = commentRepositoy.findById(id).orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

		comment.update(id, request.getContent());

		return commentRepositoy.save(comment);


	}

	@Transactional
	public void deleteBoard(Long id) {
		if (!commentRepositoy.existsById(id)) {
			throw new ClientException(NOT_FOUND_BOARD_ID);
		}
		commentRepositoy.deleteById(id);
	}

}
