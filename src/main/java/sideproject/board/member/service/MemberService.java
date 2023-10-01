package sideproject.board.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.exception.UserNotFoundException;
import sideproject.board.member.Entity.MemberEntity;
import sideproject.board.member.MemberRepository;
import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.contoller.responses.CreateMemberResponse;
import sideproject.board.member.contoller.responses.MemberListResponse;
import sideproject.board.member.contoller.responses.UpdateMemberResponse;

@RequiredArgsConstructor
@Builder
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public CreateMemberResponse createMember(CreateMemberRequest createMemberRequest) {
		MemberEntity memberEntity = MemberEntity.convertToEntity(createMemberRequest);

		MemberEntity saveEntity = memberRepository.save(memberEntity);

		CreateMemberResponse createMemberResponse = CreateMemberResponse.convertToCreateMemberRes(saveEntity);
		return createMemberResponse;
	}

	@Transactional(readOnly = true)
	public List<MemberListResponse> getAllMember() {

		List<MemberEntity> members = memberRepository.findAll();

		List<MemberListResponse> result = members.stream()
			.map(m -> new MemberListResponse(m.getId(), m.getEmail(), m.getName(), m.getAge()))
			.collect(Collectors.toList());

		return result;
	}

	@Transactional(readOnly = true)
	public MemberListResponse getMemberById(Long id) {

		MemberEntity memberEntity = memberRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("해당 유저 아이디가 없습니다. id" + id));

		MemberListResponse memberListResponse = MemberListResponse.convertToGetMemberRes(memberEntity);
		return memberListResponse;
	}

	@Transactional
	public UpdateMemberResponse updateMember(Long id, UpdateMemberRequest updateMemberRequest) {

		MemberEntity memberEntity = memberRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("해당 유저 아이디가 없습니다. id" + id));

		memberEntity.setName(updateMemberRequest.getName());
		memberEntity.setAge(updateMemberRequest.getAge());

		MemberEntity updatedEntity = memberRepository.save(memberEntity);

		UpdateMemberResponse updateMemberResponse = UpdateMemberResponse.convertToUpdateMemberRes(updatedEntity);
		return updateMemberResponse;
	}



	@Transactional
	public boolean DeleteMember(Long id) {

		MemberEntity memberEntity = memberRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("해당 유저 아이디가 없습니다. id" + id));

		memberRepository.delete(memberEntity);
		return true;

	}
}
