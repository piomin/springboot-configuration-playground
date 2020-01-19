package pl.piomin.services.playground;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.piomin.services.playground.beans.MyBean1;
import pl.piomin.services.playground.beans.MyBean2;
import pl.piomin.services.playground.beans.MyBean3;
import pl.piomin.services.playground.beans.MyBean4;
import pl.piomin.services.playground.beans.MyConfiguration;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AutoConfigurationTest {

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void test1() {
		final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
		contextRunner
				.withUserConfiguration(MyConfiguration.class)
				.withClassLoader(new FilteredClassLoader(MyBean2.class))
				.run(context -> {
					MyBean1 myBean1 = context.getBean(MyBean1.class);
					Assert.assertEquals("I'm MyBean1", myBean1.me());
				});
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void test2Negative() {
		final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
		contextRunner
				.withPropertyValues("myBean2.enabled=false")
				.withUserConfiguration(MyConfiguration.class)
				.run(context -> {
					MyBean2 myBean2 = context.getBean(MyBean2.class);
					Assert.assertEquals("I'm MyBean2", myBean2.me());
				});
	}

	@Test
	public void test2Positive() {
		final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
		contextRunner
				.withPropertyValues("myBean2.enabled=true")
				.withUserConfiguration(MyConfiguration.class)
				.run(context -> {
					MyBean2 myBean2 = context.getBean(MyBean2.class);
					Assert.assertEquals("I'm MyBean2", myBean2.me());
				});
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void test3() {
		final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
		contextRunner
				.withUserConfiguration(MyConfiguration.class)
				.run(context -> {
					MyBean3 myBean3 = context.getBean(MyBean3.class);
					Assert.assertEquals("I'm MyBean3", myBean3.me());
				});
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void test4() {
		final ApplicationContextRunner contextRunner = new ApplicationContextRunner();
		contextRunner
				.withUserConfiguration(MyConfiguration.class)
				.run(context -> {
					MyBean4 myBean4 = context.getBean(MyBean4.class);
					Assert.assertEquals("I'm MyBean1", myBean4.me());
				});
	}

}
