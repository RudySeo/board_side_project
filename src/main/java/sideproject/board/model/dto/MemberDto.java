package sideproject.board.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MemberDto {

    @NotEmpty(message = "ID를 입력해주세요")
    private String memberId;
    @NotEmpty(message = "password를 입력해주세요")
    private String password;

    @NotNull
    private String name;
    @NotNull
    private int age;
}
