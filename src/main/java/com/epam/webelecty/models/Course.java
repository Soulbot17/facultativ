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
}
