package sideproject.board.point.contoller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.configuration.ThreadLocalContext;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.point.contoller.request.PointRequest;
import sideproject.board.point.contoller.responses.PointHistoryResponse;
import sideproject.board.point.domain.Entity.PointHistory;
import sideproject.board.point.service.PointHistoryService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PointHistoryController {

	private final PointHistoryService pointHistoryService;

	@PostMapping("/point")
	public PointHistoryResponse charge(@RequestBody PointRequest request) {
		Member member = ThreadLocalContext.get();

		PointHistory point = pointHistoryService.charge(member, request);


		return PointHistoryResponse.builder()
			.balance(point.getMember().getMoney() + point.getAmount())
			.chargeAmount(point.getAmount())
			.build();
	}

	@GetMapping("/point")
	public List<PointHistoryResponse> searchPointList() {
		Member member = ThreadLocalContext.get();

		List<PointHistory> point = pointHistoryService.searchPointList(member);

		List<PointHistoryResponse> responses = point.stream()
			.map(i -> new PointHistoryResponse(i.getMember().getName(), i.getMember().getMoney(), i.getAmount(),
				i.getChargeTime()))
			.collect(Collectors.toList());
		
		return responses;
	}
}
