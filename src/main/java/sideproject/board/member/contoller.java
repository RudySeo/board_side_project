package sideproject.board.member;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import sideproject.board.contoller.MemberController;
import sideproject.board.member.requests.createMemberRequest;
import sideproject.board.model.dto.MemberDto;
import sideproject.board.model.entity.Member;
import sideproject.board.service.MemberService;

@RestController
@RequiredArgsConstructor

public class contoller {

	private final service _service;
	private final MemberService memberService;

	@PostMapping("/user")
	public @Valid createMemberRequest createMember(@RequestBody @Valid createMemberRequest _createMemberRequest) {
		// Member member = new Member();
		// member.setMemberId(memberDto.getMemberId());
		// member.setPassword(memberDto.getPassword());
		// member.setName(memberDto.getName());
		// member.setAge(memberDto.getAge());
		//
		// Long id = memberService.newMember(member);
		_service.newMember(_createMemberRequest);
		return _createMemberRequest;

	}

	@PutMapping("/user/{id}")
	public Boolean upadateMember(@RequestBody MemberDto memberDto, @PathVariable Long id) {

		try {
			memberService.update(id, memberDto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("/users")
	public MemberController.result allMembers() {
		List<Member> findMember = memberService.getAllMember();
		List<MemberController.MemberDto1> collect = findMember
			.stream()
			.map(m -> new MemberController.MemberDto1(m.getName(), m.getMemberId(), m.getAge()))
			.collect(Collectors.toList());
		return new MemberController.result<>(collect);
	}

	@Data
	@AllArgsConstructor
	static class result<T> {
		private T data;

	}

	@Data
	@AllArgsConstructor
	static class MemberDto1 {
		private String memberId;
		private String name;
		private int age;
	}
}
