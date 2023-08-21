package sideproject.board.contoller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sideproject.board.model.dto.MemberDto;
import sideproject.board.model.entity.Member;
import sideproject.board.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/users")
    public result allMembers(){
        List<Member> findMember = memberService.getAllMember();
                List<MemberDto1> collect =findMember
                .stream()
                .map(m -> new MemberDto1(m.getName(), m.getMemberId(), m.getAge()))
                .collect(Collectors.toList());
                return new result<>(collect);
    }

    @Data
    @AllArgsConstructor
    static class result<T> {
        private T data;

    }
    @Data
    @AllArgsConstructor
    static class MemberDto1 {
        private String memberId;
        private String name;
        private int age;
    }



}
