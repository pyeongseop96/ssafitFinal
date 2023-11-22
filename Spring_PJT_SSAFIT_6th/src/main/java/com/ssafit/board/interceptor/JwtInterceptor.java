package com.ssafit.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafit.board.util.JwtUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTH = "access-token";
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("인터셉터 작동");
		
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		
		String token = request.getHeader(HEADER_AUTH);
		System.out.println(token);
		if(token != null) {
			jwtUtil.valid(token);
		}
		
		throw new Exception("유효하지 않은 접근입니다.");
	}
}
