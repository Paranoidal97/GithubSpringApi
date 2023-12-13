package com.paranoidal97.githubapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class RepoDTO {

    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty
    private String description;
    @JsonProperty("html_url")
    private String url;
    @JsonProperty("stars")
    private Integer stars;
    @JsonProperty("createdAt")
    private LocalDateTime created_at;
}
