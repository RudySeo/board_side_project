package sideproject.board.member.domain.Entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByName(String name);

}
