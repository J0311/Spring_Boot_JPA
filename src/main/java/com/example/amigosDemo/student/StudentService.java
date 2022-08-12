package com.example.amigosDemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    /**
    usage of Optionals in this method.If email is taken, throw IllegalStateException
    Else, we save/add our student to the database by proxy of our repository object
     */

    public void addNewStudent(Student student) {
       Optional<Student> studentOptional = studentRepository
               .findStudentByEmail(student.getEmail());
       if (studentOptional.isPresent()){
           throw new IllegalStateException("email taken");
       }
        studentRepository.save(student);

    }

    /**
    repository method to delete student is parameterized id exists
    set value of Id as a boolean named exists. If false, throws exception
    Else, we delete student if id DOES exist (boolean returns true)
    Theoretically if you're running Postman OR Insomnia, you can run this
    as delete HTTP method to test.
     */

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId +
                    " does not exists");
        }
        studentRepository.deleteById(studentId);

    }
    /**
    Checking whether student exists with id or we throw exception.
    If name and email is NOT null and the name and email is not equal to
    CURRENT, we can set name and email. Else if it's taken, we throw exception.
    No queries are used here due to the @Transactional annotation, which
    places our method in a "managed state".
     */

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
        if(name != null && name.length() > 0
        && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0
        && !Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }

    }
}
