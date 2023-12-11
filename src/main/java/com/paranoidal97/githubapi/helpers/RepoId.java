package com.paranoidal97.githubapi.helpers;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Embeddable
public class RepoId implements Serializable {
    private String owner;
    private String repository;

}
