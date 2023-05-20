package com.codemayur.demospringboot3.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

@Configuration
@ConfigurationProperties(prefix = "rto.cassandra")
@PropertySources(value = {@PropertySource("classpath:cassandra.properties")})
@Setter
@Slf4j
public class CassandraConfig {

    private String contactPoints;
    private int port;
    private String localDatacenter;
    private String keyspaceName;
    private String username;
    private String password;

    @Bean
    public CqlSessionFactoryBean cqlSessionFactoryBean() {
        log.info("Inside cqlSessionFactoryBean");
        CqlSessionFactoryBean session = new CqlSessionFactoryBean();
        session.setContactPoints(contactPoints);
        session.setPort(port);
        session.setLocalDatacenter(localDatacenter);
        session.setKeyspaceName(keyspaceName);
        session.setUsername(username);
        session.setPassword(password);
        return session;
    }

}
