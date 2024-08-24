package com.cm.cm2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan(basePackages = "com.cm.cm2.entities")
public class Cm2Application {

    public static void main(String[] args) {
        SpringApplication.run(Cm2Application.class, args);
    }

}
