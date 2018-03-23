package com.epam.webelecty.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse implements Comparable<StudentCourse> {
    private int id;
    private int courseId;
    private int studentId;
    private int studentMark;
    private String studentFeedback;

    @Override
    public int compareTo(StudentCourse o) {
        return id-o.id;
    }
}
