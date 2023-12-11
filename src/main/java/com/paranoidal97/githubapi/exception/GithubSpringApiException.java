package com.paranoidal97.githubapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class GithubSpringApiException extends RuntimeException {
    private final HttpStatus status;

    public GithubSpringApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
