package com.ridehailing;

import config.AppConfig;
import controller.RiderController;
import model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        System.out.println("Initializing Database and Systems...");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        RiderController controller = context.getBean(RiderController.class);

        controller.startApp();
    }
}
