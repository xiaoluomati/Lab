package cn.edu.nju.lims;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimsApplication.class, args);
    }

}
