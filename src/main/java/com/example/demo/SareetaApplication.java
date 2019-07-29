package com.example.demo;

import com.example.demo.config.props.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.demo.model.persistence.repositories")
@EntityScan("com.example.demo.model.persistence")
@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class SareetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SareetaApplication.class, args);
    }

}
