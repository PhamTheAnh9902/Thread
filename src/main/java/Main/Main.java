package Main;

import org.apache.log4j.BasicConfigurator;
import webservice.StudentWsImpl;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Endpoint.publish("http://localhost:9997/model/Student",new StudentWsImpl());
    }
}
