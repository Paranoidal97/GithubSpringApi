package com.paranoidal97.githubapi.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends GithubSpringApiException {

    public DataNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
