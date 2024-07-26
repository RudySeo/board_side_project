package sideproject.board.member.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sideproject.board.member.contoller.requests.LoginMemberRequest;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;
import sideproject.board.utils.JwtUtil;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	MemberRepository memberRepository;

	@Mock
	BCryptPasswordEncoder bcrypt;

	@Mock
	JwtUtil jwtUtil;

	@InjectMocks
	MemberService memberService;

	Member saveMember;

	LoginMemberRequest loginMemberRequest;
	UpdateMemberRequest updateMemberRequest;

	@BeforeEach
	void setUp() {
		Member saveMember = Member.builder()
			.id(1L)
			.email("dskdm@naver.com")
			.password("1212")
			.build();

		LoginMemberRequest loginMemberRequest = new LoginMemberRequest("dskdm@naver.com", "1212");
		UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest("홍길동", 11);

	}

	@Test
	@DisplayName("회원가입")
	void signUp() {

		//given
		when(memberRepository.save(saveMember)).thenReturn(saveMember);

		//when
		Member member = memberService.signUp(saveMember);

		//then
		verify(memberRepository).save(saveMember);
	}

	@Test
	@DisplayName("로그인")
	void login() {

		//given
		String secretKey = "secretKey";
		Long expireTime = 100 * 60 * 6000000L;
		LocalDate currentTime = LocalDate.now();

		when(memberRepository.findByEmail(saveMember.getEmail())).thenReturn(Optional.of(saveMember));
		when(bcrypt.matches(loginMemberRequest.getPassword(), saveMember.getPassword())).thenReturn(true);

		//when
		String token = memberService.login(loginMemberRequest);

		//then
		verify(memberRepository).findByEmail(saveMember.getEmail());
		verify(bcrypt).matches(loginMemberRequest.getPassword(), saveMember.getPassword());
		assertNotNull(token);
	}

	@Test
	@DisplayName("유저전부 불러오기")
	void getAllMember() {

		//given
		List<Member> memberList = new ArrayList<>();
		memberList.add(saveMember);

		when(memberRepository.findAll()).thenReturn(memberList);
		//when
		List<Member> members = memberService.getAllMember();

		//then
		verify(memberRepository).findAll();
		assertNotNull(members);
		assertEquals(memberList.size(), members.size());
	}

	@Test
	@DisplayName("한명유저 불러오기")
	void getMemberById() {

		//given
		when(memberRepository.findById(saveMember.getId())).thenReturn(Optional.of(saveMember));

		//when
		Member member = memberService.getMemberById(saveMember.getId());

		//then
		verify(memberRepository).findById(saveMember.getId());
		assertNotNull(member);
		assertEquals(saveMember.getId(), member.getId());
	}

	@Test
	@DisplayName("유저 업데이트")
	void updateMember() {

		//given
		when(memberRepository.findById(saveMember.getId())).thenReturn(Optional.of(saveMember));
		when(memberRepository.save(saveMember)).thenReturn(saveMember);

		//when
		Member member = memberService.updateMember(1L, updateMemberRequest);

		//then
		verify(memberRepository).save(saveMember);
		assertNotNull(member);
		assertNotNull(member.getId());
		assertEquals(saveMember.getId(), member.getId());
	}

	@Test
	void deleteMember() {

		//given
		when(memberRepository.existsById(saveMember.getId())).thenReturn(true);
		
		//when
		memberService.deleteMember(saveMember.getId());

		//then
		verify(memberRepository).existsById(saveMember.getId());
		verify(memberRepository).deleteById(saveMember.getId());
	}
}