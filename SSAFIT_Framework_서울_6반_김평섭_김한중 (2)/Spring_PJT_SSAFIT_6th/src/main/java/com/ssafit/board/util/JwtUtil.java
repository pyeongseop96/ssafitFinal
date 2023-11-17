package com.ssafit.board.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	private static final String SALT = "SSAFIT";
	
	// 토큰 생성
	public String createToken(String key, String value) throws UnsupportedEncodingException {
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT") // header
				.claim(key, value)
//				.setExpiration(new Date(System.currentTimeMillis()+3000)) // payload, 수명 3초
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")) // signature
				.compact(); // 예외처리할것
	}
		
	// 토큰 유효성 검사
	public void valid(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
	}
	
}
