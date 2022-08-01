package pl.piomin.services.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:/additional-test.yml")
public class LoadingOrderTest4 {

    @Value("${property1}")
    String property1;
    @Value("${property2}")
    String property2;
    @Value("${property3}")
    String property3;
    @Value("${property4}")
    String property4;

    @Test
    public void test() {
        Assertions.assertEquals("additional", property4);
        Assertions.assertEquals("additional-test", property3);
        Assertions.assertEquals("additional-test", property2);
        Assertions.assertEquals("app", property1);
    }
}
