package sideproject.board.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.controller.dto.requests.CreateBoardRequest;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.controller.dto.responses.BoardResponse;
import sideproject.board.board.controller.dto.responses.CreateBoardResponse;
import sideproject.board.board.controller.dto.responses.UpdateResponse;
import sideproject.board.board.domain.BoardRepository;
import sideproject.board.board.domain.entity.BoardEntity;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public CreateBoardResponse createBoard(CreateBoardRequest request) {

		BoardEntity board = BoardEntity.toEntity(request);
		boardRepository.save(board);

		return CreateBoardResponse.builder().board(board).build();

	}

	@Transactional(readOnly = true)
	public List<BoardResponse> getAllBoard() {

		List<BoardEntity> board = boardRepository.findAll();

		List<BoardResponse> result = board.stream()
			.map(m -> new BoardResponse(m.getId(), m.getTitle(), m.getContent(), m.getView(), m.getLikes()))
			.collect(Collectors.toList());

		return result;
	}

	@Transactional(readOnly = true)
	public BoardResponse getOneBoard(Long id) {

		BoardEntity board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

		return BoardResponse.builder().board(board).build();

	}

	@Transactional
	public UpdateResponse updateBoard(Long id, UpdateRequest request) {

		BoardEntity board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());

		BoardEntity updateMember = new BoardEntity(id, request.getTitle(), request.getContent(),
			null, null, null);

		boardRepository.save(updateMember);

		return UpdateResponse.builder().board(updateMember).build();

	}

	@Transactional
	public void deleteBoard(Long id) {
		if (!boardRepository.existsById(id)) {
			throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
		}
		boardRepository.deleteById(id);

	}

}
