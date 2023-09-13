package sideproject.board.board.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.board.controller.dto.requests.CreateBoardRequest;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.controller.dto.responses.BoardResponse;
import sideproject.board.board.controller.dto.responses.CreateBoardResponse;
import sideproject.board.board.controller.dto.responses.UpdateResponse;
import sideproject.board.board.domain.entity.BoardEntity;
import sideproject.board.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@Builder
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/board")
	public CreateBoardResponse createBoard(@RequestBody CreateBoardRequest request) {

		BoardEntity board = boardService.createBoard(request.toEntity());

		return CreateBoardResponse.builder().board(board).build();
	}

	@GetMapping("/board")
	public List<BoardResponse> getAllBoard() {

		List<BoardEntity> board = boardService.getAllBoard();

		List<BoardResponse> response = board.stream()
			.map(m -> new BoardResponse(m.getId(), m.getTitle(), m.getContent(), m.getView(), m.getLikes()))
			.collect(Collectors.toList());

		return response;
	}

	@GetMapping("/board/{id}")
	public BoardResponse getOneBoard(@PathVariable Long id) {

		BoardEntity board = boardService.getOneBoard(id);

		return BoardResponse.builder().board(board).build();
	}

	@PutMapping("/board/{id}")
	public UpdateResponse updateBoard(@PathVariable Long id, @RequestBody UpdateRequest request) {

		BoardEntity board = boardService.updateBoard(id, request);

		return UpdateResponse.builder().board(board).build();
	}

	@DeleteMapping("/board/{id}")
	public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
		try {
			boardService.deleteBoard(id);
			return ResponseEntity.ok("게시물이 삭제되었습니다.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을 수 없습니다.");
		}
	}
}
