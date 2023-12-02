package com.paranoidal97.githubapi.controller;

import com.paranoidal97.githubapi.model.RepoDTO;
import com.paranoidal97.githubapi.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github-details")
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;

    @GetMapping("/{owner}")
    @Operation
    @ApiResponses
    public List<RepoDTO> ownerRepos(@PathVariable String owner) {
        return apiService.getOwnerRepos(owner);
    }

    @GetMapping("/{owner}/{repo}")
//    @Operation("dxf")
    @ApiResponses
    public RepoDTO getGithubDetails(@PathVariable String owner, @PathVariable String repo) {
        return apiService.getGithubDetails(owner, repo);
    }
}
