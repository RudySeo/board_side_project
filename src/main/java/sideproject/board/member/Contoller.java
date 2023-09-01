package sideproject.board.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sideproject.board.member.requests.CreateMemberRequest;
import sideproject.board.member.requests.UpdateMemberRequest;
import sideproject.board.member.responses.ResponseHandler;

@RestController
@RequiredArgsConstructor

public class Contoller {

	private final Service service;


	@PostMapping("/user")
	public ResponseEntity<Object> createMember(@RequestBody @Valid CreateMemberRequest createMemberRequest) {

		try {
			Entity result = service.newMember(createMemberRequest);
			return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}

	}

	@GetMapping("/users")
	public ResponseEntity<Object> allMembers() {
		try {
			List<Entity> result = service.getAllMember();
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.OK, null);
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> oneMemberById(@PathVariable Long id) {
		try {
			Entity result = service.getMemberById(id);
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateMember(@PathVariable Long id,
		@RequestBody UpdateMemberRequest updateMemberRequest) {
		try {
			UpdateMemberRequest result = service.updateMember(id, updateMemberRequest);

			return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> Delete(@PathVariable Long id) {
		try {
			Long result = service.DeleteMember(id);
			return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

}
