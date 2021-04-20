package com.example.demo.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

// Maps Student to db //
@Entity
@Table
// Maps Student to db //
public class Student {


    // Maps Student to db //
    @Id
    // postgres related
    @SequenceGenerator(
            name="student_sequence",
            sequenceName="student_sequence",
            allocationSize = 1 // increments by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    //////////

    private Long id;
    private String name;

    // Transient means we do not need this in Postgres Column
    @Transient
    private Integer age;


    private LocalDate dob;
    private String email;


    public Student() {

    }

//    public Student(Long id, String name, Integer age, LocalDate dob, String email) {
    public Student(Long id, String name, LocalDate dob, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
    }


//    database will generate id for us
//    public Student(String name, Integer age, LocalDate dob, String email) {
    public Student(String name,  LocalDate dob, String email) {
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}
