package sideproject.board.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import sideproject.board.member.Entity.MemberEntity;
import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.responses.CreateMemberResponse;
import sideproject.board.member.contoller.responses.MemberListResponse;

@RequiredArgsConstructor
@Builder
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public CreateMemberResponse newMember(CreateMemberRequest createMemberRequest) {
		// //DTO to Entity
		MemberEntity memberEntity = MemberEntity.convertToEntity(createMemberRequest);
		// //SAVE
		MemberEntity saveEntity = memberRepository.save(memberEntity);
		// //Entity TO DTO
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
	public MemberEntity getMemberById(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저 아이디가 없습니다. id" + id));
	}



	@Transactional
	public Long DeleteMember(Long id) {
		MemberEntity findMember = memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저 아이디가 없습니다. id" + id));
		memberRepository.deleteById(id);
		return findMember.getId();

	}
}
