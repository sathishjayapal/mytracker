package me.sathish.eventservice.config;

import me.sathish.eventservice.ApplicationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebMvcConfig implements WebMvcConfigurer {
    private final ApplicationProperties props;

    WebMvcConfig(ApplicationProperties props) {
        this.props = props;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(props.cors().pathPattern())
                .allowedOriginPatterns(props.cors().allowedOrigins())
                .allowedMethods(props.cors().allowedMethods())
                .allowedHeaders(props.cors().allowedHeaders());
    }
}
