package com.paranoidal97.githubapi.mapper;

import com.paranoidal97.githubapi.model.dto.RepoDTO;
import com.paranoidal97.githubapi.model.entity.Repo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface RepoMapper {
    RepoDTO toDto(Repo repo);

    Repo toEntity(RepoDTO repoDTO);
}
