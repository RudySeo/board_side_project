package sideproject.board.member.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sideproject.board.member.Entity.MemberEntity;
import sideproject.board.member.Entity.PrincipalDetails;
import sideproject.board.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberEntity member = memberRepository.findByName(username)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
		if (member != null) {
			return new PrincipalDetails(member);
		}
		return null;
	}
}
