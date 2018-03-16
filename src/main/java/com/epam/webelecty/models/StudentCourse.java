package com.epam.webelecty.models;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
public class StudentCourse {
    private int id;
    private int courseId;
    private int studentId;
    private int studentMark;
    private String studentFeedback;

    public StudentCourse(int courseId, int studentId, int studentMark, String studentFeedback) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.studentMark = studentMark;
        this.studentFeedback = studentFeedback;
    }
}
