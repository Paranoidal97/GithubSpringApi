package com.paranoidal97.githubapi.controller;

import com.paranoidal97.githubapi.model.RepoDTO;
import com.paranoidal97.githubapi.service.GithubDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/github-details")
@RequiredArgsConstructor
public class GithubDetailsController {
    private final GithubDetailsService apiService;

    @GetMapping("/{owner}")
    @Operation(summary = "Get user repositories", tags = "User repositories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = RepoDTO.class)))})
    })
    public Optional<List<RepoDTO>> ownerRepos(@PathVariable String owner) {
        return apiService.getOwnerRepos(owner);
    }

    @GetMapping("/{owner}/{repo}")
    @Operation(summary = "Get repository details", tags = "Repository details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = RepoDTO.class)))})
    })
    public Optional<RepoDTO> getGithubDetails(@PathVariable String owner, @PathVariable String repo) {
        return apiService.getGithubDetails(owner, repo);
    }
}
