package com.soft.service;

import com.soft.models.User;

public interface UserService {
	User findByUserName(String userName);
}
