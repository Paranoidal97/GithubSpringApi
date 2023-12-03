package com.paranoidal97.githubapi.data;

import com.paranoidal97.githubapi.model.RepoDTO;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static List<RepoDTO> creapeSampleRepos() {
        List<RepoDTO> repos = new ArrayList<>();

        RepoDTO repo1 = RepoDTO.builder()
                .title("repo1")
                .description("description1")
                .fullName("fullname1")
                .build();

        RepoDTO repo2 = RepoDTO.builder()
                .title("repo2")
                .description("description2")
                .fullName("fullname2")
                .build();

        repos.add(repo1);
        repos.add(repo2);

        return repos;
    }

    public static RepoDTO createSampleRepo() {
        RepoDTO repo1 = RepoDTO.builder()
                .title("repo1")
                .description("description1")
                .fullName("fullname1")
                .build();

        return repo1;
    }


}
