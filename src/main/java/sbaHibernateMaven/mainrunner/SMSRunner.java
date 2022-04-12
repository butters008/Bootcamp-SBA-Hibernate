package sbaHibernateMaven.mainrunner;

import sbaHibernateMaven.entity.Course;
import sbaHibernateMaven.entity.Student;
import sbaHibernateMaven.entity.StudentCourse;
import sbaHibernateMaven.service.CourseService;
import sbaHibernateMaven.service.StudentCourseService;
import sbaHibernateMaven.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class SMSRunner {
    private Scanner scanner = new Scanner(System.in);
    private StudentService studentService = new StudentService();
    private CourseService courseService = new CourseService();
    private StudentCourseService  studentCourseService = new StudentCourseService();
    private int flag = 0;
    private Student student;


    public boolean login(){

        do {
            System.out.println("Do you want to (1) log in or (2) quit");
            flag = scanner.nextInt();
            if(flag == 1){
                System.out.println("Please enter your email");
                String login = scanner.next();
                System.out.println("Please enter your password");
                String password = scanner.next();
                if(studentService.validateStudent(login, password)){
                    System.out.println("Logged in");
                    student = studentService.getStudentByEmail(login);
                    return true;
                }
                else{
                    System.out.println("Error: Please try again");
                }
            }
        }while(flag != 2);
        return false;
    }



    public void runLogic(){
        boolean user = login();
        List<Course> allCourse = courseService.getAllCourses();

        while (user){
            List<StudentCourse> registeredCourses = studentCourseService.getStudentCourse(student.getSEmail());
            //Listing all the courses the student is registered in
            System.out.println("User: " + student.getSName());
            System.out.printf("%-10s %-20s %-20s\n", "Course ID","Course Name:","Course Instructor:");
            for (StudentCourse printCourse:registeredCourses) {
                System.out.printf("%-10s %-20s %-20s\n",
                        printCourse.getCourse().getCId(),
                        printCourse.getCourse().getCName(),
                        printCourse.getCourse().getCInstructorName());
            }

            //Ask what the user wants, register or log out
            System.out.println("1) Register to Class\n2) Logout");
            flag = scanner.nextInt();
            if(flag == 2){
                user = false;
            }else{
                System.out.printf("%-10s %-20s %-20s\n", "Course ID", "Course Name", "Course Instructor");
                for (Course course:allCourse) {
                    System.out.printf("%-10s %-20s %-20s\n", course.getCId(), course.getCName(), course.getCInstructorName());
                }
                System.out.println("Which course would you like add?");
                flag = scanner.nextInt();
                try{
                    StudentCourse newCourse = new StudentCourse();
                    Course course = courseService.getCourseById(flag);
                    newCourse.setCourse(course);
                    newCourse.setStudent(student);
                    int counter = 0;
                    for (StudentCourse courseEnrolled:registeredCourses) {
                        counter++;
                        if(courseEnrolled.getCourse().getCName().equals(course.getCName())){
                            System.out.println("Already enrolled");
                            break;
                        }
                        if(counter == registeredCourses.size()){
                            studentCourseService.registerStudentToCourse(newCourse);
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e + " => Not Valid Options");
                }
            }//end of else
        }//end of while
    }//end of method

    public static void main(String[] args) {
        new SMSRunner().runLogic();
    }
}
