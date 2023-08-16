package com.rainier.service;



import org.springframework.stereotype.Service;

import com.rainier.exception.UserException;
import com.rainier.model.User;

@Service
public interface UserService {
	
	public User findUserById(Long userId)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;

}
