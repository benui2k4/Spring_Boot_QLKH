package com.soft.service;

import java.util.List;

import com.soft.models.User;

public interface UserService {
	User findByUserName(String userName);
	List<User> findAll();
	
}
