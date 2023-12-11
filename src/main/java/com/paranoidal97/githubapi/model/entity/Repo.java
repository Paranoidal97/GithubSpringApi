package com.paranoidal97.githubapi.model.entity;

import com.paranoidal97.githubapi.helpers.RepoId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table
public class Repo {

    @EmbeddedId
    private RepoId id;
    private String fullName;
    private String description;
    private String url;
    private Integer stars;
    private LocalDateTime createdAt;

    public Repo(String owner, String repository, String fullName, String description, String url, Integer stars, LocalDateTime createdAt) {
        RepoId id = new RepoId(owner, repository);
        this.id = id;
        this.fullName = fullName;
        this.description = description;
        this.url = url;
        this.stars = stars;
        this.createdAt = createdAt;
    }
}
