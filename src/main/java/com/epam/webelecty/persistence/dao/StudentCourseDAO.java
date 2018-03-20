package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class StudentCourseDAO implements DAO<StudentCourse> {

    @Value("${db.table}")
    private String databaseName;

    private ConnectionPool connectionPool;

    @Autowired
    public StudentCourseDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<StudentCourse> getAllEntries() {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT id, courseId, studentId, studentMark, studentFeedback FROM %s.student_course", databaseName);
        List<StudentCourse> scList = new ArrayList<>();
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                StudentCourse studentCourse = parseStudentCourse(rs);
                if (studentCourse != null) scList.add(studentCourse);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return scList;
    }

    public List<Course> getAllCoursesByStudent(int studentId) {
        Connection connection = connectionPool.getConnection();
        List<Course> courseList = new ArrayList<>();
        String sql = String.format("SELECT courseId from %s.student_course where studentId=%d", databaseName, studentId);
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                int id = rs.getInt("courseId");
                sql = String.format("SELECT courseId, name, tutorId, annotation, status FROM %s.courses where courseId=%d",
                        databaseName, id);
                ResultSet userResult = connection.prepareStatement(sql).executeQuery();
                courseList.add(CourseDAO.parseCourse(userResult));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return courseList;
    }

    public List<User> getAllStudentsByCourse(int courseId) {
        Connection connection = connectionPool.getConnection();
        List<User> studentList = new ArrayList<>();
        String sql = String.format("SELECT studentId from %s.student_course where courseId=%d", databaseName, courseId);
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                int id = rs.getInt("studentId");
                sql = String.format("SELECT userId, email, pass, name, lastName, role FROM %s.users WHERE userId=%d",
                        databaseName, id);
                ResultSet userResult = connection.prepareStatement(sql).executeQuery();
                studentList.add(UserDAO.parseUser(userResult));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return studentList;
    }

    @Override
    public void updateById(int id, StudentCourse entry) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("UPDATE %s.student_course SET courseId=%d, studentId=%d, studentMark=%d, studentFeedback='%s' WHERE id=%d",
                databaseName, entry.getCourseId(), entry.getStudentId(), entry.getStudentMark(), entry.getStudentFeedback(), id);
        try {
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void removeById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("DELETE FROM %s.student_course WHERE id=%d", databaseName, id);
        try {
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            log.error(e);
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void insert(StudentCourse entry) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("INSERT INTO %s.student_course(courseId, studentId, studentMark, studentFeedback) VALUES(%d, %d, %d, '%s')",
                databaseName, entry.getCourseId(), entry.getStudentId(), entry.getStudentMark(), entry.getStudentFeedback() != null ? entry.getStudentFeedback() : "No feedback yet.");
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public void insert(User student, Course course) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("INSERT INTO %s.student_course(courseId, studentId) VALUES(%d, %d)",
                databaseName, course.getCourseId(), student.getUserId());
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public StudentCourse getById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT id, courseId, studentId, studentMark, studentFeedback FROM %s.student_course WHERE id=%d",
                databaseName, id);
        StudentCourse sc = null;
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            sc = parseStudentCourse(rs);
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return sc;
    }

    public static StudentCourse parseStudentCourse(ResultSet rs) {
        StudentCourse sc = null;
        try {
            if (rs.next()) {
                int id = rs.getInt("id");
                int courseId = rs.getInt("courseId");
                int studentId = rs.getInt("studentId");
                int studentMark = rs.getInt("studentMark");
                String studentFeedback = rs.getString("studentFeedback");
                sc = StudentCourse.builder()
                        .id(id)
                        .courseId(courseId)
                        .studentId(studentId)
                        .studentMark(studentMark)
                        .studentFeedback(studentFeedback)
                        .build();
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return sc;
    }
}