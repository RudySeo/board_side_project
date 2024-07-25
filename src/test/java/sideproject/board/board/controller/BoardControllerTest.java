package sideproject.board.board.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import sideproject.board.board.Sort;
import sideproject.board.board.controller.dto.requests.CreateBoardRequest;
import sideproject.board.board.controller.dto.requests.UpdateRequest;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.service.BoardService;
import sideproject.board.product.controller.dto.requrests.CreateProduct;


@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BoardService boardService;

	@DisplayName("게시판 아이디불러오기 테스트")
	@Test
	public void getTest() throws Exception {
		// Given

		Board board = Board.builder()
			.id(1L)
			.title("안녕하세요")
			.build();

		Long boardId = 1L;
		given(boardService.getOneBoard(1L)).willReturn(board);

		// When & Then
		mockMvc.perform(get("/board/{id}", boardId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.title").exists())
			.andExpect(jsonPath("$.content").exists())
			.andExpect(jsonPath("$.view").exists())
			.andExpect(jsonPath("$.like").exists())
			.andExpect(jsonPath("$.price").exists())
			.andDo(print());

		verify(boardService).getOneBoard(1L);
	}

	@DisplayName("게시판 전체불러오기 테스트")
	@Test
	public void getAllTest() throws Exception {
		// Given
		Board board1 = Board.builder()
			.id(1L)
			.title("안녕하세요")
			.build();
		Board board2 = Board.builder()
			.id(2L)
			.title("안녕하세요")
			.build();
		List<Board> boardList = Arrays.asList(board1, board2);

		given(boardService.getAllBoard(null, Sort.LIKE)).willReturn(boardList);
		// When & Then

		mockMvc.perform(get("/board")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$[0].id").exists())
			.andExpect(jsonPath("$[0].title").exists())
			.andExpect(jsonPath("$[0].content").exists())
			.andExpect(jsonPath("$[0].view").exists())
			.andExpect(jsonPath("$[0].like").exists())
			.andExpect(jsonPath("$[0].price").exists())
			.andDo(print());

		verify(boardService).getAllBoard(null, Sort.LIKE);
	}

	@DisplayName("게시판 추가 테스트")
	@Test
	public void bordPost() throws Exception {
		// Given
		CreateProduct createProduct = new CreateProduct(null, 100L, "서울");
		CreateBoardRequest request = new CreateBoardRequest("테스트", "안녕하세요", "안녕", createProduct);
		String json = new ObjectMapper().writeValueAsString(request);

		given(boardService.saveBoard(request, "홍길동")).willReturn(request.toEntity());
		// When & Then

		mockMvc.perform(post("/board")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.title").exists())
			.andExpect(jsonPath("$.content").exists())
			.andDo(print());

		verify(boardService).saveBoard(request, "홍길동");
	}

	@DisplayName("게시판 수정하기 테스트")
	@Test
	public void bordUpdate() throws Exception {
		// Given

		UpdateRequest request = new UpdateRequest(1L, "안녕하세요", "안녕", 200L);
		String json = new ObjectMapper().writeValueAsString(request);
		Board board = Board.builder()
			.id(1L)
			.title("안녕하세요")
			.build();

		given(boardService.updateBoard(1L, request)).willReturn(board);

		Long boardId = 1L;

		// When & Then
		mockMvc.perform(put("/board/{id}", boardId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.title").exists())
			.andExpect(jsonPath("$.content").exists())
			.andDo(print());

		verify(boardService).updateBoard(1L, request);
	}

	@DisplayName("게시판 삭제 테스트")
	@Test
	public void bordRemove() throws Exception {
		// Given
		Long boardId = 1L;

		// When & Then
		mockMvc.perform(delete("/board/{id}", boardId))
			.andExpect(status().isOk())
			.andDo(print());

		verify(boardService).deleteBoard(boardId);
	}
}