package com.paranoidal97.githubapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepoDTO {
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty("full_name")
    private String fullName;

}
