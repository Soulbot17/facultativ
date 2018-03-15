package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.StudentCourse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.ResultSet;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCourseDAO {
    Connection connection;

    @SneakyThrows
    public StudentCourse getStudentCourseById(int id) {
        ResultSet rs = connection
                .prepareStatement(String.format("SELECT * FROM student_course WHERE id = %d", id))
                .executeQuery();

        int courseId = 0;
        int studentId = 0;
        int studentMark = 0;
        String studentFeedback = "";

        while (rs.next()){
            courseId = rs.getInt("courseId");
            studentId = rs.getInt("studentId");
            studentMark = rs.getInt("studentMark");
            studentFeedback = rs.getString("studentFeedback");
        }
        return new StudentCourse()
                .setId(id)
                .setCourseId(courseId)
                .setStudentId(studentId)
                .setStudentMark(studentMark)
                .setStudentFeedback(studentFeedback);
    }
}
