package com.epam.webelecty.persistence.dao.exceptions.student_course;

public class NoStudentCourseFoundException extends RuntimeException {

    public NoStudentCourseFoundException() {
    }

    public NoStudentCourseFoundException(String message) {
        super(message);
    }

    public NoStudentCourseFoundException(Throwable cause) {
        super(cause);
    }
}
