package com.ssafit.board.model.dao;

import java.util.List;

import com.ssafit.board.model.dto.User;

public interface UserDao {

	List<User> selectAll();

	void insertUser(User user);
	
	User selectOne(String userID);
	
}