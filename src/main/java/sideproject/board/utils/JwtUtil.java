package sideproject.board.utils;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sideproject.board.member.domain.Entity.Member;

public class JwtUtil {
	@Value("${jwt.secretKey}")
	private String secretKey;

	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
			.getBody().get("userName", String.class);

	}

	public static String getUserRole(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
			.getBody().get("userName", String.class);

	}

	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.getExpiration()
			.before(new Date());
	}

	public static String createJwt(Member member, String secretKey, Long expiredMs) {
		Claims claims = Jwts.claims();
		claims.put("username", member.getName());
		claims.put("role", member.getStatus());

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}
}
