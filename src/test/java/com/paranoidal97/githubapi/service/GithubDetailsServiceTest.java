package com.paranoidal97.githubapi.service;

import com.paranoidal97.githubapi.controller.GithubClient;
import com.paranoidal97.githubapi.data.TestDataFactory;
import com.paranoidal97.githubapi.exception.BadRequestException;
import com.paranoidal97.githubapi.exception.DataAlreadyExistException;
import com.paranoidal97.githubapi.exception.DataNotFoundException;
import com.paranoidal97.githubapi.helpers.RepoId;
import com.paranoidal97.githubapi.mapper.RepoMapper;
import com.paranoidal97.githubapi.model.dto.RepoDTO;
import com.paranoidal97.githubapi.model.entity.Repo;
import com.paranoidal97.githubapi.repository.RepoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class GithubDetailsServiceTest {
    GithubClient githubClient;
    RepoRepository repository;
    GithubDetailsService githubDetailsService;
    RepoMapper repoMapper;

    @BeforeEach
    void setup(){
        this.githubClient = Mockito.mock(GithubClient.class);
        this.repository = Mockito.mock(RepoRepository.class);
        this.repoMapper = Mappers.getMapper(RepoMapper.class);
        this.githubDetailsService = new GithubDetailsService(githubClient,repository,repoMapper);
    }

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
    //TODO assercje
    void getGithubDetails_OwnerExist_RepoExist_DetailsReturned() {
        //given
        RepoDTO repo = TestDataFactory.createSampleRepoDto();
        Mockito.when(githubClient.getGithubDetails(any(), any()))
                .thenReturn(repo);
        //when
        var result = githubDetailsService.getGithubDetails(any(), any());
        //then
        System.out.println(result);
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
    void saveRepostory_RepositoryDoesNotExists_RepositoryReturned(){
        //given
        RepoDTO repo = TestDataFactory.createSampleRepoDto();
        Mockito.when(githubClient.getGithubDetails(any(), any()))
                .thenReturn(repo);
        //when
        var result = githubDetailsService.saveRepository("Test", "Test");
        //then
        assertEquals(repo.getDescription(), result.getDescription());
        assertEquals(repo.getStars(), result.getStars());
        assertEquals(repo.getUrl(), result.getUrl());
        assertEquals(repo.getFullName(), repo.getFullName());
    }

    @Test
    void saveRepostory_RepositoryExists_DataAlreadyExistException(){
        //given
        RepoId id = new RepoId("Test", "Test");
        Mockito.when(repository.findById(id))
                .thenReturn(Optional.of(new Repo()));
        //when
        Exception exception = assertThrows(DataAlreadyExistException.class, () -> {
            githubDetailsService.saveRepository("Test", "Test");
        });
        //then
        assertEquals("Such repository already exists", exception.getMessage());
    }

    @Test
        //TODO
    void editRepository_RepositoryExists_RepositoryReturned(){
        //given
        RepoDTO sampleRepoDto = TestDataFactory.createSampleRepoDto();
        Mockito.when(githubClient.getGithubDetails("Test","Test"))
                .thenReturn(sampleRepoDto);
        RepoId id = new RepoId("Test", "Test");
        Repo entity = repoMapper.toEntity(sampleRepoDto);
        Mockito.when(repository.findById(id))
                .thenReturn(Optional.ofNullable(entity));
        //when
        var result = githubDetailsService.editRepository("Test", "Test");
        //then
        System.out.println(result);

    }

    @Test
    void editRepository_RepositoryDoesNotExists_DataNotFoundException(){
        //given
        RepoId id = new RepoId("Test", "Test");
        Mockito.when(repository.findById(id))
                .thenReturn(Optional.empty());
        //when
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
           githubDetailsService.editRepository("Test", "Test");
        });
        //then
        assertEquals("There is no such repo", exception.getMessage());
    }

    @Test
    void deleteRepository_RepositoryExists_RepositoryDeleted(){
        //given
        Repo sampleRepo = TestDataFactory.createSampleRepo();
        Mockito.when(repository.findById(any()))
                .thenReturn(Optional.ofNullable(sampleRepo));
        //when
        githubDetailsService.deleteRepository("test", "test");
        //then
        Mockito.verify(repository, Mockito.times(1)).delete(sampleRepo);
    }

    @Test
    void deleteRepository_RepositoryDoesNotExists_DataNotFoundException(){
        // given
        Mockito.when(repository.findById(any()))
                .thenReturn(Optional.empty());
        // when
        Exception exception = assertThrows(DataNotFoundException.class, () -> {
            githubDetailsService.deleteRepository("owner", "repo");
        });

        // then
        assertEquals("There is no such repo", exception.getMessage());
    }
}
