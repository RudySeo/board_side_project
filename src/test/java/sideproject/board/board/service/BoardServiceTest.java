package sideproject.board.board.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.domain.BoardRepository;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.exception.BadRequestException;

@SpringBootTest
class BoardServiceTest {

	@Autowired
	private BoardService boardService;

	@MockBean
	private BoardRepository boardRepository;

	@DisplayName("서비스 게시판 글쓰기 테스트")
	@Test
	public void saveBoardTest() {
		// Given
		Board board = new Board(1L, "안녕하세요", "크크크크", 20L, 10L, 200L);
		given(boardRepository.save(board)).willReturn(board);

		// When
		Board savedBoard = boardService.saveBoard(board);

		// Then
		assertNotNull(savedBoard);
		assertEquals("안녕하세요", savedBoard.getTitle());
		assertEquals("크크크크", savedBoard.getContent());
	}

	@DisplayName("서비스 게시판 업데이트 테스트")
	@Test
	public void updateBoardTest() {
		// Given
		Board board = new Board(1L, "안녕하세요", "크크크크", 20L, 10L, 200L);
		given(boardRepository.findById(1L)).willReturn(Optional.of(board));
		UpdateRequest request = new UpdateRequest(1L, "헬로", "크크크", 100L);

		// When
		Board savedBoard = boardService.updateBoard(1L, request);
		savedBoard.update(request.getId(), request.getTitle(), request.getContent(), request.getPrice());

		// Then
		assertNotNull(savedBoard);
		assertEquals("헬로", savedBoard.getTitle());
		assertEquals("크크크", savedBoard.getContent());
	}

	@DisplayName("서비스 게시판 전부 불러오기 테스트")
	@Test
	public void getAllBoardTest() {
		// Given
		List<Board> boardList = List.of(new Board(), new Board());
		given(boardRepository.findAll()).willReturn(boardList);

		// When
		List<Board> resultBoardList = boardService.getAllBoard();

		// Then
		assertEquals(boardList.size(), resultBoardList.size());
	}

	@DisplayName("서비스 게시판 하나만 불러오기 테스트")
	@Test
	public void getOneBoardTest() {
		// Given
		Board board = new Board(1L, "안녕하세요", "크크크크", 20L, 10L, 200L);

		given(boardRepository.findById(1L)).willReturn(Optional.of(board));

		// When
		Board resultBoard = boardService.getOneBoard(1L);

		// Then
		assertNotNull(resultBoard);
		assertEquals("안녕하세요", resultBoard.getTitle());
		assertEquals("크크크크", resultBoard.getContent());
	}

	@DisplayName("서비스 게시판 삭제 테스트")
	@Test
	public void deleteBoardTest() {
		// Given
		given(boardRepository.existsById(1L)).willReturn(false);

		// deleteBoard 메서드 호출
		assertThrows(BadRequestException.class, () -> boardService.deleteBoard(1L));
	}

}