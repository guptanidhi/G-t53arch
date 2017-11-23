package com.stackroute.gitsearch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.gitsearch.dao.GitRepoDAO;
import com.stackroute.gitsearch.exceptions.DuplicateGitRepoException;
import com.stackroute.gitsearch.exceptions.GitRepoNotFoundException;
import com.stackroute.gitsearch.model.GitRepo;

@Service
public class GitRepoServiceImpl implements GitRepoService {

	@Autowired
	private GitRepoDAO gitUserDAO;

	public boolean addRepo(GitRepo gitRepo) throws DuplicateGitRepoException {
		return gitUserDAO.addRepo(gitRepo);
	}

	public boolean deleteRepo(String id) throws GitRepoNotFoundException {
		return gitUserDAO.deleteRepo(id);
	}

	public List<GitRepo> getAllRepos() {
		return gitUserDAO.getAllRepos();

	}

	public List<GitRepo> getMyRepos(String userId) {
		return gitUserDAO.getMyRepos(userId);

	}

}