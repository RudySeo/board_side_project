package sideproject.board.board.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.board.Sort;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.repository.BoardRepository;
import sideproject.board.comment.model.repository.CommentRepositoy;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.domain.Entity.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

	private final BoardRepository boardRepository;

	private final MemberRepository memberRepository;

	private final CommentRepositoy commentRepositoy;

	@Transactional
	public Board saveBoard(Board request, String username) {

		request.create(username, request.getType(), request.getTitle(), request.getContent(), request.getPrice());

		return boardRepository.save(request);
	}

	@Transactional(readOnly = true)
	public List<Board> getAllBoard(String type, Sort orderBy) {

		return boardRepository.findBoardTypeAll(type, orderBy);
	}

	@Transactional(readOnly = true)
	public Board getOneBoard(Long id) {

		return boardRepository.findBoardDetail(id);

	}

	@Transactional
	public Board updateBoard(Long id, UpdateRequest request) {

		Board board = boardRepository.findById(id)
			.orElseThrow(() -> new ClientException(NOT_FOUND_BOARD_ID));

		board.update(id, request.getTitle(), request.getContent(), request.getPrice());

		return board;
	}

	@Transactional
	public void deleteBoard(Long id) {
		if (!boardRepository.existsById(id)) {
			throw new ClientException(NOT_FOUND_BOARD_ID);
		}
		boardRepository.deleteById(id);
	}
}
