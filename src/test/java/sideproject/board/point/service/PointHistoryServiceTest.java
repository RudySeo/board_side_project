package sideproject.board.point.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.point.domain.Entity.PointHistory;
import sideproject.board.point.domain.repository.PointRepository;

@SpringBootTest
	// @ExtendWith(MockitoExtension.class)
class PointHistoryServiceTest {

	@Mock
	private PointRepository pointRepository;

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private PointHistoryService pointHistoryService;

	@Test
	@DisplayName("동시성 문제 테스트 코드 작성")
	public void testCharge23() throws InterruptedException {
		// given
		Long testID = 1L;
		int initialMoney = 0;
		int amount = 1;
		int numberOfThreads = 100;

		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		Member member = Member.builder()
			.id(testID)
			.money(initialMoney)
			.build();
		when(memberRepository.findById(eq(testID))).thenReturn(Optional.of(member));

		PointHistory pointHistory = PointHistory.builder()
			.id(testID)
			.amount(amount)
			.member(member)
			.chargeTime(LocalDateTime.now())
			.build();

		when(pointRepository.save(any(PointHistory.class))).thenReturn(pointHistory);
		PointHistory[] results = new PointHistory[numberOfThreads];

		//When
		for (int i = 0; i < numberOfThreads; i++) {
			service.execute(() -> {
				pointHistoryService.charge(testID, amount);
				latch.countDown();
			});
		}

		latch.await();

		//Then

		Member findMember = memberRepository.findById(testID).orElseThrow();
		System.out.println(findMember.getMoney() + "====돈확인");

		assertEquals(findMember.getMoney(), initialMoney + numberOfThreads * (amount));

	}
}