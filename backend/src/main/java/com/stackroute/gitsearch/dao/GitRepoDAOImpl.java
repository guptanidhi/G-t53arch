package com.stackroute.gitsearch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.stackroute.gitsearch.exceptions.DuplicateGitRepoException;
import com.stackroute.gitsearch.exceptions.GitRepoNotFoundException;
import com.stackroute.gitsearch.model.GitRepo;

@Repository
@Transactional
public class GitRepoDAOImpl implements GitRepoDAO {

	@PersistenceContext
	private EntityManager repository;

	public EntityManager getRepository() {
		return repository;
	}

	public void setRepository(EntityManager repository) {
		this.repository = repository;
	}

	public boolean addRepo(GitRepo gitRepo) throws DuplicateGitRepoException {
		if (repository.find(GitRepo.class, gitRepo.getId()) != null) {
			throw new DuplicateGitRepoException("Adding repository failed, GitRepository already exists");
		}
		repository.persist(gitRepo);
		return true;

	}

	public boolean deleteRepo(String id) throws GitRepoNotFoundException {
		GitRepo gitRepo = repository.find(GitRepo.class, id);
		if (gitRepo == null) {
			throw new GitRepoNotFoundException("Could not delete , GitRepository not found!");
		}
		repository.remove(gitRepo);
		return true;

	}

	@SuppressWarnings("unchecked")
	public List<GitRepo> getAllRepos() {
		return repository.createQuery("from GitRepo").getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GitRepo> getMyRepos(String userId) {
		return repository.createQuery("From GitRepo where userId=?").setParameter(1, userId).getResultList();
	}
}
