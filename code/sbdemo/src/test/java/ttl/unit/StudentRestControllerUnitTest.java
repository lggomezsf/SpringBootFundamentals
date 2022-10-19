package ttl.unit;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ttl.larku.controller.StudentController;
import ttl.larku.controller.UriCreator;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class StudentRestControllerUnitTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController controller;

    @Mock
    private UriCreator uriCreator;


    List<Student> students = Arrays.asList(
            new Student("Manoj", "282 929 9292", Student.Status.FULL_TIME),
            new Student("Alice", "393 9393 030", Student.Status.HIBERNATING));

    private final int goodStudentId = 1;
    private final int badStudentId = 10000;


    @Test
    public void testAddStudentGood() throws Exception {
        Student student = new Student("Yogita");
        student.setId(1);

        URI newStudentURI = new URI("http://localhost:8080/adminrest/student/1");

        Mockito.when(studentService.createStudent(student)).thenReturn(student);
        Mockito.when(uriCreator.getURI(1)).thenReturn(newStudentURI);

        ResponseEntity<?> response = controller.insertStudent(student);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(newStudentURI, response.getHeaders().getLocation());

        @SuppressWarnings({"unchecked", "rawtypes"})
        ResponseEntity<?> result = (ResponseEntity) response.getBody();



        Mockito.verify(studentService).createStudent(student);
        Mockito.verify(uriCreator).getURI(1);

    }
}
