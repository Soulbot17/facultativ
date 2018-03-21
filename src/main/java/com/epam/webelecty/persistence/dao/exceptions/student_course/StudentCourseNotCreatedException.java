package com.epam.webelecty.persistence.dao.exceptions.student_course;

public class StudentCourseNotCreatedException extends RuntimeException {

    public StudentCourseNotCreatedException() {
    }

    public StudentCourseNotCreatedException(String message) {
        super(message);
    }

    public StudentCourseNotCreatedException(Throwable cause) {
        super(cause);
    }
}
