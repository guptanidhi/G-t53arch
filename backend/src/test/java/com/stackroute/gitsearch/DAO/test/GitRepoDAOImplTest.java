package com.stackroute.gitsearch.DAO.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.gitsearch.dao.GitRepoDAOImpl;
import com.stackroute.gitsearch.model.GitRepo;

public class GitRepoDAOImplTest {

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private GitRepoDAOImpl gitRepoDAOImpl;

	@Mock
	private GitRepo gitRepo;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		gitRepoDAOImpl = new GitRepoDAOImpl();
		gitRepoDAOImpl.setRepository(entityManager);
		gitRepo = new GitRepo("1", "Sample content", "JavaScript", "40", "www.ggoogle.com",
				"www.xyz.com", "1000", "very good", "www.abc.com", "Admin");

	}

	@Test
	public void testMockCreation() {
		assertNotNull(gitRepo);
		assertNotNull("entityManager creation fails: use @injectMocks on movieDAOImpl", entityManager);
	}

	@Test
	public void testAddRepo() throws Exception {
		doNothing().when(entityManager).persist(gitRepo);
		boolean flag = gitRepoDAOImpl.addRepo(gitRepo);
		assertEquals("Adding Repository failed , the call to gitRepoDAOImpl is returning false ,check this method",
				true, flag);
		verify(entityManager, times(1)).persist(gitRepo);
		verify(entityManager, times(1)).find(GitRepo.class, gitRepo.getId());
	}
	
	@Test
	public void testDeleteRepo() throws Exception {
		when(entityManager.find(GitRepo.class, "1")).thenReturn(gitRepo);
		doNothing().when(entityManager).remove(gitRepo);
		boolean flag = gitRepoDAOImpl.deleteRepo("1");
		assertEquals("deleting movie failed", true, flag);
		verify(entityManager, times(1)).remove(gitRepo);
		verify(entityManager, times(1)).find(GitRepo.class, gitRepo.getId());
	}
	
	@Test
	public void testGetAllRepos() throws Exception {
		Query query = Mockito.mock(Query.class);
		List<GitRepo> gitRepoList = new ArrayList<>(1);
		Mockito.when(entityManager.createQuery("from GitRepo")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(gitRepoList);
		List<GitRepo> gitRepo1= gitRepoDAOImpl.getAllRepos();
		assertEquals(gitRepoList, gitRepo1);
		verify(entityManager, times(1)).createQuery("from GitRepo");
	}
	
	@Test
	public void testGetMyRepos() throws Exception {
		Query query = Mockito.mock(Query.class);
		List<GitRepo> gitRepoList = new ArrayList<>();
		gitRepoList.add(gitRepo);
		Mockito.when(entityManager.createQuery("From GitRepo where userId=?")).thenReturn(query);
		Mockito.when(query.setParameter(1, "ashu@gmail.com")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(gitRepoList);
		List<GitRepo> gitRepoList1 = gitRepoDAOImpl.getMyRepos("ashu@gmail.com");
		assertEquals(gitRepoList, gitRepoList1);
		verify(entityManager, times(1)).createQuery("From GitRepo where userId=?");

	}
}
