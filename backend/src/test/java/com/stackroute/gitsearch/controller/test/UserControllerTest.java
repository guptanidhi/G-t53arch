package com.stackroute.gitsearch.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.gitsearch.App;
import com.stackroute.gitsearch.controllers.UserController;
import com.stackroute.gitsearch.model.User;
import com.stackroute.gitsearch.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	private MockMvc mockMVC;

	@MockBean
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private User user;
	static List<User> users;

	@Before
	public void setUp() {
		mockMVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		users = new ArrayList<>();
	}

	@Test
	public void testRegisterUserSuccess() throws Exception {
		user = new User();
		user.setUserId("ashu@gmail.com");
		user.setFirstName("ashutosh");
		user.setLastName("singh");
		user.setPassword("1234");

		when(userService.saveUser(user)).thenReturn(true);
		mockMVC.perform(post("/userService/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isCreated()).andDo(print());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testLoginSuccess() throws Exception {
		user = new User();
		user.setUserId("ashu@gmail.com");
		user.setPassword("1234");

		when(userService.getUserById("ashu@gmail.com")).thenReturn(user);
		mockMVC.perform(post("/userService/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
				.andExpect(status().isOk()).andDo(print());
		verify(userService, times(1)).getUserById("ashu@gmail.com");
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testFindByIdSuccess() throws Exception {
		user = new User();
		user.setUserId("ashu@gmail.com");

		when(userService.getUserById("ashu@gmail.com")).thenReturn(user);
		mockMVC.perform(get("/secureService/user/{userId}", "ashu@gmail.com.").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isOk()).andDo(print());
		verify(userService, times(1)).getUserById("ashu@gmail.com");
		verifyNoMoreInteractions(userService);
	}

	// Parsing String format data into JSON format
	private static String jsonToString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
