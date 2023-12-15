package sideproject.board.point.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.point.contoller.request.PointRequest;
import sideproject.board.point.domain.repository.PointRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PointHistoryServiceTest {

	@Mock
	private PointRepository pointRepository;

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private PointHistoryService pointHistoryService;

	private LocalDateTime time;

	@BeforeEach
	void setUp() {
		time = LocalDateTime.now();
	}

	@Test
	@DisplayName("충전 테스트 코드")
	void testCharge() throws InterruptedException {
		// Given
		int initialAmount = 50;
		int chargeAmount = 30;
		int numberOfThreads = 2;

		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		PointRequest request = new PointRequest(chargeAmount);


		Member member = Member.builder()
			.email("test@gmail.com")
			.password("1111")
			.name("동권")
			.age(25)
			.money(initialAmount + chargeAmount)
			.build();

		System.out.println(member + "   확인중22222");

		// When
		// service.execute(() -> {
		// 	pointHistoryService.charge(member, request);
		// 	latch.countDown();
		// });

		// service.execute(() -> {
		// 	pointHistoryService.charge(member, request);
		// 	latch.countDown();
		// });
		latch.await();


		verify(pointHistoryService, times(2)).charge(eq(member), eq(request));

		Member updatedMember = memberRepository.findByEmail(member.getEmail()).orElseThrow();
		assertEquals(initialAmount + (chargeAmount * 2), updatedMember.getMoney());
	}
}