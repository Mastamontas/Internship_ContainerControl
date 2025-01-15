package com.DEVLOP.Web.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/**
 * Configuration class for setting up the {@link RestTemplate} bean.
 * This class configures and provides a {@link RestTemplate} instance to be used for making HTTP requests
 * throughout the application.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates and returns a {@link RestTemplate} bean.
     * The {@link RestTemplate} is a central component for making HTTP requests in a Spring application.
     *
     * @return A configured instance of {@link RestTemplate}.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}