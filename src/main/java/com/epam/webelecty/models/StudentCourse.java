package com.epam.webelecty.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {
    private int id;
    private int courseId;
    private int studentId;
    private int studentMark;
    private String studentFeedback;

    // FIXME: 20.03.18 why do we explicitly use constructor while using lombok annotation about it? google DRY principle
    public StudentCourse(int courseId, int studentId, int studentMark, String studentFeedback) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.studentMark = studentMark;
        this.studentFeedback = studentFeedback;
    }
}
