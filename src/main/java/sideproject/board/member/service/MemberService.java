package sideproject.board.member.service;

import static sideproject.board.global.exception.ErrorCode.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.global.exception.ClientException;
import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.requests.UpdateMemberRequest;
import sideproject.board.member.contoller.responses.CreateMemberResponse;
import sideproject.board.member.contoller.responses.MemberListResponse;
import sideproject.board.member.contoller.responses.UpdateMemberResponse;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.domain.Entity.MemberRepository;

@RequiredArgsConstructor
@Builder
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public CreateMemberResponse createMember(CreateMemberRequest createMemberRequest) {
		Member member = Member.convertToEntity(createMemberRequest);

		Member saveEntity = memberRepository.save(member);

		CreateMemberResponse createMemberResponse = CreateMemberResponse.convertToCreateMemberRes(saveEntity);
		return createMemberResponse;
	}

	@Transactional(readOnly = true)
	public List<MemberListResponse> getAllMember() {

		List<Member> members = memberRepository.findAll();

		List<MemberListResponse> result = members.stream()
			.map(m -> new MemberListResponse(m.getId(), m.getEmail(), m.getName(), m.getAge()))
			.collect(Collectors.toList());

		return result;
	}

	@Transactional(readOnly = true)
	public MemberListResponse getMemberById(Long id) {

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

		MemberListResponse memberListResponse = MemberListResponse.convertToGetMemberRes(member);
		return memberListResponse;
	}

	@Transactional
	public UpdateMemberResponse updateMember(Long id, UpdateMemberRequest updateMemberRequest) {

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

		member.setName(updateMemberRequest.getName());
		member.setAge(updateMemberRequest.getAge());

		Member updatedEntity = memberRepository.save(member);

		UpdateMemberResponse updateMemberResponse = UpdateMemberResponse.convertToUpdateMemberRes(updatedEntity);
		return updateMemberResponse;
	}



	@Transactional
	public boolean DeleteMember(Long id) {

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new ClientException(NOT_FOUND_MEMBER_ID));

		memberRepository.delete(member);
		return true;

	}
}
