package sideproject.board.global.exception.configuration;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sideproject.board.member.service.MemberService;
import sideproject.board.utils.JwtUtil;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	private final MemberService memberService;

	@Value("${jwt.secretKey}")
	private final String secretKey;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
		log.info(authorization + "해더에서 토큰 값 가지고오기");

		//토큰 안 보내면 오류
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			log.error("Authorization 잘못 보냈습니다");
			filterChain.doFilter(request, response);
			return;
		}
		//Token 꺼내기
		String token = authorization.split(" ")[1];

		//유효기간 확인
		if (JwtUtil.isExpired(token, secretKey)) {
			log.error("토큰 만료 입니다");
			filterChain.doFilter(request, response);
			return;
		}

		//유저 이름 꺼내기
		String username = JwtUtil.getUserName(token, secretKey);
		String role = JwtUtil.getUserRole(token, secretKey);
		log.info(username + "유저 이름 확인");
		// PrincipalDetails principalDetails = new PrincipalDetails()

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
			null,
			List.of(new SimpleGrantedAuthority(role)));
		//디테일
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}
}
