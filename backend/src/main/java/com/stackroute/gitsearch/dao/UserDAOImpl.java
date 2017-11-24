package com.stackroute.gitsearch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.gitsearch.exceptions.DuplicateUserFoundException;
import com.stackroute.gitsearch.exceptions.UserNotFoundException;
import com.stackroute.gitsearch.model.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean saveUser(User user) throws DuplicateUserFoundException {

		if (entityManager.find(User.class, user.getUserId()) != null) {
			throw new DuplicateUserFoundException("User Registration failed, User already exists");
		}
		entityManager.persist(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) throws UserNotFoundException {
		User user1 = entityManager.find(User.class, user.getUserId());
		if (user1 == null) {
			throw new UserNotFoundException("Couldn't update user. user not found!");
		}
		entityManager.merge(user);
		return true;
	}

	@Override
	public boolean deleteUser(String userId) throws UserNotFoundException {
		User user = entityManager.find(User.class, userId);
		if (user == null) {
			throw new UserNotFoundException("Could not delete , user not found!");
		}
		entityManager.remove(user);
		return true;
	}

	@Override
	public User getUserById(String userId) throws UserNotFoundException {
		User user = entityManager.find(User.class, userId);
		if (user == null) {
			throw new UserNotFoundException("user not found!");
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return entityManager.createQuery("From User").getResultList();
	}

}
