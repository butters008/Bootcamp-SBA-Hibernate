package sbaHibernateMaven.service;

import sbaHibernateMaven.dao.CourseDAO;
import sbaHibernateMaven.entity.Course;
import sbaHibernateMaven.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseService implements CourseDAO {
    //Boiler plate code
    private static final String PERSISTENCE_UNIT_NAME = "hibernateSBA";
    private static EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    Course course = new Course();

    public Course getCourseById(int id) {
        //BP Code
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        try{
            //Making the query
            String sql = "SELECT c FROM Course c WHERE c.cId = :id";
            TypedQuery<Course> query = entityMgr.createQuery(sql, Course.class);
            query.setParameter("id", id);

            //executing the query
            Course result = query.getSingleResult();

            //returning the result
            return result;

        }catch (Exception e){
            System.out.println(e + " => Could not find course by that id");
            return null;
        }
    }


    @Override
    public List<Course> getAllCourses() {
        //BP Code
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        try{
            //Making the query
            String sql = "SELECT c FROM Course c";
            TypedQuery<Course> query = entityMgr.createQuery(sql, Course.class);

            //executing the query
            List<Course> manyCourses = query.getResultList();

            //Returning the result
            return manyCourses;
        }
        catch (Exception e){
            System.out.println(e + " => Could not get all the courses");
            return null;
        }


    }
}
