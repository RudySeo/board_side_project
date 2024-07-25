package sideproject.board.order.service;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.order.contoller.dto.PaymentRequest;
import sideproject.board.order.domain.OrderRepository;
import sideproject.board.product.domain.Entity.Product;
import sideproject.board.product.domain.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;

	@Mock
	MemberRepository memberRepository;

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	OrderService orderService;

	PaymentRequest paymentRequest;
	Member saveMember;
	Product saveProduct;

	@BeforeEach
	void setUp() {
		PaymentRequest paymentRequest = new PaymentRequest(1L, 100L, true);

		Member saveMember = Member.builder()
			.id(1L)
			.name("홍길동")
			.build();

		Product saveProduct = Product.builder()
			.id(1L)
			.price(100L)
			.build();

	}

	@Test
	@DisplayName("processPayment")
	void processPayment() {

		//given
		when(memberRepository.findById(saveMember.getId())).thenReturn(Optional.of(saveMember));
		when(productRepository.findById(saveProduct.getId())).thenReturn(Optional.of(saveProduct));

		//when
		orderService.processPayment(paymentRequest, saveMember.getId());

		//then
		verify(memberRepository).findById(saveMember.getId());
		verify(productRepository).findById(saveProduct.getId());
		verifyNoMoreInteractions(memberRepository, productRepository);
	}
}