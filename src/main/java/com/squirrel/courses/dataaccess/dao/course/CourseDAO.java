package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.mapper.CourseMapper;
import com.squirrel.courses.dataaccess.mapper.QuestionMapper;
import com.squirrel.courses.dataaccess.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CourseDAO extends JdbcDaoSupport implements ICourseDAO {

    @Autowired
    public CourseDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    @Override
    public boolean addCourse(Course course) {
        String sql = CourseMapper.INSERT_SQL + " VALUES(?, ?, ?, ?)";

        Object [] params = new Object[]{course.getLecturer(), course.getCourseName(), course.getTheme(), course.getDescription()};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public boolean editCourse(Course course) {
        return false;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        String sql = CourseMapper.DELETE_SQL + " WHERE id = ?";

        Object[] params = new Object[]{courseId};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public List<Course> findCoursesByLecturer(String lecturer) {
        String sql = CourseMapper.BASE_SQL + " WHERE lecturer = ?";

        Object[] params = new Object[]{lecturer};
        CourseMapper mapper = new CourseMapper();

        try {
            List<Course> courses = getJdbcTemplate().query(sql, params, mapper);                        //возможно неправильно
            return courses;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Course> findCoursesByTheme(String theme) {
        String sql = CourseMapper.BASE_SQL + " WHERE theme = ?";

        Object[] params = new Object[]{theme};
        CourseMapper mapper = new CourseMapper();

        try {
            List<Course> courses = getJdbcTemplate().query(sql, params, mapper);                        //возможно неправильно
            return courses;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Course findCourseByID(int id) {
        String sql = CourseMapper.BASE_SQL + " WHERE id = ?";

        Object[] params = new Object[]{id};
        CourseMapper mapper = new CourseMapper();

        try {
            Course course = getJdbcTemplate().queryForObject(sql, params, mapper);                        //возможно неправильно
            return course;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Course> findAllCourses() {
        String sql = CourseMapper.BASE_SQL;

        CourseMapper mapper = new CourseMapper();

        try {
            List<Course> courses = getJdbcTemplate().query(sql, mapper);                                //возможно неправильно
            return courses;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Course> findCoursesByName(String name) {
        String sql = CourseMapper.BASE_SQL + " WHERE course_name LIKE \"%\" ? \"%\" GROUP BY id";

        Object[] params = new Object[]{name};
        CourseMapper mapper = new CourseMapper();

        try {
            List<Course> courses = getJdbcTemplate().query(sql, params,  mapper);                       //возможно неправильно
            return courses;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<String> findCoursesThemes() {
        String sql = CourseMapper.THEME_SQL;

        try {
            List<String> themes = this.getJdbcTemplate().queryForList(sql, String.class);               //возможно неправильно
            return themes;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<String> findCoursesThemesByLecturer(String lecturer) {
        String sql = CourseMapper.THEME_SQL + " WHERE lecturer = ?";

        Object[] params = new Object[]{lecturer};

        try {
            List<String> themes = this.getJdbcTemplate().queryForList(sql, params, String.class);       //возможно неправильно
            return themes;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
