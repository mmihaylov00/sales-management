package com.sap.salesmanagement.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration("Beans")
public class ApplicationBeanConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean
    public JavaMailSender javaMail() {
        return new JavaMailSenderImpl();
    }

    @Bean
    public ConfigValues configValues() {
        return new ConfigValues();
    }

    @Bean
    public GsonJsonParser jsonParser() {
        return new GsonJsonParser();
    }
}
