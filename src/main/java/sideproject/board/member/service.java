package sideproject.board.member;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.exception.UserNotFoundException;
import sideproject.board.member.requests.createMemberRequest;
import sideproject.board.model.dto.MemberDto;
import sideproject.board.model.entity.Member;
import sideproject.board.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class service {

	private final repository repositorys;
	private final MemberRepository memberRepository;

	@Transactional
	public void newMember(createMemberRequest _createMemberRequest) {

		try {
			entity memberentity = new entity();
			memberentity.setEmail(_createMemberRequest.getEmail());
			memberentity.setPassword(_createMemberRequest.getPassword());
			memberentity.setName(_createMemberRequest.getName());
			memberentity.setAge(_createMemberRequest.getAge());

			repositorys.save(memberentity);
		} catch (Exception e) {
			System.out.println("오류입니다");
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
