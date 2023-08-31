package sideproject.board.member;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import sideproject.board.member.requests.CreateMemberRequest;
import sideproject.board.member.responses.MemberAllResponse;
import sideproject.board.service.MemberService;

@RestController
@RequiredArgsConstructor

public class Contoller {

	private final Service service;
	private final MemberService memberService;

	@PostMapping("/user")
	public boolean createMember(@RequestBody @Valid CreateMemberRequest createMemberRequest) {

		return service.newMember(createMemberRequest);
	}

	@GetMapping("/users")
	public MemberAllResponse allMembers() {
		List<Entity> findMember = service.getAllMember();
		List<MemberDto1> collect = findMember
			.stream()
			.map(m -> new MemberDto1(m.getEmail(), m.getName(), m.getAge()))
			.collect(Collectors.toList());
		return new MemberAllResponse<>(collect);

	}

	@Data
	@AllArgsConstructor
	public static class MemberDto1 {
		private String email;
		private String name;
		private int age;
	}

	//
	// @PutMapping("/user/{id}")
	// public Boolean upadateMember(@RequestBody MemberDto memberDto, @PathVariable Long id) {
	//
	// 	try {
	// 		memberService.update(id, memberDto);
	// 		return true;
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 		return false;
	// 	}
	// }
	//
	// @GetMapping("/users")
	// public MemberController.result allMembers() {
	// 	List<Member> findMember = memberService.getAllMember();
	// 	List<MemberController.MemberDto1> collect = findMember
	// 		.stream()
	// 		.map(m -> new MemberController.MemberDto1(m.getName(), m.getMemberId(), m.getAge()))
	// 		.collect(Collectors.toList());
	// 	return new MemberController.result<>(collect);
	// }
	//
	// @Data
	// @AllArgsConstructor
	// static class result<T> {
	// 	private T data;
	//
	// }
	//

}
