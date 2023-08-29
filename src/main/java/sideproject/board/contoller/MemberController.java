package sideproject.board.contoller;

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
import sideproject.board.model.dto.MemberDto;
import sideproject.board.model.entity.Member;
import sideproject.board.service.MemberService;

@RestController
@RequiredArgsConstructor

public class MemberController {


	private final MemberService memberService;

	@PostMapping("/user")
	public Long createMember(@RequestBody @Valid MemberDto memberDto) {
		Member member = new Member();
		member.setMemberId(memberDto.getMemberId());
		member.setPassword(memberDto.getPassword());
		member.setName(memberDto.getName());
		member.setAge(memberDto.getAge());

		Long id = memberService.newMember(member);
		return id;
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
	public result allMembers() {
		List<Member> findMember = memberService.getAllMember();
		List<MemberDto1> collect = findMember
			.stream()
			.map(m -> new MemberDto1(m.getName(), m.getMemberId(), m.getAge()))
			.collect(Collectors.toList());
		return new result<>(collect);
	}

	@Data
	@AllArgsConstructor
	public static class result<T> {
		private T data;

	}

	@Data
	@AllArgsConstructor
	public static class MemberDto1 {
		private String memberId;
		private String name;
		private int age;
	}



}
