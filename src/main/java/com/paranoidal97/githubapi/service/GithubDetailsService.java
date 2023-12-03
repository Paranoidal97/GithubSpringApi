package com.paranoidal97.githubapi.service;

import com.paranoidal97.githubapi.controller.GithubClient;
import com.paranoidal97.githubapi.exception.BadRequestException;
import com.paranoidal97.githubapi.model.RepoDTO;
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

    public Optional<List<RepoDTO>> getOwnerRepos(String owner) {
        Optional<List<RepoDTO>> githubDetails = Optional.ofNullable(githubClient.getGithubDetails(owner));
        if(githubDetails.isEmpty() || githubDetails == null){
            throw new BadRequestException("Your request is bad or none repositories was found");
        }
        return githubDetails;
    }

    public Optional<RepoDTO> getGithubDetails(String owner, String repo) {
        Optional<RepoDTO> githubDetails = Optional.ofNullable(githubClient.getGithubDetails(owner, repo));
        if(githubDetails.isEmpty() || githubDetails == null){
            throw new BadRequestException("Your request is bad or none repositorie was found");
        }
        return githubDetails;
    }
}
