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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RedissonClient;

import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.point.domain.Entity.PointHistory;
import sideproject.board.point.domain.repository.PointRepository;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
class PointHistoryServiceTest {


	@Mock
	private PointRepository pointRepository;
	@Mock
	private MemberRepository memberRepository;

	@Mock
	private RedissonClient redissonClient;
	@InjectMocks
	private PointHistoryService pointHistoryService;


	@Test
	@DisplayName("동시성 문제 테스트 코드 작성")
	public void testCharge23() throws InterruptedException {
		// given
		Long testID = 1L;
		int initialMoney = 0;
		int amount = 1;
		int numberOfThreads = 10;

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
		//When
		for (int i = 0; i < numberOfThreads; i++) {
			service.execute(() -> {
				try {
					pointHistoryService.charge(testID, amount);
					System.out.println("성공했습니다");
				} catch (Exception e) {
					System.out.println("오류입니다");

				}
				latch.countDown();
			});
		}

		latch.await();

		//Then

		Member findMember = memberRepository.findById(testID).orElseThrow();


		assertEquals(findMember.getMoney(), initialMoney + numberOfThreads * (amount));

	}

	@Test
	@DisplayName("레드락 테스트")
	public void testConcurrentCharge() throws InterruptedException {
		// given
		Long testId = 1L;
		int initialMoney = 1000;
		int amount = -1;
		int numberOfThreads = 1000;

		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);


		Member mockMember = new Member();
		mockMember.setId(testId);
		mockMember.setMoney(initialMoney);

		when(memberRepository.findById(eq(testId))).thenReturn(Optional.of(mockMember));

		// When
		for (int i = 0; i < numberOfThreads; i++) {
			service.submit(() -> {
				try {
					pointHistoryService.charge(testId, amount);
					// System.out.println("성공");
				} catch (Exception e) {
					System.out.println("실패");
				}
				latch.countDown();
			});
		}

		latch.await();

		// Then
		Member findMember = memberRepository.findById(testId).orElseThrow();
		System.out.println(findMember.getMoney() + " 잔액확인");
		assertEquals(initialMoney + numberOfThreads * amount, findMember.getMoney());

		// Verify
		verify(memberRepository, times(numberOfThreads)).save(mockMember);
		verify(pointRepository, times(numberOfThreads)).save(any(PointHistory.class));
	}
}