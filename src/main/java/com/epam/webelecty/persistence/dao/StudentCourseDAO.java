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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Log4j2
@Component
public class StudentCourseDAO implements DAO<StudentCourse> {

    @Value("${db.name}")
    private String databaseName;

    private ConnectionPool connectionPool;

    @Autowired
    public StudentCourseDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Set<StudentCourse> getAllEntries() {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT id, courseId, studentId, studentMark, studentFeedback FROM %s.student_course", databaseName);
        Set<StudentCourse> studentCourseSet = new TreeSet<>();
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()){
            while (rs.next()) {
                StudentCourse studentCourse = parseStudentCourse(rs);
                if (studentCourse != null) studentCourseSet.add(studentCourse);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return studentCourseSet;
    }

    public Set<Course> getAllCoursesByStudent(User user) {
        Connection connection = connectionPool.getConnection();
        Set<Course> courseSet = new HashSet<>();
        String sql = String.format("SELECT courses.courseId, name, tutorId, annotation, status from %s.student_course" +
                        " JOIN %s.courses ON %s.courses.courseId = %s.student_course.courseId where studentId=%d",
                databaseName, databaseName, databaseName, databaseName, user.getUserId());
        fillCoursesSet(connection, courseSet, sql);
        return courseSet;
    }

    public Set<Course> getAllCoursesByStudentId(int id) {
        Connection connection = connectionPool.getConnection();
        Set<Course> courseSet = new HashSet<>();
        String sql = String.format("SELECT courses.courseId, name, tutorId, annotation, status from %s.student_course" +
                        " JOIN %s.courses ON %s.courses.courseId = %s.student_course.courseId where studentId=%d",
                databaseName, databaseName, databaseName, databaseName, id);
        fillCoursesSet(connection, courseSet, sql);
        return courseSet;
    }

    public Set<User> getAllStudentsByCourse(Course course) {
        Connection connection = connectionPool.getConnection();
        Set<User> studentSet = new HashSet<>();
        String sql = String.format("SELECT users.userId, email, pass, name, lastName, role from %s.student_course JOIN " +
                        "%s.users ON %s.student_course.studentId = %s.users.userId where courseId=%d",
                databaseName, databaseName, databaseName, databaseName, course.getCourseId());
        fillUserSet(connection, studentSet, sql);
        return studentSet;
    }

    public Set<User> getAllStudentsByCourseId(int id) {
        Connection connection = connectionPool.getConnection();
        Set<User> studentSet = new HashSet<>();
        String sql = String.format("SELECT users.userId, email, pass, name, lastName, role from %s.student_course JOIN " +
                        "%s.users ON %s.student_course.studentId = %s.users.userId where courseId=%d",
                databaseName, databaseName, databaseName, databaseName, id);
        fillUserSet(connection, studentSet, sql);
        return studentSet;
    }

    @Override
    public StudentCourse updateEntry(StudentCourse entry) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("UPDATE %s.student_course SET courseId=%d, studentId=%d, studentMark=%d, studentFeedback='%s' WHERE id=%d",
                databaseName, entry.getCourseId(), entry.getStudentId(), entry.getStudentMark(),
                entry.getStudentFeedback(), entry.getId());
        executeSqlStatement(connection, sql);
        return entry;
    }

    @Override
    public void removeById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("DELETE FROM %s.student_course WHERE id=%d", databaseName, id);
        executeSqlStatement(connection, sql);
    }

    @Override
    public StudentCourse insert(StudentCourse entry) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("INSERT INTO %s.student_course(courseId, studentId, studentMark, studentFeedback) VALUES(%d, %d, %d, '%s')",
                databaseName, entry.getCourseId(), entry.getStudentId(), entry.getStudentMark(), entry.getStudentFeedback() != null ? entry.getStudentFeedback() : "No feedback yet.");
        executeSqlStatement(connection, sql);
        return entry;
    }

    public void insert(User student, Course course) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("INSERT INTO %s.student_course(courseId, studentId) VALUES(%d, %d)",
                databaseName, course.getCourseId(), student.getUserId());
        executeSqlStatement(connection, sql);
    }

    @Override
    public StudentCourse getById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT id, courseId, studentId, studentMark, studentFeedback FROM %s.student_course WHERE id=%d",
                databaseName, id);
        StudentCourse sc = null;
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()){
            if (rs.next()) sc = parseStudentCourse(rs);
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return sc;
    }

    public static StudentCourse parseStudentCourse(ResultSet rs) {
        StudentCourse sc;
        try {
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
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return sc;
    }

    private void fillUserSet(Connection connection, Set<User> studentSet, String sql) {
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()){
            while (rs.next()) {
                studentSet.add(UserDAO.parseUser(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void fillCoursesSet(Connection connection, Set<Course> courseSet, String sql) {
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            if (rs.next()) {
                while (rs.next()) {
                    courseSet.add(CourseDAO.parseCourse(rs));
                }
            }
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void executeSqlStatement(Connection connection, String sql) {
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}