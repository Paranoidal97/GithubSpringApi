package com.paranoidal97.githubapi.controller;


import com.paranoidal97.githubapi.model.RepoDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github", url = "${feign.path}")
@Headers("User-Agent: http://developer.github.com/v3/#user-agent-required")
public interface GithubClient {
    @GetMapping("/repos/{owner}/{repo}")
    public RepoDTO getGithubDetails(@PathVariable String owner, @PathVariable String repo);

    //TODO tyo nie maja być voidy
    @GetMapping("/users/{owner}/repos")
    public List<RepoDTO> getGithubDetails(@PathVariable String owner);
}
