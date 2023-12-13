package com.paranoidal97.githubapi.helpers;

import com.paranoidal97.githubapi.exception.BadRequestException;
import com.paranoidal97.githubapi.exception.DataNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 404:
                throw new DataNotFoundException(response.reason());
            case 400:
                throw new BadRequestException(response.reason());
            default:
                throw new BadRequestException("unexpected error");
        }
    }
}
