package sbaHibernateMaven.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sbaHibernateMaven.entity.Course;
import sbaHibernateMaven.service.CourseService;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceTest {

    private static CourseService courseService;

    @BeforeAll
    public static void setUp(){
        courseService = new CourseService();
    }

    @Test
    public void getCourseTest(){
        //expected
        Course expected = new Course();
        expected.setCId(1);
        expected.setCName("boot camp");
        expected.setCInstructorName("Eric");

        //when
        Course actual = courseService.getCourseById(1);

        //then
        Assertions.assertEquals(expected.getCId(), actual.getCId());
        Assertions.assertEquals(expected.getCName(), actual.getCName());
        Assertions.assertEquals(expected.getCInstructorName(), actual.getCInstructorName());
    }

    @Test
    public void getAnotherCourseTest(){
        //expected
        Course expected = new Course();
        expected.setCId(2);
        expected.setCName("Mathematics");
        expected.setCInstructorName("Eustace Niemetz");

        //when
        Course actual = courseService.getCourseById(2);

        //then
        Assertions.assertEquals(expected.getCId(), actual.getCId());
        Assertions.assertEquals(expected.getCName(), actual.getCName());
        Assertions.assertEquals(expected.getCInstructorName(), actual.getCInstructorName());
//        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void getAllCoursesTest(){
        //given
        List<Course> expected = new ArrayList<>();
        Course expectedCourse1 = new Course(1, "boot camp", "Eric");
        Course expectedCourse2 = new Course(2, "Mathematics", "Eustace Niemetz");
        Course expectedCourse3 = new Course(3, "Anatomy", "Reynolds Pastor");
        Course expectedCourse4 = new Course(4, "Organic Chemistry", "Odessa Belcher");
        Course expectedCourse5 = new Course(5, "Physics", "Dani Swallow");
        Course expectedCourse6 = new Course(6, "Digital Logic", "Glenden Reilingen");
        Course expectedCourse7 = new Course(7, "Object Oriented Programming", "Giselle Ardy");
        Course expectedCourse8 = new Course(8, "Data Structures", "Carolan Stoller");
        Course expectedCourse9 = new Course(9, "Politics", "Carmita De Maine");
        Course expectedCourse10 = new Course(10, "Art", "Kingsly Doxsey");

        expected.add(expectedCourse1);
        expected.add(expectedCourse2);
        expected.add(expectedCourse3);
        expected.add(expectedCourse4);
        expected.add(expectedCourse5);
        expected.add(expectedCourse6);
        expected.add(expectedCourse7);
        expected.add(expectedCourse8);
        expected.add(expectedCourse9);
        expected.add(expectedCourse10);

        //when
        List<Course> actual = courseService.getAllCourses();

        //then
        Assertions.assertEquals(expected.toString(), actual.toString());
    }
}
