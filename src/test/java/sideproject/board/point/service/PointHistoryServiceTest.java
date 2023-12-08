package sideproject.board.point.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sideproject.board.board.domain.entity.Board;
import sideproject.board.comment.model.entity.Comment;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.member.domain.Entity.Role;
import sideproject.board.point.contoller.request.PointRequest;
import sideproject.board.point.domain.Entity.PointHistory;
import sideproject.board.point.domain.repository.PointRepository;

@SpringBootTest
class PointHistoryServiceTest {

	@MockBean
	private PointRepository pointRepository;

	@MockBean
	private MemberRepository memberRepository; //5
	@InjectMocks
	private PointHistoryService pointHistoryService;



	@Test
	@DisplayName("동시성 테스트")
	public void testConcurrentCharge() throws InterruptedException {
		// Given
		int threadCount = 100;
		LocalDateTime time = LocalDateTime.now();
		List<Comment> comments = new ArrayList<>();
		List<Board> boards = new ArrayList<>();
		List<PointHistory> points = new ArrayList<>();
		PointRequest request = new PointRequest(1);

		Member member = new Member(1L, "test@example.com", "password", "John Doe", 25, 0, null, Role.USER,
			comments, boards, points);

		PointHistory point = PointHistory.builder()
			.amount(request.getAmount())
			.member(member)
			.chargeTime(time)
			.build();


		given(pointRepository.save(point)).willReturn(point);

		// When
		List<Future<Void>> response = runConcurrentThreads(threadCount, member, request);

		// Then
		assertNotNull(response);

		verify(memberRepository, times(threadCount)).save(any(Member.class));
	}

	private List<Future<Void>> runConcurrentThreads(int threadCount, Member member, PointRequest request) throws
		InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					pointHistoryService.charge(member, request);
				} finally {
					latch.countDown();
				}
			});
		}
		latch.await();
		return null;
	}

}