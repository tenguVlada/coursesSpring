package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.mapper.CourseMapper;
import com.squirrel.courses.dataaccess.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * Class CourseDAO realizes data-access methods related to working with table Course.
 *
 * @author    Maxim Tytskiy
 */
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
        String sql = "UPDATE course SET course_name = ?, theme = ?, description = ? WHERE id=?";

        Object [] params = new Object[]{course.getCourseName(), course.getTheme(), course.getDescription(), course.getId()};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
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
            return getJdbcTemplate().query(sql, params, mapper);
        } catch (EmptyResultDataAccessException e){
            return Collections.emptyList();
        }
    }

    @Override
    public List<Course> findCoursesByTheme(String theme) {
        String sql = CourseMapper.BASE_SQL + " WHERE theme = ?";

        Object[] params = new Object[]{theme};
        CourseMapper mapper = new CourseMapper();

        try {
            return getJdbcTemplate().query(sql, params, mapper);
        } catch (EmptyResultDataAccessException e){
            return Collections.emptyList();
        }
    }

    @Override
    public Course findCourseByID(int id) {
        String sql = CourseMapper.BASE_SQL + " WHERE id = ?";

        Object[] params = new Object[]{id};
        CourseMapper mapper = new CourseMapper();

        try {
            return getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Course> findAllCourses() {
        String sql = CourseMapper.BASE_SQL;

        CourseMapper mapper = new CourseMapper();

        try {
            return getJdbcTemplate().query(sql, mapper);
        } catch (EmptyResultDataAccessException e){
            return Collections.emptyList();
        }
    }

    @Override
    public List<Course> findCoursesByName(String name) {
        String sql = CourseMapper.BASE_SQL + " WHERE course_name LIKE \"%\" ? \"%\" GROUP BY id";

        Object[] params = new Object[]{name};
        CourseMapper mapper = new CourseMapper();

        try {
            return getJdbcTemplate().query(sql, params,  mapper);
        } catch (EmptyResultDataAccessException e){
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> findCoursesThemes() {
        String sql = CourseMapper.THEME_SQL;

        try {
            return this.getJdbcTemplate().queryForList(sql, String.class);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> findCoursesThemesByLecturer(String lecturer) {
        String sql = CourseMapper.THEME_SQL + " WHERE lecturer = ?";

        Object[] params = new Object[]{lecturer};

        try {
            return this.getJdbcTemplate().queryForList(sql, params, String.class);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
