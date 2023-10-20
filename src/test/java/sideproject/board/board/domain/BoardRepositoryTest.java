// package sideproject.board.board.domain;
//
// import static org.assertj.core.api.Assertions.*;
//
// import java.util.Optional;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
// import sideproject.board.board.domain.entity.Board;
//
//
// // @SpringBootTest
// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// class BoardRepositoryTest {
//
// 	@Autowired
// 	private BoardRepository boardRepository;
//
//
// 	@Test
// 	@DisplayName("게시판 저장하기")
// 	public void hello() {
// 		// Given
// 		Board board = new Board();
// 		board.setTitle("안녕하세요");
// 		board.setContent("확인");
// 		board.setPrice(20L);
//
// 		// When
// 		Board savedBoard = boardRepository.save(board);
//
// 		// Then
// 		assertThat(savedBoard.getId()).isNotNull();
// 		assertThat(savedBoard.getTitle()).isEqualTo("안녕하세요");
// 		assertThat(savedBoard.getContent()).isEqualTo("확인");
// 		assertThat(savedBoard.getPrice()).isEqualTo(20L);
// 	}
//
// 	@Test
// 	@DisplayName("업데이트 테스트")
// 	public void updateBoardTest() {
// 		// Given
// 		Board board = new Board();
// 		board.setTitle("안녕");
// 		board.setContent("확인중");
// 		board.setPrice(20L);
// 		boardRepository.save(board);
//
// 		// When
// 		Optional<Board> foundBoard = boardRepository.findById(board.getId());
// 		if (foundBoard.isPresent()) {
// 			Board updatedBoard = foundBoard.get();
// 			updatedBoard.setTitle("안녕하세요");
// 			updatedBoard.setContent("확인");
// 			updatedBoard.setPrice(20L);
// 			boardRepository.save(updatedBoard);
// 		}
//
// 		// Then
// 		Optional<Board> updatedBoard = boardRepository.findById(board.getId());
// 		assertThat(updatedBoard).isPresent();
// 		assertThat(updatedBoard.get().getTitle()).isEqualTo("안녕하세요");
// 		assertThat(updatedBoard.get().getContent()).isEqualTo("확인");
// 	}
//
//
// 	@Test
// 	@DisplayName("게시판 하나만 불러오기 테스트")
// 	public void testFindBoardById() {
// 		// Given
// 		Board board = new Board();
// 		board.setTitle("안녕하세요");
// 		board.setContent("확인");
// 		boardRepository.save(board);
//
// 		// When
// 		Optional<Board> foundBoard = boardRepository.findById(board.getId());
//
// 		// Then
// 		assertThat(foundBoard).isPresent();
// 		assertThat(foundBoard.get().getTitle()).isEqualTo("안녕하세요");
// 	}
//
// 	@Test
// 	@DisplayName("삭제 테스트")
// 	public void deleteBoardTest() {
// 		// Given
// 		Board board = new Board();
// 		board.setId(1L);
// 		board.setTitle("안녕");
// 		board.setContent("확인중");
// 		board.setPrice(20L);
// 		boardRepository.save(board);
//
// 		// When
// 		boardRepository.deleteById(1L);
//
// 		// Then
// 		Optional<Board> deletedBoard = boardRepository.findById(board.getId());
// 		assertThat(deletedBoard).isNotPresent();
// 	}
//
// }