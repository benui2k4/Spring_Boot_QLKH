package com.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.models.User;
import com.soft.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	


	@Override
	public User findByUserName(String userName) {

		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

}
