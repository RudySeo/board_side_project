package sideproject.board.member;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.exception.UserNotFoundException;
import sideproject.board.member.requests.CreateMemberRequest;
import sideproject.board.model.dto.MemberDto;
import sideproject.board.model.entity.Member;
import sideproject.board.repository.MemberRepository;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

	private final Repository repositorys;
	private final MemberRepository memberRepository;

	@Transactional
	public boolean newMember(CreateMemberRequest createMemberRequest) {

		try {
			Entity memberentity = new Entity();
			System.out.println(createMemberRequest + "aaaaaa");
			memberentity.setEmail(createMemberRequest.getEmail());
			memberentity.setPassword(createMemberRequest.getPassword());
			memberentity.setName(createMemberRequest.getName());
			memberentity.setAge(createMemberRequest.getAge());
			repositorys.save(memberentity);
			return true;

		} catch (Exception e) {
			System.out.println("오류입니다");
			return false;
		}
	}

	@Transactional(readOnly = true)
	public List<Member> getAllMember() {

		return memberRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Long getMemberById(Long id) {
		memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return id;
	}

	@Transactional
	public void update(Long id, MemberDto memberDto) throws Exception {
		Optional<Member> findMember = memberRepository.findById(id);
		if (findMember.isPresent()) {
			Member member = findMember.get();
			member.setAge(memberDto.getAge());
			member.setName(memberDto.getName());
		} else {
			throw new Exception();
		}
	}
}
