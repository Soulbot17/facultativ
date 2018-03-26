package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.persistence.dao.exceptions.course.CourseNotCreatedException;
import com.epam.webelecty.persistence.dao.exceptions.course.NoCourseFoundException;
import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static com.epam.webelecty.persistence.dao.ExecuterSQLDAO.executeSqlStatement;

@Log4j2
@Component
public class CourseDAO implements DAO<Course> {

    @Value("${db.name}")
    private String databaseName;

    private ConnectionPool connectionPool;

    @Autowired
    public CourseDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Set<Course> getAllEntries() {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT courseId, name, tutorId, annotation, status FROM %s.courses", databaseName);
        Set<Course> courseSet = new HashSet<>();
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                Course course = parseCourse(rs);
                if (course != null) courseSet.add(course);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoCourseFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return courseSet;
    }

    @Override
    public Course updateEntry(Course entry) {
        String sql = String.format("UPDATE %s.courses SET name='%s', tutorId='%d', annotation='%s', status='%s' WHERE courseId=%d",
                databaseName, entry.getCourseName(), entry.getTutorId(), entry.getAnnotation(), entry.getStatus().name().toLowerCase(), entry.getCourseId());
        executeSqlStatement(connectionPool, sql);
        return getById(entry.getCourseId());
    }

    @Override
    public void removeById(int id) {
        String sql = String.format("DELETE FROM %s.courses WHERE courseId=%d", databaseName, id);
        executeSqlStatement(connectionPool, sql);
    }

    @Override
    public Course insert(Course entry) {
        String sql = String.format("INSERT INTO %s.courses(name, tutorId, annotation, status) VALUES('%s', %d, '%s', '%s')",
                databaseName, entry.getCourseName(), entry.getTutorId(), entry.getAnnotation(), entry.getStatus().name().toLowerCase());
        Connection connection = connectionPool.getConnection();
        Course course = null;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) course = getById(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new CourseNotCreatedException("Course was not created");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return course;
    }

    @Override
    public Course getById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT courseId, name, tutorId, annotation, status FROM %s.courses where courseId=%d", databaseName, id);
        Course course = null;
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            if (rs.next()) course = parseCourse(rs);
        } catch (SQLException e) {
            log.error(e);
            throw new NoCourseFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return course;
    }

    static Course parseCourse(ResultSet rs) {
        Course course;
        try {
            int id = rs.getInt("courseId");
            String name = rs.getString("name");
            int tutorId = rs.getInt("tutorId");
            String annotation = rs.getString("annotation");
            CourseStatus status;
            switch (rs.getString("status").toLowerCase()) {
                case "finished":
                    status = CourseStatus.FINISHED;
                    break;
                case "planned":
                default:
                    status = CourseStatus.PLANNED;
            }
            course = Course.builder()
                    .courseId(id)
                    .courseName(name)
                    .tutorId(tutorId)
                    .annotation(annotation)
                    .status(status)
                    .build();
        } catch (SQLException e) {
            log.error(e);
            throw new CourseNotCreatedException(e);
        }
        return course;
    }
}