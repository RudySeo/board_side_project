package sideproject.board.member.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.common.CommonResponseEntity;
import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.contoller.responses.CreateMemberResponse;
import sideproject.board.member.contoller.responses.MemberListResponse;
import sideproject.board.member.contoller.responses.UpdateMemberResponse;
import sideproject.board.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Builder
public class MemberContoller {

	private final MemberService memberService;


	@PostMapping("/user")
	public CommonResponseEntity<CreateMemberResponse> createMember(
		@RequestBody @Valid CreateMemberRequest createMemberRequest) {

		CreateMemberResponse res = memberService.createMember(createMemberRequest);
		CommonResponseEntity<CreateMemberResponse> responseEntity = CommonResponseEntity.<CreateMemberResponse>builder()

			.result(true)
			.status(HttpStatus.CREATED)
			.data(res)
			.build();

		return responseEntity;
	}

	@GetMapping("/users")
	public CommonResponseEntity<List<MemberListResponse>> allMembers() {
		List<MemberListResponse> result = memberService.getAllMember();
		CommonResponseEntity<List<MemberListResponse>> responseEntity = CommonResponseEntity.<List<MemberListResponse>>builder()

			.result(true)
			.status(HttpStatus.OK)
			.data(result)
			.build();

		return responseEntity;

	}

	@GetMapping("/user/{id}")
	public CommonResponseEntity<MemberListResponse> getMember(@PathVariable Long id) {
		MemberListResponse result = memberService.getMemberById(id);

		CommonResponseEntity<MemberListResponse> responseEntity = CommonResponseEntity.<MemberListResponse>builder()
			.result(true)
			.status(HttpStatus.OK)
			.data(result)
			.build();

		return responseEntity;


	}

	@PutMapping("/user/{id}")
	public CommonResponseEntity<UpdateMemberResponse> updateMember(@PathVariable Long id,
		@RequestBody UpdateMemberRequest updateMemberRequest) {
		UpdateMemberResponse result = memberService.updateMember(id, updateMemberRequest);

		CommonResponseEntity<UpdateMemberResponse> responseEntity = CommonResponseEntity.<UpdateMemberResponse>builder()
			.result(true)
			.status(HttpStatus.OK)
			.data(result)
			.build();

		return responseEntity;


	}

	@DeleteMapping("/user/{id}")
	public boolean Delete(@PathVariable Long id) {

		boolean result = memberService.DeleteMember(id);

		return true;
	}

}
