package com.paranoidal97.githubapi.exception;

import org.springframework.http.HttpStatus;

public class DataAlreadyExistException extends GithubSpringApiException {
    public DataAlreadyExistException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
