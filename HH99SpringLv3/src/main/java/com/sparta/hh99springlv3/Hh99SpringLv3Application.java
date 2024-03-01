package com.sparta.hh99springlv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Hh99SpringLv3Application {

    public static void main(String[] args) {
        SpringApplication.run(Hh99SpringLv3Application.class, args);
    }

}
