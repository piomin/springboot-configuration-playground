package pl.piomin.services.playground;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
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
        Assert.assertEquals("additional", property4);
        Assert.assertEquals("additional-test", property3);
        Assert.assertEquals("additional-test", property2);
        Assert.assertEquals("app", property1);
    }
}
