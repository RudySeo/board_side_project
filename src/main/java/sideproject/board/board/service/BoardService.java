package sideproject.board.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.domain.BoardRepository;
import sideproject.board.board.domain.entity.Board;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public Board saveBoard(Board request) {

		return boardRepository.save(request);
	}

	@Transactional(readOnly = true)
	public List<Board> getAllBoard() {

		return boardRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Board getOneBoard(Long id) {

		return boardRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("아이디 찾을 수 없습니다."));

	}

	@Transactional
	public Board updateBoard(Long id, UpdateRequest request) {

		Board board = boardRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("아이디 찾을 수 없습니다."));

		board.update(id, request.getTitle(), request.getContent(), request.getPrice());

		return board;
	}

	@Transactional
	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}
}
