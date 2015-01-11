package com.cug.user.dao;

import com.cug.user.domain.User;

public interface UserDao {
	public void addUser(User user);
	public User findByUsername(String username);
}
