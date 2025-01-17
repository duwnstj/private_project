package net.daum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
            }
        };
    }
}


//.addMapping("/**")
//.allowedOrigins("http://localhost:8059", "https://c44f-112-221-198-149.ngrok-free.app")
//.allowedMethods("GET", "POST", "PUT", "DELETE")
//.allowedHeaders("*");

//.addMapping("/**")
//.allowedOrigins("*")
//.allowedMethods("*")
//.allowedHeaders("*");