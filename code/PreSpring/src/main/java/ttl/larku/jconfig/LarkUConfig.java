package ttl.larku.jconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

@Configuration
@ComponentScan({"ttl.larku"})
//@ComponentScan
public class LarkUConfig {

    /*
    <bean id="inMemoryStudentDAO" class="ttl.larku.dao.inmemory.InMemoryStudentDAO"/>
    */
    @Bean
    public BaseDAO<Student> studentDAO() {
        BaseDAO<Student> dao = new InMemoryStudentDAO();
        return dao;
    }

    /*1
    <bean id="studentService" class="ttl.larku.service.StudentService" lazy-init="true">
        <property name="studentDAO" ref="inMemoryStudentDAO"/>
    </bean>
     */

    @Bean
    public StudentService studentService() {
        StudentService ss = new StudentService();

        BaseDAO<Student> dao = studentDAO();

//        BaseDAO<Student> dao = new InMemoryStudentDAO();

        ss.setStudentDAO(dao);

        return ss;
    }
}