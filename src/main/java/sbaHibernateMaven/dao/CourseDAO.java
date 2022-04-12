package sbaHibernateMaven.dao;

import sbaHibernateMaven.entity.Course;

import java.util.List;

public interface CourseDAO {
    public List<Course> getAllCourses();
}
