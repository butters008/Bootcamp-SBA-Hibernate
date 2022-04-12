package sbaHibernateMaven.service;

import sbaHibernateMaven.entity.StudentCourse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentCourseService {
    //Boiler plate code
    private static final String PERSISTENCE_UNIT_NAME = "hibernateSBA";
    private static EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public StudentCourse registerStudentToCourse(StudentCourse studentCourse ) {
        EntityManager entityMgr = emFactoryObj.createEntityManager();
        entityMgr.getTransaction().begin();

        // save the actor to the database
        entityMgr.persist(studentCourse);

        // commit the transaction
        entityMgr.getTransaction().commit();
        entityMgr.clear();
        try{
            return studentCourse;
        }catch (Exception e){
            System.out.println(e + " => Could not register student with that course");
            return null;
        }

    }

    public List<StudentCourse> getStudentCourse(String email) {
        //BP Code
        EntityManager entityMgr = emFactoryObj.createEntityManager();

        String sql = "SELECT sc FROM StudentCourse sc WHERE sc.studentId = :studentId";
        TypedQuery<StudentCourse> query = entityMgr.createQuery(sql, StudentCourse.class);
        query.setParameter("studentId", email);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
