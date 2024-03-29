package com.example.cinema.service.exceptions;

/**
 * Custom exception for service-layer
 *
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable cause) { super(cause); }
    public ServiceException(String message, Throwable cause) { super(message, cause); }

}
