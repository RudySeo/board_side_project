package sideproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sideproject.board.exception.UserNotFoundException;
import sideproject.board.model.dto.MemberDto;
import sideproject.board.model.entity.Member;
import sideproject.board.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional(readOnly = false)
    public Long newMember(Member MemberDto) {
        memberRepository.save(MemberDto);
        return MemberDto.getId();
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
