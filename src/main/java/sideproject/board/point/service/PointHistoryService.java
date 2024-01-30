package sideproject.board.point.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.ClientException;
import sideproject.board.global.exception.ErrorCode;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.point.domain.Entity.PointHistory;
import sideproject.board.point.domain.repository.PointRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointHistoryService {

	private final PointRepository pointRepository;

	private final MemberRepository memberRepository;

	private final RedissonClient redissonClient;

	LocalDateTime time = LocalDateTime.now();

	@Transactional
	public PointHistory charge(Long id, int amount) {
		final String lockName = id + ":lock";
		RLock lock = redissonClient.getLock(lockName);
		try {
			boolean available = lock.tryLock(3, 1, TimeUnit.SECONDS); // lock 획득
			if (!available) {
				System.out.println("lock 획득 실패");
			}

			//findAndLockById 수정
			Member findMember = memberRepository.findById(id)
				.orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER_ID));

			findMember.addMoney(amount);

			memberRepository.save(findMember);

			PointHistory point = PointHistory.builder()
				.amount(amount)
				.member(findMember)
				.chargeTime(time)
				.build();

			return pointRepository.save(point);


		} catch (InterruptedException e) {
			throw new RuntimeException(e);

		} finally {
			lock.unlock(); //lock 해제
		}
	}

	@Transactional
	public Page<PointHistory> searchPointList(Member member, Pageable pageable) {

		Member findMember = memberRepository.findByEmail(member.getEmail())
			.orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER_ID));

		return pointRepository.findAllByMemberId(member.getId(), pageable);
	}

	@Transactional
	public PointHistory searchUserPoint(Long id) {

		return pointRepository.findById(id).orElseThrow(() -> new ClientException(NOT_FOUND_POINT_HISTORY_ID));
	}
}
