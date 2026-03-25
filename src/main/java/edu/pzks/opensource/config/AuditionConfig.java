package edu.pzks.opensource.config;


/*
  @author   george
  @project   open-source
  @class  AuditionConfig
  @version  1.0.0 
  @since 15.04.25 - 10.54
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class AuditionConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
