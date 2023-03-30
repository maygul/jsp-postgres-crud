package org.ptt.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@EnableJpaRepositories(basePackages = "org.ptt.persistence.repository")
@EnableTransactionManagement
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "org.ptt")
public class SpringConfig {

    @PostConstruct
    public void init() {
        System.out.println("Spring initialized");
    }
}
