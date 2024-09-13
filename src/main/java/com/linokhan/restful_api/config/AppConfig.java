package com.linokhan.restful_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration class that defines beans and other
 * global application settings.
 */
@Configuration
public class AppConfig {

    /**
     * Provides a {@link ModelMapper} bean that can be used to map between
     * different object models, typically DTOs and entities.
     *
     * @return a configured {@link ModelMapper} instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
