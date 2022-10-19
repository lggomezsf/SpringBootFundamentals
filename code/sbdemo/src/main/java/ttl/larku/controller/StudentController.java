package ttl.larku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.util.List;

/**
 * @author whynot
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private UriCreator uriCreator;

//    @GetMapping("/hello")
//    public String sayit() {
//        return "Hello from here";
//    }

    @GetMapping
    public List<Student> getStudents() {
        List<Student> students =  studentService.getAllStudents();
        return students;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if(student == null) {
            return ResponseEntity.status(400).body("No Student with id: " + id);
        }

        return ResponseEntity.ok(student);
    }


    @PostMapping
    public ResponseEntity<?> insertStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);

//        URI newResource = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(newStudent.getId())
//                .toUri();

        URI newResource = uriCreator.getURI(newStudent.getId());


        return ResponseEntity.created(newResource).build();
        //return ResponseEntity.created(newResource).body(newStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        boolean result = studentService.deleteStudent(id);
        if(result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(400).body("No Student with id: " + id);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        boolean result = studentService.updateStudent(student);
        if(result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(400).body("No Student with id: " + student.getId());
    }
}