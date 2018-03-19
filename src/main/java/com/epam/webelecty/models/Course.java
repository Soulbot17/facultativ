package com.epam.webelecty.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int courseId;
    private String courseName;
    private int tutorId;
    private String annotation;
    private CourseStatus status;

    public Course(String courseName, int tutorId, String annotation, CourseStatus status) {
        this.courseName = courseName;
        this.tutorId = tutorId;
        this.annotation = annotation;
        this.status = status;
    }
}
