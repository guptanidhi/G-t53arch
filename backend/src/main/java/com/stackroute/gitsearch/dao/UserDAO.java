package com.stackroute.gitsearch.dao;

import java.util.List;

import com.stackroute.gitsearch.exceptions.DuplicateUserFoundException;
import com.stackroute.gitsearch.exceptions.UserNotFoundException;
import com.stackroute.gitsearch.model.User;

public interface UserDAO {

	public boolean saveUser(User user) throws DuplicateUserFoundException;

	public boolean updateUser(User user) throws UserNotFoundException;

	public boolean deleteUser(String userId) throws UserNotFoundException;

	public User getUserById(String userId) throws UserNotFoundException;

	public List<User> getAllUsers() ;

}
