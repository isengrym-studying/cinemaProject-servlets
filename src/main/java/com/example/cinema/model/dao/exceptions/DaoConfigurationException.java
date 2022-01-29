package com.example.cinema.model.dao.exceptions;

public class DaoConfigurationException extends RuntimeException {
    public DaoConfigurationException(String message) {
        super(message);
    }
    public DaoConfigurationException(Throwable cause) { super(cause); }
    public DaoConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
