package pl.piomin.services.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import pl.piomin.services.playground.beans.*;

@SpringBootTest
public class AutoConfigurationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoConfigurationTest.class);

    @Test
    public void testMyBean1() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            contextRunner
                    .withUserConfiguration(MyConfiguration.class)
                    .withClassLoader(new FilteredClassLoader(MyBean2.class))
                    .run(context -> {
                        MyBean1 myBean1 = context.getBean(MyBean1.class);
                        Assertions.assertEquals("I'm MyBean1", myBean1.me());
                    });
        });
    }

    @Test
    public void testMyBean2Negative() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            contextRunner
                    .withPropertyValues("myBean2.enabled=false")
                    .withUserConfiguration(MyConfiguration.class)
                    .run(context -> {
                        MyBean2 myBean2 = context.getBean(MyBean2.class);
                        Assertions.assertEquals("I'm MyBean2", myBean2.me());
                    });
        });
    }

    @Test
    public void testMyBean2Positive() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner
                .withPropertyValues("myBean2.enabled=true")
                .withUserConfiguration(MyConfiguration.class)
                .run(context -> {
                    MyBean2 myBean2 = context.getBean(MyBean2.class);
                    Assertions.assertEquals("I'm MyBean2", myBean2.me());
                });
    }

    @Test
    public void testMyBean3() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            contextRunner
                    .withUserConfiguration(MyConfiguration.class)
                    .run(context -> {
                        MyBean3 myBean3 = context.getBean(MyBean3.class);
                        Assertions.assertEquals("I'm MyBean3", myBean3.me());
                    });
        });
    }

    @Test
    public void testMyBean4Negative() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            contextRunner
                    .withUserConfiguration(MyConfiguration.class)
                    .withPropertyValues("multipleBeans.enabled")
                    .run(context -> {
                        MyBean4 myBean4 = context.getBean(MyBean4.class);
                        Assertions.assertEquals("I'm MyBean4", myBean4.me());
                    });
        });
    }

    @Test
    public void testMyBean4Positive() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner
                .withUserConfiguration(MyConfiguration.class)
                .withPropertyValues("multipleBeans.enabled", "myBean2.enabled")
                .run(context -> {
                    MyBean4 myBean4 = context.getBean(MyBean4.class);
                    Assertions.assertEquals("I'm MyBean4", myBean4.me());
                });
    }

    @Test
    public void testOrder() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner
                .withAllowBeanDefinitionOverriding(true)
                .withUserConfiguration(MyConfiguration.class, MyConfigurationOverride.class)
                .withPropertyValues("myBean2.enabled")
                .run(context -> {
                    MyBean2 myBean2 = context.getBean(MyBean2.class);
                    Assertions.assertEquals("I'm MyBean2 overridden", myBean2.me());
                });
    }

    @Test
    public void testOrderMultiple() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
            contextRunner
                    .withAllowBeanDefinitionOverriding(true)
                    .withUserConfiguration(MyConfiguration.class, MyConfigurationOverride.class)
                    .withPropertyValues("myBean2.enabled", "multiple.beans")
                    .run(context -> {
                        MyBean2 myBean2 = context.getBean(MyBean2.class);
                        Assertions.assertEquals("I'm MyBean2 overridden", myBean2.me());
                    });
        });
    }

    @Test
    public void testMyBean5() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner
                .withUserConfiguration(MyConfiguration.class)
                .withPropertyValues("myBean5.disabled=false")
                .run(context -> {
                    MyBean5 myBean5 = context.getBean(MyBean5.class);
                    Assertions.assertEquals("I'm MyBean5", myBean5.me());
                });
    }

    @Test
    public void testMyBean6() {
        final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(MyConfiguration.class)
                .run(context -> {
                    MyBean6 myBean6 = context.getBean(MyBean6.class);
                    Assertions.assertEquals("I'm MyBean6", myBean6.me());
                });
    }
}
