package com.naehas;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.naehas.model")
@ComponentScan(basePackages = "com.naehas")
public class StudentApplication {
    public static void main(String[] args) {
        // Load .env file
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        
        // Set system properties from .env
        dotenv.entries().forEach(entry -> 
            System.setProperty(entry.getKey(), entry.getValue())
        );
        
        SpringApplication.run(StudentApplication.class, args);
    }
}
