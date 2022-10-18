package ttl.larku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Student;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.service.StudentService;

import java.util.List;

@SpringBootApplication //(scanBasePackages = {"ttl.larku.jconfig", "ttl.larku.sbdemo"})
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
public class SbdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbdemoApplication.class, args);
	}
}

@Component
class MyRunner implements CommandLineRunner
{
	@Autowired
	private StudentService studentService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Here we go with Spring Boot");
		List<Student> students = studentService.getAllStudents();
		students.forEach(System.out::println);
	}
}

