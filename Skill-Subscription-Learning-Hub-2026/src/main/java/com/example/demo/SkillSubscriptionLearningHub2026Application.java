package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.skills.hub")
@EnableJpaRepositories(basePackages = "com.skills.hub.repository")
@EntityScan(basePackages = "com.skills.hub.model")
public class SkillSubscriptionLearningHub2026Application {

    public static void main(String[] args) {
        SpringApplication.run(SkillSubscriptionLearningHub2026Application.class, args);
    }
}
