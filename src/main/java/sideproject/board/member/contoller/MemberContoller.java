package sideproject.board.member.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.common.CommonResponseEntity;
import sideproject.board.member.MemberService;
import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.responses.CreateMemberResponse;
import sideproject.board.member.contoller.responses.MemberListResponse;

@RestController
@RequiredArgsConstructor
@Builder
public class MemberContoller {

	private final MemberService memberService;


	@PostMapping("/user")
	public CommonResponseEntity<CreateMemberResponse> createMember(
		@RequestBody @Valid CreateMemberRequest createMemberRequest) {

		CreateMemberResponse res = memberService.newMember(createMemberRequest);
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

		if (result != null) {
			CommonResponseEntity<MemberListResponse> responseEntity = CommonResponseEntity.<MemberListResponse>builder()
				.result(true)
				.status(HttpStatus.OK)
				.data(result)
				.build();

			return responseEntity;
		} else {
			return CommonResponseEntity.<MemberListResponse>builder()
				.result(false)
				.status(HttpStatus.NOT_FOUND)
				.build();
		}
	}

	// @PutMapping("/user/{id}")
	// public ResponseEntity<Object> updateMember(@PathVariable Long id,
	// 	@RequestBody UpdateMemberRequest updateMemberRequest) {
	// 	try {
	// 		UpdateMemberRequest result = memberService.updateMember(id, updateMemberRequest);
	//
	// 		return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, result);
	// 	} catch (Exception e) {
	// 		return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
	// 	}
	// }

	// @DeleteMapping("/user/{id}")
	// public ResponseEntity<Object> Delete(@PathVariable Long id) {
	// 	try {
	// 		Long result = memberService.DeleteMember(id);
	// 		return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, result);
	// 	} catch (Exception e) {
	// 		return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	// 	}
	// }

}
