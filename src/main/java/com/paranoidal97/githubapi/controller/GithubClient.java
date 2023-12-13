package com.paranoidal97.githubapi.controller;


import com.paranoidal97.githubapi.helpers.FeignClientConfig;
import com.paranoidal97.githubapi.model.dto.RepoDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github", url = "${app.githubi.api.url}", configuration = FeignClientConfig.class)
@Headers("User-Agent: http://developer.github.com/v3/#user-agent-required")
public interface GithubClient {
    @GetMapping("/repos/{owner}/{repo}")
    public RepoDTO getGithubDetails(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/users/{owner}/repos")
    public List<RepoDTO> getGithubDetails(@PathVariable String owner);
}
