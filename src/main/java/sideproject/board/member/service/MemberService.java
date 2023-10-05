package sideproject.board.member.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;

@RequiredArgsConstructor
@Builder
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public Member signUp(Member request) {


		Member saveEntity = memberRepository.save(request);

		return saveEntity;
	}

	@Transactional(readOnly = true)
	public List<Member> getAllMember() {

		return memberRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Member getMemberById(Long id) {

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

		return member;
	}

	@Transactional
	public Member updateMember(Long id, UpdateMemberRequest request) {

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

		member.update(id, request.getName(), request.getAge());

		return member;

	}



	@Transactional
	public void deleteMember(Long id) {

		if (!memberRepository.existsById(id)) {
			throw new ClientException(NOT_FOUND_BOARD_ID);
		}
		memberRepository.deleteById(id);
	}
}
