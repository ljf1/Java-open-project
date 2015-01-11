package com.cug.user.service;

import com.cug.user.dao.UserDao;
import com.cug.user.dao.UserDaoFactory;
import com.cug.user.domain.User;

public class UserService {
	private UserDao userDao = UserDaoFactory.getUserDao();
	
	public void register(User form) throws UserException{
		User user = userDao.findByUsername(form.getUsername());
		if(user != null)
			throw new UserException("该用户"+form.getUsername()+"已经存在！");
		userDao.addUser(form);
	}
	
	public User login(User form) throws UserException{
		User user = userDao.findByUsername(form.getUsername());
		if(user == null){
			throw new UserException("该用户不存在");
		}
		if(!user.getPassword().equals(form.getPassword())){
			throw new UserException("该用户的密码错误");
		}
		return user;
	}
}
