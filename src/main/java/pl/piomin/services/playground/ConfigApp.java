package pl.piomin.services.playground;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.Map;

@SpringBootApplication
@PropertySource(value = "classpath:/additional.yml", ignoreResourceNotFound = true)
public class ConfigApp {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigApp.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConfigApp.class);
        app.setDefaultProperties(Map.of("property.default", "default"));
        app.setAllowBeanDefinitionOverriding(true);
        app.run(args);
    }

    @Value("${property.default}")
    String propertyDefault;
    @Value("${property.activation:none}")
    String propertyActivation;

    @PostConstruct
    public void printInfo() {
        LOG.info("========= Property (default): {}", propertyDefault);
        LOG.info("========= Property (activation): {}", propertyActivation);
    }
}
