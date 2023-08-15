package sideproject.board.contoller;

import org.springframework.web.bind.annotation.*;
import sideproject.board.exception.UserNotFoundException;
import sideproject.board.model.entity.Member;
import sideproject.board.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
@RestController
public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/user")
    Member newMember(@RequestBody Member MemberDto) {
        return memberRepository.save(MemberDto);
    }

    @GetMapping("/users")
    List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @GetMapping("/user/{id}")
    Member getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    Optional<Member> upadateMember(@RequestBody Member MemberDto, @PathVariable Long id) {

        return Optional.ofNullable(memberRepository.findById(id)
                .map(member -> {
                    member.setName(MemberDto.getName());
                    member.setAge(MemberDto.getAge());
                    return memberRepository.save(member);
                }).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @DeleteMapping("/user/{id}")
    String deleteMember(@PathVariable Long id) {
        if (!memberRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        memberRepository.deleteById(id);
        return id + "삭제 완료 했습니다";
    }
}
