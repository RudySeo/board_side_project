package sideproject.board.member.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.contoller.requests.LoginMemberRequest;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.member.domain.Entity.Role;
import sideproject.board.utils.JwtUtil;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	private final BCryptPasswordEncoder bcrypt;

	@Value("${jwt.secretKey}")
	private String secretKey;

	private Long expireTime = 100 * 60 * 60L;

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

	@Transactional
	public String login(LoginMemberRequest request) {

		Member findEmail = memberRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new ClientException(USER_NOT_FOUND));

		if (!bcrypt.matches(request.getPassword(), findEmail.getPassword()))
			throw new ClientException(USER_NOT_FOUND);

		String accessToken = JwtUtil.createJwt(findEmail.getEmail(), secretKey, expireTime);

		return accessToken;
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
