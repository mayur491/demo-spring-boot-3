package com.codemayur.demospringboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(scanBasePackages = "com.codemayur")
@EnableAutoConfiguration
@EnableKafka
@EnableCassandraRepositories(basePackages = "com.codemayur.core.repo")
public class DemoSpringBoot3Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBoot3Application.class, args);
    }

}
