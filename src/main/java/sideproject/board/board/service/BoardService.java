package sideproject.board.board.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.board.Sort;
import sideproject.board.board.controller.dto.requests.CreateBoardRequest;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.repository.BoardRepository;
import sideproject.board.comment.model.repository.CommentRepositoy;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.product.domain.Entity.Product;
import sideproject.board.product.domain.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

	private final BoardRepository boardRepository;

	private final MemberRepository memberRepository;

	private final CommentRepositoy commentRepositoy;

	private final ProductRepository productRepository;

	@Transactional
	public Board saveBoard(CreateBoardRequest request, String username) {

		Product products = request.getProduct().toEntity();
		productRepository.save(products);

		Board board = Board.create(username, request.getTitle(), request.getType(), request.getContent(), products);

		return boardRepository.save(board);
	}


	@Transactional(readOnly = true)
	public List<Board> getAllBoard(String type, Sort orderBy) {

		return boardRepository.findBoardTypeAll(type, orderBy);
	}

	@Transactional(readOnly = true)
	public Board getOneBoard(Long id) {

		return boardRepository.findById(id).orElseThrow(() -> new ClientException(NOT_FOUND_BOARD_ID));
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
