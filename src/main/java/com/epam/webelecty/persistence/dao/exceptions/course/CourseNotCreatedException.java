package com.epam.webelecty.persistence.dao.exceptions.course;

public class CourseNotCreatedException extends RuntimeException {

    public CourseNotCreatedException() {
    }

    public CourseNotCreatedException(String message) {
        super(message);
    }

    public CourseNotCreatedException(Throwable cause) {
        super(cause);
    }
}
