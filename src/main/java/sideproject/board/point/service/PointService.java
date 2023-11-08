package sideproject.board.point.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.ClientException;
import sideproject.board.global.exception.ErrorCode;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.point.contoller.request.PointRequest;
import sideproject.board.point.domain.Entity.PointHistory;
import sideproject.board.point.domain.repository.PointRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {

	private final PointRepository pointRepository;

	private final MemberRepository memberRepository;

	public PointHistory charge(Member member, PointRequest request) {

		Member findMember = memberRepository.findByEmail(member.getEmail())
			.orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER_ID));

		log.info(request.getAmount() + "금액확인");
		//충전 금액과
		findMember.charge(request.getAmount());
		memberRepository.save(findMember);

		//충전금액 시간 저장\
		PointHistory point = PointHistory.builder()
			.amount(request.getAmount())
			.member(member)
			.build();

		return pointRepository.save(point);
	}

}
