package sideproject.board.product.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.board.domain.repository.BoardRepository;
import sideproject.board.product.domain.Entity.Product;
import sideproject.board.product.domain.repository.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final BoardRepository boardRepository;


	public Product saveProduct(Product request) {


		Product product = request.create(request);
		return productRepository.save(product);

	}
}
