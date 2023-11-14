package com.ssafit.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.board.model.dto.User;
import com.ssafit.board.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-user")
@Api(tags = "게시판 컨트롤러")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	
	//1. 회원가입
	@PostMapping("/signup")
	@ApiOperation(value = "회원 등록하기", notes = "유저를 회원으로 등록합니다.")
	public ResponseEntity<User> signup(User user) {
		userService.signup(user);
		
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	
	//2. 로그인
	@PostMapping("/login")
	@ApiOperation(value = "로그인하기", notes = "해당 유저를 로그인합니다.")
	public ResponseEntity<String> login(User user, HttpSession session) {
		User tmp = userService.login(user);

		if(tmp == null)
			return new ResponseEntity<String>("fail", HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<String>(tmp.getName(), HttpStatus.OK);
	}
	
	// 3. 로그아웃
	@GetMapping("logout")
	@ApiOperation(value = "로그아웃하기", notes = "해당 유저를 로그아웃합니다.")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}