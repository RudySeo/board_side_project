package sideproject.board.product.service;

import static sideproject.board.global.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sideproject.board.board.domain.entity.Board;
import sideproject.board.board.domain.repository.BoardRepository;
import sideproject.board.global.exception.ClientException;
import sideproject.board.product.domain.Entity.Product;
import sideproject.board.product.domain.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final BoardRepository boardRepository;


	public Product saveProduct(Long boardId, Product request) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new ClientException(NOT_FOUND_BOARD_ID));

		Product product = request.create(request);

		return productRepository.save(product);

	}
}
