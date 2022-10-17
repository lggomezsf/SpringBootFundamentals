package ttl.larku.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.domain.Student;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.util.List;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class SpringDemoApp {

    public static void main(String[] args) {
        SpringDemoApp sda = new SpringDemoApp();
        sda.goCourse();
    }

    public void go() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);


        StudentService ss = context.getBean("mybean", StudentService.class);

        StudentService ss2 = context.getBean("mybean", StudentService.class);

        List<Student> students = ss.getAllStudents();
        out.println("Students: " + students.size());
        students.forEach(System.out::println);
    }

    public void goCourse() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);


        CourseService cs = context.getBean("courseService", CourseService.class);

        out.println(cs.getAllCourses());
    }
}
