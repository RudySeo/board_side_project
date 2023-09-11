package sideproject.board.comment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.comment.controller.dto.requests.CreateCommentRequest;
import sideproject.board.comment.controller.dto.responses.CommentResponse;
import sideproject.board.comment.controller.dto.responses.UpdateCommentResponse;
import sideproject.board.comment.controller.dto.responses.createCommentResponse;
import sideproject.board.comment.model.entity.CommentEntity;
import sideproject.board.comment.model.repository.CommentRepositoy;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepositoy commentRepositoy;

	@Transactional
	public createCommentResponse createComment(CreateCommentRequest request) {

		CommentEntity comment = CommentEntity.toEntity(request);
		commentRepositoy.save(comment);

		return createCommentResponse.builder().comment(comment).build();
	}

	@Transactional(readOnly = true)
	public List<CommentResponse> getAllComment() {

		List<CommentEntity> comment = commentRepositoy.findAll();

		List<CommentResponse> result = comment.stream()
			.map(m -> new CommentResponse(m.getId(), m.getContent()))
			.collect(Collectors.toList());

		return result;

	}

	@Transactional(readOnly = true)
	public CommentResponse getOneComment(Long id) {

		CommentEntity comment = commentRepositoy.findById(id).orElseThrow(() -> new IllegalArgumentException());

		return CommentResponse.builder().comment(comment).build();
	}

	@Transactional
	public UpdateCommentResponse updateComment(Long id, CreateCommentRequest request) {

		CommentEntity comment = commentRepositoy.findById(id).orElseThrow(() -> new IllegalArgumentException());

		CommentEntity updateComment = new CommentEntity(request.getId(), request.getContent());

		commentRepositoy.save(updateComment);

		return UpdateCommentResponse.builder().comment(updateComment).build();
	}

	@Transactional
	public void deleteBoard(Long id) {
		if (!commentRepositoy.existsById(id)) {
			throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
		}
		commentRepositoy.deleteById(id);
	}
}
