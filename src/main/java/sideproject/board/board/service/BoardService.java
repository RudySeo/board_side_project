package sideproject.board.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.controller.dto.requests.CreateBoardRequest;
import sideproject.board.board.controller.dto.requests.boardResponse;
import sideproject.board.board.controller.dto.requests.createBoardResponse;
import sideproject.board.board.domain.BoardRepository;
import sideproject.board.board.domain.entity.BoardEntity;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;


	public createBoardResponse createBoard(CreateBoardRequest request) {

		BoardEntity board = BoardEntity.toEntity(request);
		boardRepository.save(board);

		return createBoardResponse.builder().board(board).build();

	}

	public List<boardResponse> getAllBoard() {

		List<BoardEntity> board = boardRepository.findAll();

		List<boardResponse> result = board.stream()
			.map(m -> new boardResponse(m.getId(), m.getTitle(), m.getContent(), m.getView(), m.getLikes()))
			.collect(Collectors.toList());

		return result;
	}
}
