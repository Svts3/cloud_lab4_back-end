package ua.lviv.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DbLab5Application {

    public static void main(String[] args) {
        SpringApplication.run(DbLab5Application.class, args);
    }

}
