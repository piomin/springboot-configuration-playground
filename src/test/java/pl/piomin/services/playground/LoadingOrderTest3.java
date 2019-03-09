package pl.piomin.services.playground;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(properties = {
    "spring.profiles.active=override"
})
@RunWith(SpringRunner.class)
public class LoadingOrderTest3 {

    @Value("${property1}")
    String property1;
    @Value("${property2}")
    String property2;
    @Value("${property3}")
    String property3;

    @Test
    public void test() {
        Assert.assertEquals("override", property3);
        Assert.assertEquals("override", property2);
        Assert.assertEquals("app", property1);
    }
}
