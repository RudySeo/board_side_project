package sideproject.board.member;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sideproject.board.member.requests.CreateMemberRequest;
import sideproject.board.member.requests.UpdateMemberRequest;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

	private final Repository repositorys;

	@Transactional
	public Entity newMember(CreateMemberRequest createMemberRequest) {

		Entity memberentity = new Entity();
		memberentity.setEmail(createMemberRequest.getEmail());
		memberentity.setPassword(createMemberRequest.getPassword());
		memberentity.setName(createMemberRequest.getName());
		memberentity.setAge(createMemberRequest.getAge());

		return repositorys.save(memberentity);

	}

	@Transactional(readOnly = true)
	public List<Entity> getAllMember() {

		return repositorys.findAll();
	}

	@Transactional(readOnly = true)
	public Entity getMemberById(Long id) {
		return repositorys.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저 아이디가 없습니다. id" + id));
	}

	@Transactional
	public UpdateMemberRequest updateMember(Long id, UpdateMemberRequest updateMemberRequest) {
		Entity findMember = repositorys.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저 아이디가 없습니다. id" + id));
		findMember.setName(updateMemberRequest.getName());
		findMember.setAge(updateMemberRequest.getAge());
		repositorys.save(findMember);

		return updateMemberRequest;
	}

	@Transactional
	public Long DeleteMember(Long id) {
		Entity findMember = repositorys.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저 아이디가 없습니다. id" + id));
		repositorys.deleteById(id);
		return findMember.getId();

	}
}
