package com.ssafit.board.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ssafit.board.model.dto.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	private static final String SALT = "SSAFIT";
	
	// 넣을 정보가 담긴 claims 선언
	
	
	// 토큰 생성
	public String createToken(User user) throws UnsupportedEncodingException {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getUserID());
		claims.put("password", "");
		claims.put("name", user.getName().getBytes("UTF-8")); // 한글 깨짐 수정 (UTF-8 => Byte)
		claims.put("email", user.getEmail());
		claims.put("age", user.getAge());
		
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT") // header
				.setClaims(claims)
//				.setExpiration(new Date(System.currentTimeMillis()+3000)) // payload, 수명 3초
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")) // signature
				.compact(); // 예외처리할것
	}
		
	// 토큰 유효성 검사
	public void valid(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		System.out.println(" 유효성 검사 진행 ");
		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
	}
	
}
