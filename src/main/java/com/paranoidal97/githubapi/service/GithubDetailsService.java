package com.paranoidal97.githubapi.service;

import com.paranoidal97.githubapi.controller.GithubClient;
import com.paranoidal97.githubapi.exception.BadRequestException;
import com.paranoidal97.githubapi.exception.DataAlreadyExistException;
import com.paranoidal97.githubapi.exception.DataNotFoundException;
import com.paranoidal97.githubapi.helpers.RepoId;
import com.paranoidal97.githubapi.mapper.RepoMapper;
import com.paranoidal97.githubapi.model.dto.RepoDTO;
import com.paranoidal97.githubapi.model.entity.Repo;
import com.paranoidal97.githubapi.repository.RepoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubDetailsService {
    private final GithubClient githubClient;
    private final RepoRepository repoRepository;
    private final RepoMapper repoMapper;

    public Optional<List<RepoDTO>> getOwnerRepos(String owner) {
        Optional<List<RepoDTO>> githubDetails = Optional.ofNullable(githubClient.getGithubDetails(owner));
        if (githubDetails.isEmpty() || githubDetails == null) {
            throw new BadRequestException("Your request is bad or none repositories was found");
        }
        return githubDetails;
    }

    public Optional<RepoDTO> getGithubDetails(String owner, String repo) {
        Optional<RepoDTO> githubDetails = Optional.ofNullable(githubClient.getGithubDetails(owner, repo));
        if (githubDetails.isEmpty() || githubDetails == null) {
            throw new BadRequestException("Your request is bad or none repositorie was found");
        }
        return githubDetails;
    }

    public RepoDTO saveRepository(String owner, String repo) {
        RepoDTO githubRepo = githubClient.getGithubDetails(owner, repo);
        //TODO throw
        Repo entity = repoMapper.toEntity(githubRepo);
        RepoId id = new RepoId(owner, repo);
        Optional<Repo> byId = repoRepository.findById(id);
        if (byId.isPresent()) {
            throw new DataAlreadyExistException("Such repository already exists");
        }
        repoRepository.save(entity);
        return githubRepo;
    }

    public RepoDTO editRepository(String owner, String repo) {
        RepoDTO githubDetails = githubClient.getGithubDetails(owner, repo);
        //TODO throw
        RepoId id = new RepoId(owner, repo);
        Repo entity = repoMapper.toEntity(githubDetails);
        Repo repoToEdite = repoRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("There is no such repo"));
        repoToEdite.setDescription(entity.getDescription());
        repoToEdite.setStars(entity.getStars());
        return repoMapper.toDto(repoToEdite);
    }

    public void deleteRepository(String owner, String repo) {
        RepoId id = new RepoId(owner, repo);
        Repo repoToDelete = repoRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("There is no such repo"));
        repoRepository.delete(repoToDelete);
    }
}
