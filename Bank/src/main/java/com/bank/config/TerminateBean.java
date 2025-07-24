package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PreDestroy;

@Configuration
public class TerminateBean {

    @PreDestroy
    public void onDestroy() {
        System.out.println("Spring Container is destroyed!");
    }
    
    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}
