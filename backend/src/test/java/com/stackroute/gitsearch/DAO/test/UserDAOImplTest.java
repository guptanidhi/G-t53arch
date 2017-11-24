package com.stackroute.gitsearch.DAO.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.gitsearch.dao.UserDAOImpl;
import com.stackroute.gitsearch.model.User;

public class UserDAOImplTest {

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private UserDAOImpl userDAOImpl;

	@Mock
	private User user;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		userDAOImpl = new UserDAOImpl();
		userDAOImpl.setEntityManager(entityManager);
		user = new User();
		user.setFirstName("sample");
		user.setLastName("user");
		user.setUserId("1");
		user.setPassword("123");

	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(user);
		assertNotNull("entityManager creation fails: use @injectMocks on movieDAOImpl", entityManager);
	}
	
	@Test
	public void testSaveUserSuccess() throws Exception {
		doNothing().when(entityManager).persist(user);
		boolean flag = userDAOImpl.saveUser(user);
		assertEquals("Adding Repository failed , the call to userDAOImpl is returning false ,check this method",
				true, flag);
		verify(entityManager, times(1)).persist(user);
		verify(entityManager, times(1)).find(User.class, user.getUserId());
	}
	
	@Test
	public void testGetUserByIdSuccess() throws Exception {
		when(entityManager.find(User.class, "1")).thenReturn(user);
		User user1 = userDAOImpl.getUserById("1");
		assertEquals("fetching user by id failed", user1, user);
		verify(entityManager, times(1)).find(User.class, user.getUserId());
	}


}
