package sideproject.board.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.comment.model.repository.CommentRepositoy;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepositoy commentRepositoy;

	@Transactional
	public Comment createComment(Comment request) {

		return commentRepositoy.save(request);
	}

	@Transactional(readOnly = true)
	public List<Comment> getAllComment() {

		return commentRepositoy.findAll();
	}

	@Transactional(readOnly = true)
	public Comment getOneComment(Long id) {

		return commentRepositoy.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	@Transactional
	public Comment updateComment(Long id, Comment request) {

		Comment comment = commentRepositoy.findById(id).orElseThrow(() -> new IllegalArgumentException());

		String content = request.getContent();

		if (content != null && content.length() > 1000) {
			throw new IllegalArgumentException("글자수 오류 입니다");
		}
		comment.update(id, content);

		return commentRepositoy.save(comment);
	}

	@Transactional
	public void deleteBoard(Long id) {
		if (!commentRepositoy.existsById(id)) {
			throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
		}
		commentRepositoy.deleteById(id);
	}

}
