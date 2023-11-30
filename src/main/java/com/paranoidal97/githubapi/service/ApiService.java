package com.paranoidal97.githubapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paranoidal97.githubapi.controller.GithubClient;
import com.paranoidal97.githubapi.model.RepoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final GithubClient githubClient;
    public List<RepoDTO> getOwnerRepos(String owner) {
        List<RepoDTO> githubDetails = githubClient.getGithubDetails(owner);
        return githubDetails;
    }

    public RepoDTO getGithubDetails(String owner, String repo){
        RepoDTO githubDetails = githubClient.getGithubDetails(owner, repo);
        return githubDetails;
    }
}
