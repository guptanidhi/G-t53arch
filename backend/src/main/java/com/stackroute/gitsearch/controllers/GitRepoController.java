package com.stackroute.gitsearch.controllers;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.gitsearch.exceptions.DuplicateGitRepoException;
import com.stackroute.gitsearch.exceptions.GitRepoNotFoundException;
import com.stackroute.gitsearch.model.GitRepo;
import com.stackroute.gitsearch.services.GitRepoService;

import io.jsonwebtoken.Jwts;

//@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/gitSearchService")
public class GitRepoController {

	@Autowired
	private GitRepoService repoService;

	@PostMapping(path = "/addRepo")
	public ResponseEntity<?> addRepo(@RequestBody GitRepo gitRepo) {
		try {
			repoService.addRepo(gitRepo);
		} catch (DuplicateGitRepoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(gitRepo, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteRepo/{id}")
	public ResponseEntity<?> deleteRepo(@PathVariable("id") String id) {
		try {
			repoService.deleteRepo(id);
		} catch (GitRepoNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("GitUser deleted successfully", HttpStatus.OK);
	}

	@GetMapping("/getAllRepos")
	public ResponseEntity<List<GitRepo>> getAllRepos() {
		return new ResponseEntity<List<GitRepo>>(repoService.getAllRepos(), HttpStatus.OK);

	}

	@GetMapping("/getMyRepos")
	public ResponseEntity<List<GitRepo>> getMyUsers(final ServletRequest req, final ServletResponse res) {

		final HttpServletRequest request = (HttpServletRequest) req;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userName = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

		return new ResponseEntity<List<GitRepo>>(repoService.getMyRepos(userName), HttpStatus.OK);

	}

}
