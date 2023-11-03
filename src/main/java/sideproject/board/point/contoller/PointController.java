package sideproject.board.point.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.configuration.ThreadLocalContext;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.point.contoller.request.PointRequest;
import sideproject.board.point.domain.Entity.Point;
import sideproject.board.point.service.PointService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PointController {

	private final PointService pointService;

	@PostMapping("/point")
	public int charge(@RequestBody PointRequest request) {
		Member member = ThreadLocalContext.get();

		Point point = pointService.charge(member, request);

		return point.getMember().getMoney();
	}
}
