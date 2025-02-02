package sideproject.board.member.contoller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.requests.LoginMemberRequest;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.contoller.responses.MemberResponse;
import sideproject.board.member.contoller.responses.UpdateMemberResponse;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Builder
public class MemberController {

	private final MemberService memberService;


	@PostMapping("/user")
	public MemberResponse signUp(
		@RequestBody @Valid CreateMemberRequest request) {

		Member member = memberService.signUp(request.toEntity());

		return MemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.name(member.getName())
			.age(member.getAge())
			.money(member.getMoney())
			.build();
	}

	@PostMapping("/login")
	public String login(
		@RequestBody @Valid LoginMemberRequest request) {

		return memberService.login(request);
	}

	@GetMapping("/user")
	public List<MemberResponse> getAllMember() {
		List<Member> member = memberService.getAllMember();

		return member.stream()
			.map(m -> new MemberResponse(m.getId(), m.getEmail(), m.getName(), m.getAge(), m.getMoney()))
			.collect(Collectors.toList());
	}


	@GetMapping("/user/{id}")
	public MemberResponse getOneMember(@PathVariable Long id) {

		Member member = memberService.getMemberById(id);

		return MemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.name(member.getName())
			.age(member.getAge())
			.money(member.getMoney())
			.build();

	}

	@PutMapping("/user/{id}")
	public UpdateMemberResponse updateMember(@PathVariable Long id,
		@RequestBody UpdateMemberRequest request) {

		Member member = memberService.updateMember(id, request);

		return UpdateMemberResponse.builder().member(member).build();
	}

	@DeleteMapping("/user/{id}")
	public boolean delete(@PathVariable Long id) {

		memberService.deleteMember(id);

		return true;
	}

}
