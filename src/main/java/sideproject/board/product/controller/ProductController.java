package sideproject.board.product.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.product.controller.dto.requrests.CreateProduct;
import sideproject.board.product.controller.dto.responses.ProductResponse;
import sideproject.board.product.domain.Entity.Product;
import sideproject.board.product.service.ProductService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/product{boardId}")
	public ProductResponse saveProduct(@PathVariable Long boardId, @RequestBody CreateProduct request) {

		Product product = productService.saveProduct(boardId, request.toEntity());

		return ProductResponse.builder()
			.id(product.getId())
			.type(product.getType())
			.price(product.getPrice())
			.stock(product.isStock())
			.location(product.getLocation())
			.build();

	}



}
