package webservice;

import model.Student;
import Thread.threadOne;

import javax.jws.WebService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


@WebService(endpointInterface = "webservice.StudentWs")
public class StudentWsImpl implements StudentWs{
    private static final String path = "app.conf";
    @Override
    public String addStudentFake(String username, String password,Student addStudent ) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(path)));
        String user = properties.getProperty("username");
        String pass = properties.getProperty("password");
        if(!username.equalsIgnoreCase(user) && !password.equalsIgnoreCase(pass)){
            return "Username và Password không hợp lệ";
        }
        else {
//            Student student = new Student(name,className,address,age,code,mark);
            Student student = addStudent;
            threadOne threadOne = new threadOne(student);
            threadOne.start();
            return "Thành công ";
        }
    }
}
//String name, int age, int code, String className, String address, double mark