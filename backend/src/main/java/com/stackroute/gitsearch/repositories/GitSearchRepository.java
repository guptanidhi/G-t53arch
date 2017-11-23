package com.stackroute.gitsearch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.gitsearch.model.GitRepo;

@Repository
public interface GitSearchRepository extends JpaRepository<GitRepo, String> {

}
