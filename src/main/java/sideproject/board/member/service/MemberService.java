package sideproject.board.member.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.member.domain.Entity.Role;

@RequiredArgsConstructor
@Builder
@Slf4j
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	private final BCryptPasswordEncoder bcrypt;

	@Transactional
	public Member signUp(Member request) {

		Member member = Member.builder()
			.email(request.getEmail())
			.password(bcrypt.encode(request.getPassword()))
			.name(request.getName())
			.age(request.getAge())
			.status(Role.USER)
			.build();

		return memberRepository.save(member);
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
