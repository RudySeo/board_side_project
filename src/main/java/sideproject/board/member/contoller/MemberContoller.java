package sideproject.board.member.contoller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.common.CommonResponseEntity;
import sideproject.board.member.MemberService;
import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.responses.CreateMemberResponse;

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
			.body(res)
			.build();

		return responseEntity;
	}

	// @GetMapping("/users")
	// public ResponseEntity<Object> allMembers() {
	// 	try {
	// 		List<MemberEntity> result = memberService.getAllMember();
	// 		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
	// 	} catch (Exception e) {
	// 		return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
	// 	}
	// }
	//
	// @GetMapping("/user/{id}")
	// public ResponseEntity<Object> oneMemberById(@PathVariable Long id) {
	// 	try {
	// 		MemberEntity result = memberService.getMemberById(id);
	// 		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
	// 	} catch (Exception e) {
	// 		return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	// 	}
	// }

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
