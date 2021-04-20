package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// dependency injection -
//@Component or service
@Service
public class StudentService {
    private final StudentRepository studentRepository; // the data layer

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {

        return studentRepository.findAll(); // Thanks to Spring JPA, makes it simple!

//        // Return json of this list.
//        return List.of(
//                new Student(
//                        1L,
//                        "Mariam",
//                        21,
//                        LocalDate.of(2000, Month.JANUARY, 23),
//                        "mariam@gmail.com"
//                )
//        );
    }


    public void addNewStudent(Student student) {
        System.out.println(student);

        // Error handling, if we find in db a duplicate email of the student..
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }


        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");

        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
//    Entity goes into "Managed State"
    // @Transactional allows us to do PUT requests seamlessly without writing manual code in StudentRepository
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        // if not updating the the same name
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            // Error handling, if we find in db a duplicate email
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            student.setEmail(email);
        }
    }
}


