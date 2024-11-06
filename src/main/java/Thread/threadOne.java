package Thread;

import model.Student;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

public class threadOne extends Thread{
    private final Queue<Student> studentQueue = new LinkedList<>();
    private static Student student = new Student();
    private static final Logger LOGGER =  Logger.getLogger(threadOne.class);
    private static final String path = "db.conf";
    public threadOne(Student student1) {
        student = student1;
    }

    @Override
    public void run() {
        studentQueue.add(student);
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(path)));
            Connection connection =  DriverManager.getConnection(properties.getProperty("connection"),
                       properties.getProperty("username"),
                        properties.getProperty("password"));
            //            Statement statement = connection.createStatement();
            String sql = "INSERT INTO student(name,code,age,className,address,mark) VALUES(?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getCode());
            preparedStatement.setInt(3,student.getAge());
            preparedStatement.setString(4,student.getClassName());
            preparedStatement.setString(5,student.getAddress());
            preparedStatement.setDouble(6,student.getMark());

            int check = preparedStatement.executeUpdate();
            LOGGER.info("Thêm mới học viên vào database :"+student.getName()+"-"+student.getCode()
                        +"-"+student.getAge()+"-"+student.getClassName()+"-"+student.getAddress()+"-"+student.getMark());
            if(check > 0){
                LOGGER.info("Thành công");
            }
            else {
                LOGGER.info("Thất Bại");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
