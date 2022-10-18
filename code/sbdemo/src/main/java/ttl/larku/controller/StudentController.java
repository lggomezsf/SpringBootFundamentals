package ttl.larku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

/**
 * @author whynot
 */

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hello")
    public String sayit() {
        return "Hello from here";
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students =  studentService.getAllStudents();
        return students;
    }
}