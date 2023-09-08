package sideproject.board.member;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.member.Entity.MemberEntity;


public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
