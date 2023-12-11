package com.paranoidal97.githubapi.service;

import com.paranoidal97.githubapi.controller.GithubClient;
import com.paranoidal97.githubapi.data.TestDataFactory;
import com.paranoidal97.githubapi.exception.BadRequestException;
import com.paranoidal97.githubapi.model.dto.RepoDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class GithubDetailsServiceTest {
    @Mock
    GithubClient githubClient;
    @InjectMocks
    GithubDetailsService githubDetailsService;

    @Test
    void getGithubDetailsList_OwnerExist_ReposReturned() {
        //given
        List<RepoDTO> repos = TestDataFactory.creapeSampleReposDto();

        Mockito.when(githubClient.getGithubDetails(any())).thenReturn(repos);
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
        Mockito.when(githubClient.getGithubDetails(any()))
                .thenReturn(null);

        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubDetailsService.getOwnerRepos("any");
        });
        //then
        assertEquals("Your request is bad or none repositories was found", exception.getMessage());
    }

    @Test
    void getGithubDetails_OwnerExist_RepoExist_DetailsReturned() {
        //given
        RepoDTO repo = TestDataFactory.createSampleRepoDto();
        Mockito.when(githubClient.getGithubDetails(any(), any()))
                .thenReturn(repo);
        //when
        var result = githubDetailsService.getGithubDetails(any(), any());
        //then
    }

    @Test
    void getGithubDetails_OwnerExist_RepoNotExist_BadRequestException() {
        //given
        Mockito.when(githubClient.getGithubDetails(any(), any()))
                .thenReturn(null);
        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubDetailsService.getGithubDetails(any(), any());
        });
        //then
        assertEquals("Your request is bad or none repositorie was found", exception.getMessage());
    }

    @Test
    void getGithubDetails_OwnerExist_RepoExist_BadRequestException() {
        //given
        Mockito.when(githubClient.getGithubDetails(any(), any()))
                .thenReturn(null);
        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubDetailsService.getGithubDetails(any(), any());
        });
        //then
        assertEquals("Your request is bad or none repositorie was found", exception.getMessage());
    }

    @Test
    void getGithubDetails_OwnerNotExist_RepoNotExist_BadRequestException() {
        //given
        Mockito.when(githubClient.getGithubDetails(any(), any()))
                .thenReturn(null);
        //when
        Exception exception = assertThrows(BadRequestException.class, () -> {
            githubDetailsService.getGithubDetails(any(), any());
        });
        //then
        assertEquals("Your request is bad or none repositorie was found", exception.getMessage());
    }

    @Test
    //TODO
    void saveRepostory_RepositoryDoesNotExists_RepositoryReturned(){

    }

    @Test
        //TODO
    void saveRepostory_RepositoryExists_DataAlreadyExistException(){

    }

    @Test
        //TODO
    void editRepository_RepositoryExists_RepositoryReturned(){

    }

    @Test
        //TODO
    void editRepository_RepositoryDoesNotExists_DataNotFoundException(){

    }

    @Test
        //TODO
    void deleteRepository_RepositoryExists_RepositoryDeleted(){

    }

    @Test
        //TODO
    void deleteRepository_RepositoryDoesNotExists_DataNotFoundException(){

    }
}
