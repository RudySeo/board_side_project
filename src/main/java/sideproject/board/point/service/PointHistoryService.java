package sideproject.board.point.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PointHistoryService {

	private final PointRepository pointRepository;

	private final MemberRepository memberRepository;

	LocalDateTime time = LocalDateTime.now();

	@Transactional
	public PointHistory charge(Member member, PointRequest request) {

		Member findMember = memberRepository.findByEmail(member.getEmail())
			.orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER_ID));

		findMember.charge(request.getAmount());
		memberRepository.save(findMember);

		PointHistory point = PointHistory.builder()
			.amount(request.getAmount())
			.member(member)
			.chargeTime(time)
			.build();

		return pointRepository.save(point);
	}

	@Transactional
	public List<PointHistory> searchPointList(Member member) {

		Member findMember = memberRepository.findByEmail(member.getEmail())
			.orElseThrow(() -> new ClientException(ErrorCode.NOT_FOUND_MEMBER_ID));

		return pointRepository.findAllByMemberId(findMember.getId());
	}

	@Transactional
	public PointHistory searchUserPoint(Long id) {

		return pointRepository.findById(id).orElseThrow(() -> new ClientException(NOT_FOUND_BOARD_ID));
	}
}
