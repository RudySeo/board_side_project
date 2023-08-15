package sideproject.board.contoller;

import org.springframework.web.bind.annotation.*;
import sideproject.board.model.entity.Board;
import sideproject.board.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {

    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @PostMapping("/board")
    Board createBoard(@RequestBody Board BoardDto ){
        return  boardRepository.save(BoardDto);
    }

    @GetMapping("/boards")
    List<Board> getAllBoard(){
        return  boardRepository.findAll();
    }

    @GetMapping("/board/{id}")
    Optional<Board> getBoardById(@PathVariable Long id){
        return boardRepository.findById(id);
    }
    
    @DeleteMapping("/board/{id}")
    String deleteBoard(@PathVariable Long id){
        if (!boardRepository.existsById(id)){
            String 오류입니다 = "오류입니다";
            return 오류입니다;
        }
        boardRepository.deleteById(id);
        return id + "삭제 완료 했습니다";
    }



}
