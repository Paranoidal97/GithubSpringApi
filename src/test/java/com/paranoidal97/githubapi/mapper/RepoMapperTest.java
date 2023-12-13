package com.paranoidal97.githubapi.mapper;

import com.paranoidal97.githubapi.data.TestDataFactory;
import com.paranoidal97.githubapi.model.dto.RepoDTO;
import com.paranoidal97.githubapi.model.entity.Repo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepoMapperTest {
    RepoMapper repoMapper = Mappers.getMapper(RepoMapper.class);

    @ParameterizedTest
    @MethodSource("entityData")
    void toDtoTest(Repo repo){
        var result = repoMapper.toDto(repo);

        assertAll(
                () -> assertEquals(repo.getDescription(), result.getDescription()),
                () -> assertEquals(repo.getUrl(), result.getUrl()),
                () -> assertEquals(repo.getStars(), result.getStars())
        );
    }

    @ParameterizedTest
    @MethodSource("dtoData")
    void toEntityTest(RepoDTO repoDTO){
        var result = repoMapper.toEntity(repoDTO);

        assertAll(
                () -> assertEquals(repoDTO.getDescription(), result.getDescription()),
                () -> assertEquals(repoDTO.getUrl(), result.getUrl()),
                () -> assertEquals(repoDTO.getStars(), result.getStars()),
                () -> assertEquals(repoDTO.getCreated_at(), result.getCreatedAt())
        );
    }

    public static Stream<Arguments> entityData(){
        Repo sampleRepo1 = TestDataFactory.createSampleRepo();
        Repo sampleRepo2 = TestDataFactory.createSampleRepo();

        return Stream.of(
                Arguments.of(sampleRepo1),
                Arguments.of(sampleRepo2)
        );
    }

    public static Stream<Arguments> dtoData(){
        RepoDTO sampleRepo1 = TestDataFactory.createSampleRepoDto();
        RepoDTO sampleRepo2 = TestDataFactory.createSampleRepoDto();

        return Stream.of(
                Arguments.of(sampleRepo1),
                Arguments.of(sampleRepo2)
        );
    }


}
