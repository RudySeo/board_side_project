package sideproject.board.order.service;

import static sideproject.board.global.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.order.contoller.dto.PaymentRequest;
import sideproject.board.order.domain.OrderRepository;
import sideproject.board.order.domain.entity.Order;
import sideproject.board.product.domain.Entity.Product;
import sideproject.board.product.domain.repository.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final MemberRepository memberRepository;

	private final ProductRepository productRepository;

	@Transactional
	public void processPayment(PaymentRequest request, Long memberId) {

		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

		if (member.getMoney() < request.getPrice()) {
			throw new ClientException(NOT_ENOUGH_MONEY);
		}

		Product findProduct = productRepository.findById(request.getProductId())
			.orElseThrow(() -> new ClientException(NOT_FOUND_PRODUCT_ID));
		log.info(findProduct.isStock() + "=====");

		if (!findProduct.isStock()) {
			throw new ClientException(PRODUCT_SOLD_OUT);
		}

		Order order = Order.builder()
			.member(member)
			.product(findProduct)
			.build();

		Product product = Product.checkProduct(findProduct);
		Member.payable(member.getMoney(), product.getPrice());

		orderRepository.save(order);
		memberRepository.save(member);
		productRepository.save(product);

	}
}
