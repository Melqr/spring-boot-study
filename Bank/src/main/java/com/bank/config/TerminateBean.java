package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class TerminateBean {

    @PreDestroy
    public void onDestroy() {
    	log.info("Spring Container is destroyed!");
    }
    
    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}
