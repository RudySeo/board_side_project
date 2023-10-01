package sideproject.board.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sideproject.board.member.Entity.MemberEntity;


public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findByName(String name);

}
