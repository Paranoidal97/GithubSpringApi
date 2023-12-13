package com.paranoidal97.githubapi.data;

import com.paranoidal97.githubapi.model.dto.RepoDTO;
import com.paranoidal97.githubapi.model.entity.Repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static List<RepoDTO> creapeSampleReposDto() {
        List<RepoDTO> repos = new ArrayList<>();

        RepoDTO repo1 = RepoDTO.builder()
                .description("description1")
                .fullName("fullname1")
                .build();

        RepoDTO repo2 = RepoDTO.builder()
                .description("description2")
                .fullName("fullname2")
                .build();

        repos.add(repo1);
        repos.add(repo2);

        return repos;
    }

    public static RepoDTO createSampleRepoDto() {
        RepoDTO repo1 = RepoDTO.builder()
                .description("description1")
                .fullName("fullname1")
                .build();

        return repo1;
    }

    public static Repo createSampleRepo() {
        LocalDateTime now = LocalDateTime.now();

        Repo repo1 = new Repo("owner",
                "repo",
                "owner/repo" ,
                "description",
                "urlllll",
                4,
                now );

        return repo1;
    }


}
