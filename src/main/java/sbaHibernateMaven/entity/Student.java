package sbaHibernateMaven.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email")
    private String sEmail;

    @Column(name = "name")
    private String sName;

    @Column(name = "password")
    private String sPassword;

    public Student() {
    }

    public Student(String sEmail, String sName, String sPassword) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPassword = sPassword;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<StudentCourse> studentCourses;
}
