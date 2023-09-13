package sideproject.board.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.domain.BoardRepository;
import sideproject.board.board.domain.entity.BoardEntity;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public BoardEntity createBoard(BoardEntity request) {

		return boardRepository.save(request);
	}

	@Transactional(readOnly = true)
	public List<BoardEntity> getAllBoard() {

		return boardRepository.findAll();
	}

	@Transactional(readOnly = true)
	public BoardEntity getOneBoard(Long id) {

		return boardRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("아이디 찾을 수 없습니다."));

	}

	@Transactional
	public BoardEntity updateBoard(Long id, UpdateRequest request) {

		BoardEntity board = boardRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("아이디 찾을 수 없습니다."));

		board.setTitle(request.getTitle());
		board.setContent(request.getContent());

		return null;
	}

	@Transactional
	public void deleteBoard(Long id) {
		if (!boardRepository.existsById(id)) {
			throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
		}
		boardRepository.deleteById(id);
	}
}
