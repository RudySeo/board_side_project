package sideproject.board.utils;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	@Value("${jwt.secretKey}")
	private String secretKey;


	public static String createJwt(String name, String secretKey, Long expiredMs) {
		Claims claims = Jwts.claims();
		claims.put("username", name);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}
}
