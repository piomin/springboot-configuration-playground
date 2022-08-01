package pl.piomin.services.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.config.location=classpath:/global.yml,classpath:/override.yml,classpath:/app.yml"
})
public class LoadingOrderTest {

    @Value("${property1}")
    String property1;
    @Value("${property2}")
    String property2;
    @Value("${property3}")
    String property3;

    @Test
    public void test() {
        Assertions.assertEquals("app", property3);
        Assertions.assertEquals("override", property2);
        Assertions.assertEquals("global", property1);
    }
}
