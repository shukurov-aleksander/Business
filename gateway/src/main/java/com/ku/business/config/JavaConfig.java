package com.ku.business.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@OpenAPIDefinition(info = @Info(
        title = "Business service gateway",
        description = "Access to the business service app through gateway",
        version = "v1"))
public class JavaConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
