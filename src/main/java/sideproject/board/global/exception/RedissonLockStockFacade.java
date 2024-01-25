package sideproject.board.global.exception;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sideproject.board.point.domain.Entity.PointHistory;
import sideproject.board.point.service.PointHistoryService;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {

	private final RedissonClient redissonClient;
	private final PointHistoryService pointHistoryService;

	public PointHistory decrease(Long id, int amount) {
		RLock lock = redissonClient.getLock(id.toString());

		try {
			boolean available = lock.tryLock(5, 1, TimeUnit.SECONDS); // lock 획득
			if (!available) {
				System.out.println("lock 획득 실패");
				return null;
			}
			pointHistoryService.charge(id, amount);

		} catch (InterruptedException e) {
			throw new RuntimeException(e);

		} finally {
			lock.unlock(); //lock 해제
		}
		return null;
	}
}
