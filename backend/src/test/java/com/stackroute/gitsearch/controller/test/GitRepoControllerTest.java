package com.stackroute.gitsearch.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.stackroute.gitsearch.controllers.GitRepoController;
import com.stackroute.gitsearch.model.GitRepo;
import com.stackroute.gitsearch.services.GitRepoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitRepoControllerTest {

	private MockMvc mockMVC;

	@MockBean
	private GitRepoService repoService;

	@InjectMocks
	private GitRepoController movieController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private GitRepo gitRepo;
	static List<GitRepo> gitRepos;

	@Before
	public void setUp() {
		mockMVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		gitRepos = new ArrayList<>();
		gitRepo = new GitRepo("1", "Sample content", "JavaScript", "40", "www.ggoogle.com", "www.xyz.com", "1000",
				"very good", "www.abc.com", "Admin");
		gitRepos.add(gitRepo);
		gitRepo = new GitRepo("2", "Sample content", "JavaScript", "40", "www.ggoogle.com", "www.xyz.com", "1000",
				"very good", "www.abc.com", "Admin");

		gitRepos.add(gitRepo);
	}

	@Test
	public void testAddRepoSuccess() throws Exception {
		when(repoService.addRepo(gitRepo)).thenReturn(true);
		mockMVC.perform(
				post("/gitSearchService/addRepo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(gitRepo)))
				.andExpect(status().isCreated())
				.andDo(print());
		verify(repoService, times(1)).addRepo(Mockito.any(GitRepo.class));
		verifyNoMoreInteractions(repoService);
	}

	
	@Test
	public void testDeleteRepoSuccess() throws Exception {
		when(repoService.deleteRepo("1")).thenReturn(true);
		mockMVC.perform(
				delete("/gitSearchService/deleteRepo/{id}", "1"))
				.andExpect(status().isOk());
		verify(repoService, times(1)).deleteRepo("1");
		verifyNoMoreInteractions(repoService);
	}

	@Test
	public void testGetAllRepos() throws Exception {
		when(repoService.getAllRepos()).thenReturn(null);
		mockMVC.perform(
				get("/gitSearchService/getAllRepos"))
				.andExpect(status().isOk());
		verify(repoService, times(1)).getAllRepos();
		verifyNoMoreInteractions(repoService);
	}

	@Test
	public void testGetMyRepos() throws Exception {
		String token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2h1QGdtYWlsLmNvbSIsImlhdCI6MTUxMDgxNTczMX0.6a3aiWgY2BhfIU2ghwf89wqzjPVoBEfmTwUgT9LExnw";
		when(repoService.getMyRepos("ashu@gmail.com")).thenReturn(gitRepos);
		mockMVC.perform(
				get("/gitSearchService/getMyRepos")
				.header("authorization", "Bearer " +token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
		verify(repoService, times(1)).getMyRepos("ashu@gmail.com");
		verifyNoMoreInteractions(repoService);
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
