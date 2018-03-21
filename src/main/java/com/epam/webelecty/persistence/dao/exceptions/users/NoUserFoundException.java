package com.epam.webelecty.persistence.dao.exceptions.users;

public class NoUserFoundException extends RuntimeException {

    public NoUserFoundException() {
    }

    public NoUserFoundException(String message) {
        super(message);
    }

    public NoUserFoundException(Throwable cause) {
        super(cause);
    }
}
