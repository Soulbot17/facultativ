package com.epam.webelecty.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCourse {
    int id;
    int courseId;
    int studentId;
    int studentMark;
    String studentFeedback;
}
