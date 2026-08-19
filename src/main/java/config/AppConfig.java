package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.ridehailing", "controller", "model", "repository", "routing", "service", "util"})
public class AppConfig {

}
