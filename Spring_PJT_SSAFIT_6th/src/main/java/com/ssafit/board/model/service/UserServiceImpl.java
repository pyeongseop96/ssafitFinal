package com.ssafit.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.board.model.dto.User;
import com.ssafit.board.model.dao.UserDao;
import com.ssafit.board.model.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}


	@Override
	public void signup(User user) {
		userDao.insertUser(user);
	}


	@Override
	public User login(User user) {
		User tmp = userDao.selectOne(user.getUserID());
		if(tmp != null && tmp.getPassword().equals(user.getPassword()))
			return tmp;
		return null;
	}


	@Override
	public void changeInfo(User user) {
		userDao.updateUser(user);
	}
}