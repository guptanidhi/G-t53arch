package com.stackroute.gitsearch.dao;

import java.util.List;

import com.stackroute.gitsearch.exceptions.DuplicateGitRepoException;
import com.stackroute.gitsearch.exceptions.GitRepoNotFoundException;
import com.stackroute.gitsearch.model.GitRepo;

public interface GitRepoDAO {

	public boolean addRepo(GitRepo gitRepo) throws DuplicateGitRepoException;

	public boolean deleteRepo(String id) throws GitRepoNotFoundException;

	public List<GitRepo> getAllRepos();

	public List<GitRepo> getMyRepos(String userId);

}
