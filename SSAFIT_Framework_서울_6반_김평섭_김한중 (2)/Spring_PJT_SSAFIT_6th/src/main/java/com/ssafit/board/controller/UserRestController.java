package com.ssafit.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.board.model.dto.User;
import com.ssafit.board.model.service.UserService;
import com.ssafit.board.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-user")
@Api(tags = "게시판 컨트롤러")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	
	//1. 회원가입
	@PostMapping("/signup")
	@ApiOperation(value = "회원 등록하기", notes = "유저를 회원으로 등록합니다.")
	public ResponseEntity<User> signup(@RequestBody User user) {
		userService.signup(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	
	//2. 로그인
	@PostMapping("/login")
	@ApiOperation(value = "로그인하기", notes = "해당 유저를 로그인합니다.")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User tmp = userService.login(user);
		if(tmp == null || !tmp.getPassword().equals(user.getPassword()))
			return new ResponseEntity<String>("fail", HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<User>(tmp, HttpStatus.OK);
	}
	
	/*
	 * jwt 토큰 사용
	 * 인증 만료 확인할 것
	 */

//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
//		Map<String, Object> res = new HashMap<String, Object>();
//		
//		// service - dao - db 통해서 유저 확인 필요
//		
//		HttpStatus status = null;
//		
//		try {
//			if (user.getId() != null && user.getId().length() > 0) {
//				res.put("access-token", jwtUtil.createToken("id", user.getId()));
//				res.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				res.put("message", FAIL);
//				status = HttpStatus.NO_CONTENT;
//			}
//		} catch (UnsupportedEncodingException e) {
//			res.put("message", FAIL);
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}		
//		
//		return new ResponseEntity<Map<String, Object>>(res,status);
//
//	}
	

	
	
	// 3. 로그아웃
	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃하기", notes = "해당 유저를 로그아웃합니다.")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	


}