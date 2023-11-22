package com.ssafit.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@CrossOrigin("*")
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
	
	/*
	 * jwt 토큰 사용
	 * 인증 만료 확인할 것
	 */
	//2. 로그인
	@PostMapping("/login")
	@ApiOperation(value = "로그인하기", notes = "해당 유저를 로그인합니다.")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		Map<String, Object> res = new HashMap<String, Object>();

		HttpStatus status = null;
	
		User tmp = userService.login(user);	// service - dao - db 통해서 유저 확인
		
		try {
			if (tmp.getUserID() != null && tmp.getUserID().length() > 0) {
				// 노출 가능한 유저 정보 추가 (id, name, email, age)
				res.put("access-token", jwtUtil.createToken(tmp));
				res.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				res.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (UnsupportedEncodingException e) {
			res.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}		

		return new ResponseEntity<Map<String, Object>>(res,status);
	}
	
	// 3. 로그아웃
	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃하기", notes = "해당 유저를 로그아웃합니다.")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	// 4. 유저 인증
	@PutMapping("/auth")
	@ApiOperation(value = "인증하기", notes = "해당 유저의 ACCESS토큰을 검증합니다.")
	public ResponseEntity<?> verify(@RequestBody String token) {
		HttpStatus status;
		System.out.println(token);
		try {
			jwtUtil.valid(token);
			status = HttpStatus.ACCEPTED;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("인증된 유저가 아닙니다");
			status = HttpStatus.UNAUTHORIZED;

		}
		
		return new ResponseEntity<Void>(status);
	}
	
	// 5. 유저 정보 수정
		@PostMapping("/update")
		@ApiOperation(value = "프로필 수정하기", notes = "해당 유저의 개인정보를 수정합니다.")
		public ResponseEntity<?> changeProfile(@RequestBody User user) {
//		public ResponseEntity<?> changeProfile(@RequestParam String password, @RequestParam String name, @RequestParam String email, @RequestParam int age) {
			System.out.println(user.toString());
			
			if (user != null) {
				userService.changeInfo(user);
			}
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}


}