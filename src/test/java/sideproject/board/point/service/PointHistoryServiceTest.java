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
		// Arrange
		Long memberId = 1L;
		int initialMoney = 0;
		int amount = 100;
		int numberOfThreads = 1;

		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		Member member = Member.builder()
			.id(memberId)
			.money(initialMoney)
			.build();
		when(memberRepository.findById(eq(memberId))).thenReturn(Optional.of(member));

		PointHistory pointHistory = PointHistory.builder()
			.amount(amount)
			.member(member)
			.chargeTime(LocalDateTime.now())
			.build();

		when(pointRepository.save(any(PointHistory.class))).thenReturn(pointHistory);
		PointHistory[] results = new PointHistory[numberOfThreads];

		for (int i = 0; i < numberOfThreads; i++) {
			int index = i; // create a separate variable inside the loop
			service.execute(() -> {
				results[index] = pointHistoryService.charge(memberId, amount);
				latch.countDown();
			});
		}

		latch.await();

		// Assert
		verify(memberRepository, times(numberOfThreads)).findById(eq(memberId));
		verify(memberRepository, times(numberOfThreads)).save(any(Member.class));
		verify(pointRepository, times(numberOfThreads)).save(any(PointHistory.class));

		for (int i = 0; i < numberOfThreads; i++) {
			System.out.println(amount + "======== 잔액확인");
			System.out.println(results[0].getAmount() + "======== 잔액확인");

			System.out.println(amount + "======== 잔액확인");
			// System.out.println(results[1].getAmount() + "======== 잔액확인");

			assertEquals(amount, results[i].getAmount());
			assertEquals(initialMoney + amount, results[i].getMember().getMoney());
		}
	}
}