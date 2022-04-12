package sbaHibernateMaven.service;

import sbaHibernateMaven.dao.StudentDAO;
import sbaHibernateMaven.entity.Course;
import sbaHibernateMaven.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;

public class StudentService implements StudentDAO {
    //Boiler plate code
    private static final String PERSISTENCE_UNIT_NAME = "hibernateSBA";
    private static EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //Creating instance var for Student Object
    Student student = new Student();

    @Override
    public List<Student> getAllStudents() {
        //BP Code
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        try {
            //Making the query
            String sql = "SELECT s FROM Student s";
            TypedQuery<Student> query = entityMgr.createQuery(sql, Student.class);

            //executing the query
            List<Student> manyStudents = query.getResultList();

            //Returning the result
            return manyStudents;
        }
        catch (Exception e){
            System.out.println(e + " => Could not get the students");
            return null;
        }
    }

    @Override
    public Student getStudentByEmail(String email) {
        //BP Code
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        try{
            //Making the query
            String sql = "SELECT s FROM Student s WHERE s.sEmail = :email";
            TypedQuery<Student> query = entityMgr.createQuery(sql, Student.class);
            query.setParameter("email", email);

            //executing the query
            Student result = query.getSingleResult();

            //returning the result
            return result;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean validateStudent(String email, String password) {
        //BP Code
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        try{
            //Making the query
            String sql = "SELECT s FROM Student s WHERE s.sEmail = :email AND s.sPassword = :password";
            TypedQuery<Student> query = entityMgr.createQuery(sql, Student.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            //execute the query
            Student result = query.getSingleResult();

            //returning the result
            if(result.getSEmail().equals(email) && result.getSPassword().equals(password)){
                return true;
            }

        }catch(Exception e){
            System.out.println(e + " => Could not validate student");
            return false;
        }
        return false;
    }




}
