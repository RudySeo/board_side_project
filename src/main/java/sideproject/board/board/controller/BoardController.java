package sideproject.board.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.board.controller.dto.requests.CreateBoardRequest;
import sideproject.board.board.controller.dto.requests.boardResponse;
import sideproject.board.board.controller.dto.requests.createBoardResponse;
import sideproject.board.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@Builder
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/board")
	public createBoardResponse createBoard(@RequestBody CreateBoardRequest request) {

		createBoardResponse response = boardService.createBoard(request);
		return response;

	}

	@GetMapping("/board")
	public List<boardResponse> getAllBoard() {
		
		List<boardResponse> response = boardService.getAllBoard();
		return response;
	}

}
