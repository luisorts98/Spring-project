package es.javaschool.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class TrainApplication {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(TrainApplication.class, args);
    }

}
