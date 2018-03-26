package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.exceptions.course.NoCourseFoundException;
import com.epam.webelecty.persistence.dao.exceptions.student_course.NoStudentCourseFoundException;
import com.epam.webelecty.persistence.dao.exceptions.student_course.StudentCourseNotCreatedException;
import com.epam.webelecty.persistence.dao.exceptions.users.NoUserFoundException;
import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

import static com.epam.webelecty.persistence.dao.ExecuterSQLDAO.executeSqlStatement;

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
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                StudentCourse studentCourse = parseStudentCourse(rs);
                if (studentCourse != null) studentCourseSet.add(studentCourse);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoStudentCourseFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return studentCourseSet;
    }

    public Set<Course> getAllAvailableCoursesByStudent(User user) {
        Connection connection = connectionPool.getConnection();
        Set<Course> courseSet = new HashSet<>();
        String sql = String.format("SELECT courseId, name, tutorId, annotation, status FROM %s.courses WHERE courses.courseId NOT IN " +
                        "(SELECT courseId FROM %s.student_course WHERE studentId = %d) AND courses.status = 'planned'",
                databaseName, databaseName, user.getUserId());
        fillCoursesSet(connection, courseSet, sql);
        return courseSet;
    }

    public Set<Course> getWaitedCourses(User user) {
        Connection connection = connectionPool.getConnection();
        Set<Course> courseSet = new HashSet<>();
        String sql = String.format("SELECT courses.courseId, name, tutorId, annotation, status from %s.student_course JOIN %s.courses ON %s.courses.courseId = %s.student_course.courseId where studentId=%d and courses.status='planned'",
                databaseName, databaseName, databaseName, databaseName, user.getUserId());
        fillCoursesSet(connection, courseSet, sql);
        return courseSet;
    }

    public Set<Course> getAllCoursesByStudent(User user) {
        Connection connection = connectionPool.getConnection();
        Set<Course> courseSet = new HashSet<>();
        String sql = String.format("SELECT courses.courseId, name, tutorId, annotation, status from %s.student_course JOIN %s.courses ON %s.courses.courseId = %s.student_course.courseId where studentId=%d",
                databaseName, databaseName, databaseName, databaseName, user.getUserId());
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

    public Set<Course> getAllFinishedCoursesByTutor(User user) {
        Connection connection = connectionPool.getConnection();
        Set<Course> courseSet = new HashSet<>();
        String sql = String.format("SELECT courses.courseId, name, tutorId, annotation, status from %s.student_course JOIN %s.courses ON %s.courses.courseId = %s.student_course.courseId where tutorId=%d and " +
                        "courses.status='finished'",
                databaseName, databaseName, databaseName, databaseName, user.getUserId());
        fillCoursesSet(connection, courseSet, sql);
        return courseSet;
    }

    public Set<Course> getAllUnfinishedCoursesByTutor(User user) {
        Connection connection = connectionPool.getConnection();
        Set<Course> courseSet = new HashSet<>();
        String sql = String.format("SELECT courseId, name, tutorId, annotation, status from %s.courses where tutorId = %d and status != 'finished'",
                databaseName, user.getUserId());
        fillCoursesSet(connection, courseSet, sql);
        return courseSet;
    }

    @Override
    public StudentCourse updateEntry(StudentCourse entry) {
        String sql = String.format("UPDATE %s.student_course SET courseId=%d, studentId=%d, studentMark=%d, studentFeedback='%s' WHERE id=%d",
                databaseName, entry.getCourseId(), entry.getStudentId(), entry.getStudentMark(),
                entry.getStudentFeedback(), entry.getId());
        executeSqlStatement(connectionPool, sql);
        return getById(entry.getId());
    }

    public StudentCourse postMarkAndAnnotation(int userId, int courseId, int mark, String annotation) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("select id, courseId, studentId, studentMark, studentFeedback from %s.student_course where studentId=%d and courseId=%d",
                databaseName, userId, courseId);
        StudentCourse studentCourse = null;
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            if (rs.next()) {
                studentCourse = parseStudentCourse(rs);
                studentCourse.setStudentMark(mark);
                studentCourse.setStudentFeedback(annotation);
                updateEntry(studentCourse);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoStudentCourseFoundException(e);
        }
        return studentCourse;
    }

    @Override
    public void removeById(int id) {
        String sql = String.format("DELETE FROM %s.student_course WHERE id=%d", databaseName, id);
        executeSqlStatement(connectionPool, sql);
    }

    public Map<Course, StudentCourse> getFinishedCoursesMap(User user) {
        Connection connection = connectionPool.getConnection();
        Map<Course, StudentCourse> finishedMap = new HashMap<>();
        String sql = String.format("SELECT courses.courseId, name, tutorId, annotation, status, id, studentId, studentMark, " +
                        "studentFeedback FROM %s.courses JOIN %s.student_course ON " +
                        "student_course.courseId = %s.courses.courseId WHERE studentId=%d and status='finished'", databaseName,
                databaseName, databaseName, user.getUserId());
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                Course course = CourseDAO.parseCourse(rs);
                StudentCourse studentCourse = parseStudentCourse(rs);
                finishedMap.put(course, studentCourse);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoStudentCourseFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return finishedMap;
    }

    public Map<User, StudentCourse> getMapStudentCoursesByCourse(Course course, boolean hasFeedback) {
        Connection connection = connectionPool.getConnection();
        Map<User, StudentCourse> finishedMap = new HashMap<>();
        String iSNullRequest = hasFeedback
                ? "is not null and studentFeedback!=''"
                : "is null or studentFeedback=''";

        String sql = String.format("SELECT users.userId, email, pass, name, lastName, role,"
                        + " Id, courseId, studentId, studentMark, studentFeedback FROM %s.users JOIN %s.student_course ON "
                        + "%s.student_course.studentId = %s.users.userId WHERE courseId=%d and studentFeedback " + iSNullRequest
                , databaseName, databaseName, databaseName, databaseName, course.getCourseId(), iSNullRequest);
        fillMapByStudentCourseUser(connection, finishedMap, sql);
        return finishedMap;
    }

    private void fillMapByStudentCourseUser(Connection connection, Map<User, StudentCourse> finishedMap, String sql) {
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                User user = UserDAO.parseUser(rs);
                StudentCourse studentCourse = parseStudentCourse(rs);
                finishedMap.put(user, studentCourse);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoStudentCourseFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public StudentCourse insert(StudentCourse entry) {
        String sql = String.format("INSERT INTO %s.student_course(courseId, studentId, studentMark, studentFeedback) VALUES(%d, %d, %d, '%s')",
                databaseName, entry.getCourseId(), entry.getStudentId(), entry.getStudentMark(), entry.getStudentFeedback() != null ? entry.getStudentFeedback() : "No feedback yet.");
        Connection connection = connectionPool.getConnection();
        StudentCourse studentCourse = null;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) studentCourse = getById(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new StudentCourseNotCreatedException("Student-course entity was not created");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return studentCourse;
    }

    public StudentCourse insert(User student, Course course) {
        String sql = String.format("INSERT INTO %s.student_course(courseId, studentId) VALUES(%d, %d)",
                databaseName, course.getCourseId(), student.getUserId());
        Connection connection = connectionPool.getConnection();
        StudentCourse studentCourse = null;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) studentCourse = getById(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new StudentCourseNotCreatedException("Student_course was not created");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return studentCourse;
    }

    @Override
    public StudentCourse getById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT id, courseId, studentId, studentMark, studentFeedback FROM %s.student_course WHERE id=%d",
                databaseName, id);
        StudentCourse sc = null;
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            if (rs.next()) sc = parseStudentCourse(rs);
        } catch (SQLException e) {
            log.error(e);
            throw new NoStudentCourseFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return sc;
    }

    private static StudentCourse parseStudentCourse(ResultSet rs) {
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
            throw new NoStudentCourseFoundException(e);
        }
        return sc;
    }

    private void fillUserSet(Connection connection, Set<User> studentSet, String sql) {
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                studentSet.add(UserDAO.parseUser(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoUserFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void fillCoursesSet(Connection connection, Set<Course> courseSet, String sql) {
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                courseSet.add(CourseDAO.parseCourse(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoCourseFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}