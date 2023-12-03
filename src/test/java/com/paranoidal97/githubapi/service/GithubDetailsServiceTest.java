package com.paranoidal97.githubapi.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.paranoidal97.githubapi.controller.GithubClient;
import com.paranoidal97.githubapi.data.TestDataFactory;
import com.paranoidal97.githubapi.exception.BadRequestException;
import com.paranoidal97.githubapi.model.RepoDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.paranoidal97.githubapi.data.WireMockStubs.setupMockGithubClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GithubDetailsServiceTest {
    @Mock
    GithubClient githubClient;

    @InjectMocks
    GithubDetailsService githubDetailsService;

    @Test
    void getGithubDetailsList_OwnerExist_ReposReturned() {
        //given
        List<RepoDTO> repos = TestDataFactory.creapeSampleRepos();
        Mockito.when(githubClient.getGithubDetails("any")).thenReturn(repos);
        //when
        var result = githubDetailsService.getOwnerRepos("any");
        //then
        Assertions.assertThat(result)
                .usingFieldByFieldValueComparator()
                .containsSame(repos);
    }

    @Test
    void getGithubDetailsList_OwnerNotExist_BadRequestException() {
        //given
        Mockito.when(githubClient.getGithubDetails("any"))
                .thenReturn(null);
        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubClient.getGithubDetails(any());
        });
        //then
        assertEquals("Your request is bad or none repositories was found", exception.getMessage());
    }

    @Test
    void getGithubDetails_OwnerExist_RepoExist_DetailsReturned() {
        //given
        RepoDTO repo = TestDataFactory.createSampleRepo();
        Mockito.when(githubClient.getGithubDetails("any","any"))
                .thenReturn(repo);
        //when
        var result = githubDetailsService.getGithubDetails("any","any");
        //then
    }

    @Test
    void getGithubDetails_OwnerExist_RepoNotExist_BadRequestException() {
        //given
        RepoDTO repo = TestDataFactory.createSampleRepo();
        Mockito.when(githubClient.getGithubDetails(any(),any()))
                .thenReturn(null);
        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubClient.getGithubDetails(any(),any());
        });
        //then
        assertEquals("Your request is bad or none repositorie was found", exception.getMessage());
    }

    @Test
    void getGithubDetails_OwnerExist_RepoExist_BadRequestException() {
        //given
        RepoDTO repo = TestDataFactory.createSampleRepo();
        Mockito.when(githubClient.getGithubDetails(any(),any()))
                .thenReturn(null);
        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubClient.getGithubDetails(any(),any());
        });
        //then
        assertEquals("Your request is bad or none repositorie was found", exception.getMessage());
    }

    @Test
    void getGithubDetails_OwnerNotExist_RepoNotExist_BadRequestException() {
        //given
        RepoDTO repo = TestDataFactory.createSampleRepo();
        Mockito.when(githubClient.getGithubDetails(any(),any()))
                .thenReturn(null);
        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubClient.getGithubDetails(any(),any());
        });
        //then
        assertEquals("Your request is bad or none repositorie was found", exception.getMessage());
    }
}
