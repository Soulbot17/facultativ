package com.epam.webelecty.persistence.dao.exceptions.course;

public class NoCourseFoundException extends RuntimeException {

    public NoCourseFoundException() {
    }

    public NoCourseFoundException(String message) {
        super(message);
    }

    public NoCourseFoundException(Throwable cause) {
        super(cause);
    }
}
