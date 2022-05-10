package edu.depaul.cdm.se452.group4.minuteTrak.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // bean
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  private final long MAX_AGE_SECS = 3600;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // For all routes
    registry.addMapping("/**")//
        .allowedOrigins("http://localhost:3000")//
        .allowedHeaders("*")//
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")//
        .allowCredentials(true)//
        .maxAge(MAX_AGE_SECS);
  }

}
