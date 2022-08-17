package com.example.amigosDemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    /**
    Created a StudentService object to pass as arg within
    our StudentController constructor
     */

    // Autowired instantiates/injects StudentService obj into StudentController constructor

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
    StudentController acts as our API layer and is mapped to the getStudents method,
    which is being utilized by our service layer object, StudentService
     */

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    /**
    mapping Delete method by way of student ID<Long value>
     */

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(
            @PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
    /**
    Mapped to updateStudent method found in our StudentService class.
    Will locate student predicated on Id and update name and email.
    Name & email both are NOT required
     */

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }

}

