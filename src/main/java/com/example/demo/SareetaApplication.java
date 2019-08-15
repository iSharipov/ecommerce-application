package com.example.demo;

import com.example.demo.config.props.JwtProperties;
import org.apache.log4j.Logger;
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

    private static Logger logger = org.apache.log4j.Logger.getLogger(SareetaApplication.class);

    public static void main(String[] args) {
        logger.info("Starting application");
        SpringApplication.run(SareetaApplication.class, args);
    }

}
