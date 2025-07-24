package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class BeanLogger implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(BeanLogger.class);
    private final ApplicationContext applicationContext;

    public BeanLogger(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("==== Beans from 'com.demo' package ====");

        List<String> beanNames = Arrays.asList(applicationContext.getBeanDefinitionNames());

        List<String> demoBeans = beanNames.stream()
                .filter(beanName -> {
                    try {
                        Object bean = applicationContext.getBean(beanName);
                        return bean != null && bean.getClass().getPackage() != null &&
                               bean.getClass().getPackage().getName().startsWith("com.demo");
                    } catch (Exception e) {
                        logger.warn("Skipping bean '{}' due to error: {}", beanName, e.getMessage());
                        return false;
                    }
                })
                .sorted()
                .toList();

        demoBeans.forEach(beanName -> {
            try {
                Object bean = applicationContext.getBean(beanName);
                logger.info("{} -> {}", beanName, bean.getClass().getName());
            } catch (Exception e) {
                logger.error("Error accessing bean '{}': {}", beanName, e.getMessage());
            }
        });
    }


}