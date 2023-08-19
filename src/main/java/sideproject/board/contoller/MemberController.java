package sideproject.board.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sideproject.board.model.dto.MemberDto;
import sideproject.board.model.entity.Member;
import sideproject.board.service.MemberService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor

public class MemberController {


    private final MemberService memberService;

    @PostMapping("/user")
    public Long createMember(@RequestBody @Valid MemberDto memberDto) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setPassword(memberDto.getPassword());
        member.setName(memberDto.getName());
        member.setAge(memberDto.getAge());

        Long id = memberService.newMember(member);
        return  id;
    }
//
//    @GetMapping("/users")
//    List<Member> getAllMember() {
//        return memberRepository.findAll();
//    }

//    @GetMapping("/user/{id}")
//    Member getMemberById(@PathVariable Long id) {
//        return memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    @PutMapping("/user/{id}")
//    Optional<Member> upadateMember(@RequestBody Member MemberDto, @PathVariable Long id) {
//
//        return Optional.ofNullable(memberRepository.findById(id)
//                .map(member -> {
//                    member.setName(MemberDto.getName());
//                    member.setAge(MemberDto.getAge());
//                    return memberRepository.save(member);
//                }).orElseThrow(() -> new UserNotFoundException(id)));
//    }
//
//    @DeleteMapping("/user/{id}")
//    String deleteMember(@PathVariable Long id) {
//        if (!memberRepository.existsById(id)) {
//            throw new UserNotFoundException(id);
//        }
//        memberRepository.deleteById(id);
//        return id + "삭제 완료 했습니다";
//    }
}
