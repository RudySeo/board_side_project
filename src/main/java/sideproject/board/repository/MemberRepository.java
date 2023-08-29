package sideproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sideproject.board.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
