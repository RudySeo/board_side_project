package sideproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.board.model.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
