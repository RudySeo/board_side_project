package sideproject.board.order.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.configuration.ThreadLocalContext;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.order.contoller.dto.PaymentRequest;
import sideproject.board.order.service.OrderService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/payment")
	public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) {

		Member member = ThreadLocalContext.get();

		orderService.processPayment(request, member.getId());

		return ResponseEntity.ok("Payment successful");
	}
}
