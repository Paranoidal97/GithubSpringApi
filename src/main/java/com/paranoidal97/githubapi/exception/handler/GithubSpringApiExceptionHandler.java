package com.paranoidal97.githubapi.exception.handler;

import com.paranoidal97.githubapi.exception.BadRequestException;
import com.paranoidal97.githubapi.exception.DataAlreadyExistException;
import com.paranoidal97.githubapi.exception.DataNotFoundException;
import com.paranoidal97.githubapi.model.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GithubSpringApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageDto> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new MessageDto(ex.getMessage(), ex.getStatus(), LocalDateTime.now()));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<MessageDto> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new MessageDto(ex.getMessage(), ex.getStatus(), LocalDateTime.now()));
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<MessageDto> handleDataAlreadyExsistException(DataNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new MessageDto(ex.getMessage(), ex.getStatus(), LocalDateTime.now()));
    }
}
