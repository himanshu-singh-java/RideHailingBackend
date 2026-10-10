package com.ridehailing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAspectJAutoProxy
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        System.out.println("Initializing Database and Systems...");

        ApplicationContext context = SpringApplication.run(Main.class);
    }
}
