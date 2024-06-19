package com.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soft.models.Category;
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
	
	 @Override
	    public void save(User user) {
		
	        userRepository.save(user);
	    }

	@Override
	public List<User> searchUser(String keyword) {
		
		return this.userRepository.searchUser(keyword);
	}

	@Override
	public Page<User> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		return this.userRepository.findAll(pageable);
	}

	@Override
	public Page<User> searchUser(String keyword, Integer pageNo) {
List list = this.searchUser(keyword);
		
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		
		Integer start = (int) pageable.getOffset();
		
		Integer end =(int) ( pageable.getOffset() + pageable.getPageSize() > list.size() ?  list.size() : pageable.getOffset() + pageable.getPageSize());
		
		list = list.subList(start, end);
		
		return new PageImpl<User>(list, pageable, this.searchUser(keyword).size());
	}

}
