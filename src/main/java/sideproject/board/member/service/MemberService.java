package sideproject.board.member.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.contoller.requests.LoginMemberRequest;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.member.domain.Entity.Role;
import sideproject.board.utils.JwtUtil;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;

	private final BCryptPasswordEncoder bcrypt;

	@Value("${jwt.secretKey}")
	private String secretKey;

	Long expireTime = 100 * 60 * 60L;
	LocalDate currentTime = LocalDate.now();

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


		Member member = memberRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new ClientException(USER_NOT_FOUND));

		if (!bcrypt.matches(request.getPassword(), member.getPassword())) {
			throw new ClientException(USER_NOT_FOUND);
		}

		if (member.getLastLoginDate() == null) {
			member.setPoint(1000);
		}

		LocalDate loginTime = member.setLastLoginDate(currentTime);
		if (loginTime.isAfter(currentTime)) {
			member.setPoint(1000);
		}


		return JwtUtil.createJwt(member.getEmail(), secretKey, expireTime);
	}

	@Transactional(readOnly = true)
	public List<Member> getAllMember() {

		return memberRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Member getMemberById(Long id) {

		return memberRepository.findById(id)
			.orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));
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
