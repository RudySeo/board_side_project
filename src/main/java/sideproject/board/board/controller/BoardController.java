package sideproject.board.board.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.board.controller.dto.requests.CreateBoardRequest;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.controller.dto.responses.BoardResponse;
import sideproject.board.board.controller.dto.responses.UpdateResponse;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.service.BoardService;
import sideproject.board.global.exception.configuration.ThreadLocalContext;
import sideproject.board.member.domain.Entity.Member;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/board")
	public BoardResponse saveBoard(@RequestBody CreateBoardRequest request) {
		Member memberLocal = ThreadLocalContext.get();

		Board board = boardService.saveBoard(request.toEntity(), memberLocal.getName());

		return BoardResponse.builder().board(board).build();
	}

	@GetMapping("/board")
	public List<BoardResponse> getAllBoard() {

		List<Board> board = boardService.getAllBoard();

		List<BoardResponse> response = board.stream()
			.map(
				m -> new BoardResponse(m.getId(), m.getWriter(), m.getTitle(), m.getContent(),
					m.getView(), m.getLike(), m.getPrice()))
			.collect(Collectors.toList());

		return response;
	}

	@GetMapping("/board/{id}")
	public BoardResponse getOneBoard(@PathVariable Long id) {

		Board board = boardService.getOneBoard(id);

		return BoardResponse.builder().board(board).build();
	}

	@PutMapping("/board/{id}")
	public UpdateResponse updateBoard(@PathVariable Long id, @RequestBody UpdateRequest request) {

		Board board = boardService.updateBoard(id, request);

		return UpdateResponse.builder().board(board).build();
	}

	@DeleteMapping("/board/{id}")
	public String deleteBoard(@PathVariable Long id) {

		boardService.deleteBoard(id);

		return "게시물이 삭제되었습니다.";
	}
}
