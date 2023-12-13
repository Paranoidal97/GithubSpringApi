package com.paranoidal97.githubapi.controller;

import com.paranoidal97.githubapi.model.dto.RepoDTO;
import com.paranoidal97.githubapi.service.GithubDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GithubDetailsController {
    private final GithubDetailsService apiService;

    @GetMapping("/repositories/{owner}")
    @Operation(summary = "Get user repositories", tags = "User repositories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = RepoDTO.class)))})
    })
    public Optional<List<RepoDTO>> ownerRepos(@PathVariable String owner) {
        return apiService.getOwnerRepos(owner);
    }

    @GetMapping("/repositories/{owner}/{repo}")
    @Operation(summary = "Get repository details", tags = "Repository details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = RepoDTO.class)))})
    })
    public Optional<RepoDTO> getGithubDetails(@PathVariable String owner, @PathVariable String repo) {
        return apiService.getGithubDetails(owner, repo);
    }

    @PostMapping("/repositories/{owner}/{repository-name}")
    // TODO
    @Operation(summary = "Save repository details", tags = "Repository details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Repository details saved successfully"),
            @ApiResponse(responseCode = "404", description = "Repository not found"),
    })
    public RepoDTO saveRepository(@PathVariable String owner, @PathVariable String repo) {
        return apiService.saveRepository(owner, repo);
    }

    @GetMapping("/local/repositories/{owner}/{repo}")
    @Operation(summary = "Get local repository details", tags = "Repository details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Repository details "),
            @ApiResponse(responseCode = "404", description = "Repository not found"),
    })
    public Optional<RepoDTO> getLocalGithubDetails(@PathVariable String owner, @PathVariable String repo) {
        return apiService.getGithubDetails(owner, repo);
    }

    @PutMapping("/repositories/{owner}/{repository-name}")
    // TODO
    @Operation(summary = "Edit repository details", tags = "Repository details")
    public RepoDTO editRepository(@PathVariable String owner, @PathVariable String repo) {
        return apiService.editRepository(owner, repo);
    }

    // TODO
    @Operation(summary = "Get repository details", tags = "Repository details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = RepoDTO.class)))})
    })
    @DeleteMapping("/repositories/{owner}/{repository-name}")
    public void deleteRepository(@PathVariable String owner, @PathVariable String repo) {
        apiService.deleteRepository(owner, repo);
    }

}
