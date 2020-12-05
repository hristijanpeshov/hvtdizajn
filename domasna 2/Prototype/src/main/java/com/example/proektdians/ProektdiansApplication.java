package com.example.proektdians;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ProektdiansApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProektdiansApplication.class, args);
    }

}
