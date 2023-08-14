package sideproject.board.model.dto;

import lombok.Data;

@Data
public class MemberDto {

    private String member_id;
    private String password;

    private String name;

    private int age;
}
