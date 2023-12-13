package com.paranoidal97.githubapi.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends GithubSpringApiException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
