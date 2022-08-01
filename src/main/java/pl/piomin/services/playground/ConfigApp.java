package pl.piomin.services.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@SpringBootApplication
@PropertySource(value = "classpath:/additional.yml", ignoreResourceNotFound = true)
public class ConfigApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConfigApp.class);
        app.setDefaultProperties(Map.of("property1", "default"));
        app.setAllowBeanDefinitionOverriding(true);
        app.run(args);
//        SpringApplication.run(ConfigApp.class, args);
    }

}
