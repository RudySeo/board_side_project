package sideproject.board.order.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sideproject.board.order.contoller.dto.PaymentRequest;
import sideproject.board.order.domain.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	public void processPayment(PaymentRequest paymentRequest) {
		
	}
}
