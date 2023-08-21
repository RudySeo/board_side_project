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
        return id;
    }

    @PutMapping("/user/{id}")
    public Boolean upadateMember(@RequestBody MemberDto memberDto, @PathVariable Long id) {

        try {
            memberService.update(id, memberDto);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
