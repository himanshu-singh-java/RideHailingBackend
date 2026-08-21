package com.ridehailing;

import controller.RiderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ridehailing", "controller", "repository", "routing", "service", "util"})
public class Main {
    public static void main(String[] args) {

        System.out.println("Initializing Database and Systems...");

        ApplicationContext context = SpringApplication.run(Main.class);

        RiderController controller = context.getBean(RiderController.class);
        controller.startApp();
    }
}
