package com.epam.webelecty.persistence.dao.exceptions.users;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException() {
    }

    public UserNotCreatedException(String message) {
        super(message);
    }

    public UserNotCreatedException(Throwable cause) {
        super(cause);
    }
}
