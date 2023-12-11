package sideproject.board.point.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.time.LocalDateTime;

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

		Member updateMember = Member.builder()
			.id(findMember.getId())
			.money(findMember.getMoney() + request.getAmount())
			.build();
		
		memberRepository.save(updateMember);

		PointHistory point = PointHistory.builder()
			.amount(request.getAmount())
			.member(member)
			.chargeTime(time)
			.build();
		log.info(findMember.getMoney() + "잔액환인중@@!!");

		return pointRepository.save(point);
	}

	private void simulateProcessingTime() {
		try {
			// 처리 시간을 시뮬레이션합니다.
			Thread.sleep(5000); // 5초
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
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
