package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class CourseDAO implements DAO<Course> {

    @Value("${db.table}")
    private String databaseName;

    private ConnectionPool connectionPool;

    @Autowired
    public CourseDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Course> getAllEntries() {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT courseId, name, tutorId, annotation, status FROM %s.courses", databaseName);
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = parseCourse(rs);
                if (course != null) courses.add(course);
            }

        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return courses;
    }

    @Override
    public void updateById(int id, Course entry) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("UPDATE %s.courses SET name='%s', tutorId='%d', annotation='%s', status='%s' WHERE courseId=%d",
                databaseName, entry.getCourseName(), entry.getTutorId(), entry.getAnnotation(), entry.getStatus().name(), id);
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
        String sql = String.format("DELETE FROM %s.courses WHERE courseId=%d", databaseName, id);
        try {
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Course entry) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("INSERT INTO %s.courses(name, tutorId, annotation, status) VALUES('%s', %d, '%s', '%s')",
                databaseName, entry.getCourseName(), entry.getTutorId(), entry.getAnnotation(), entry.getStatus().name());
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Course getById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT courseId, name, tutorId, annotation, status FROM %s.courses where courseId=%d", databaseName, id);
        Course course = null;
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            course = parseCourse(rs);
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return course;
    }

    public static Course parseCourse(ResultSet rs) {
        Course course = null;
        try {
            if (rs.next()) {
                int id = rs.getInt("courseId");
                String name = rs.getString("name");
                int tutorId = rs.getInt("tutorId");
                String annotation = rs.getString("annotation");
                CourseStatus status;
                switch (rs.getString("status").toLowerCase()) {
                    case "active":
                        status = CourseStatus.ACTIVE;
                        break;
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
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return course;
    }
}