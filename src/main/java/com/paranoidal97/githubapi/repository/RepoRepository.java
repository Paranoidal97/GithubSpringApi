package com.paranoidal97.githubapi.repository;

import com.paranoidal97.githubapi.helpers.RepoId;
import com.paranoidal97.githubapi.model.entity.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<Repo, RepoId> {

}
