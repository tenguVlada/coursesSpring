package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Course;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseDAO implements ICourseDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO course (lecturer, course_name, theme, description) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, course.getLecturer());
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getTheme());
            ps.setString(4, course.getDescription());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return true;
    }

    @Override
    public boolean editCourse(Course course) {
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        String sql = "DELETE FROM course WHERE id LIKE ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return true;
    }

    @Override
    public List<Course> findCoursesByLecturer(String lecturer) {
        String sql = "SELECT id, course_name, theme, description FROM course WHERE lecturer = ? ";
        Connection conn = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, lecturer);

            rs = ps.executeQuery();

            Course course;
            while (rs.next()){
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setTheme(rs.getString("theme"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));

                courseList.add(course);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return courseList;
    }

    @Override
    public List<Course> findCoursesByTheme(String theme) {
        String sql = "SELECT id, course_name, description FROM course WHERE theme = ? ";
        Connection conn = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, theme);

            rs = ps.executeQuery();

            Course course;
            while (rs.next()){
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setTheme(theme);
                course.setDescription(rs.getString("description"));

                courseList.add(course);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return courseList;
    }

    @Override
    public List<Course> findAllCourses() {
        String sql = "SELECT id, course_name, theme, description FROM course";
        Connection conn = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            Course course;
            while (rs.next()){
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setTheme(rs.getString("theme"));

                courseList.add(course);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return courseList;
    }

    @Override
    public List<Course> findCoursesByName(String name) {
        String sql = "SELECT id, lecturer, course_name, theme, description, " +
                "FROM course WHERE course_name LIKE \"%\" ? \"%\" GROUP BY id";
        Connection conn = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            rs = ps.executeQuery();

            Course course;
            while (rs.next()){
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setLecturer(rs.getString("lecturer"));
                course.setCourseName(rs.getString("course_name"));
                course.setTheme(rs.getString("theme"));
                course.setDescription(rs.getString("description"));

                courseList.add(course);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return courseList;
    }

    @Override
    public List<String> findCoursesThemes() {
        String sql = "SELECT DISTINCT theme FROM course";
        Connection conn = null;
        ResultSet rs = null;
        List<String> themeList = new ArrayList();

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()){
                themeList.add(rs.getString("theme"));
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return themeList;
    }

    @Override
    public List<String> findCoursesThemesByLecturer(String lecturer) {
        String sql = "SELECT DISTINCT theme FROM course WHERE lecturer = ?";
        Connection conn = null;
        ResultSet rs = null;
        List<String> themeList = new ArrayList();

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, lecturer);

            rs = ps.executeQuery();

            while (rs.next()){
                themeList.add(rs.getString("theme"));
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return themeList;
    }
}