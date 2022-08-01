package pl.piomin.services.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.config.additional-location=classpath:/sample-appconfig.yml"
})
public class LoadingOrderTest2 {

    @Value("${property1}")
    String property1;
    @Value("${property2}")
    String property2;
    @Value("${property3}")
    String property3;

    @Test
    public void test() {
        Assertions.assertEquals("sample", property3);
        Assertions.assertEquals("sample", property2);
        Assertions.assertEquals("app", property1);
    }
}
