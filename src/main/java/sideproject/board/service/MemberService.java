package sideproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sideproject.board.exception.UserNotFoundException;
import sideproject.board.model.entity.Member;
import sideproject.board.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional(readOnly = false)

    public Long newMember(Member MemberDto) {
        memberRepository.save(MemberDto);
        return MemberDto.getId();
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    public Long getMemberById(Long id) {
        memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return id;
    }
}
