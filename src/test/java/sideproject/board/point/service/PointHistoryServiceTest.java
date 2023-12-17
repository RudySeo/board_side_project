package sideproject.board.point.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.board.global.exception.ClientException;
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
		Member member = new Member();
		member.setEmail("test1@naver.com");
		// Member.builder()
		// 	.id(2L)
		// 	.email("test1@naver.com")
		// 	.password("33333")
		// 	.name("강호")
		// 	.age(12)
		// 	.money(initialAmount)
		// 	.build();


		// given(memberRepository.save(member)).willReturn((member));
		given(memberRepository.save(member)).willReturn(member);
		given(memberRepository.findByEmail(member.getEmail())).willReturn(Optional.of(member));
		// given(pointRepository.save())

		// When

		service.execute(() -> {
			System.out.println(member.getEmail() + "====파라미터 확인");
			pointHistoryService.charge(member.getEmail(), request.getAmount());
			latch.countDown();

		});

		service.execute(() -> {
			pointHistoryService.charge(member.getEmail(), request.getAmount());
			latch.countDown();
		});

		latch.await();


		// verify(pointHistoryService, times(2)).charge(eq(member.getEmail()), eq(request.getAmount()));
		//
		// Member updatedMember = memberRepository.findById(member.getId()).orElseThrow();
		// assertEquals(initialAmount + (chargeAmount * 2), updatedMember.getMoney());
	}

	@Test
	@DisplayName("테스트3")
	void testCharge21() {
		// Arrange
		String email = "test@naver.com";
		int amount = 100;

		// 불필요한 스텁을 방지하기 위해 lenient 사용
		lenient().when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(ClientException.class, () -> {
			pointHistoryService.charge(email, amount);
		});

		// 회원이 존재하지 않으므로 save 메소드가 호출되지 않았는지 확인
		verify(memberRepository, Mockito.never()).save(any());
		verify(pointRepository, Mockito.never()).save(any());
	}
}