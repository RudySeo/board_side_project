package sideproject.board.member.domain.Entity;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;


public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Member> findAndLockById(Long id);
}
