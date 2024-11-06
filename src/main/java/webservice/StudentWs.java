package webservice;

import model.Student;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebService
public interface StudentWs {

    @WebMethod
    String addStudentFake(String username, String password, Student addStudent) throws IOException;
}
