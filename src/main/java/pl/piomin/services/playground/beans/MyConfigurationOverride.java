package pl.piomin.services.playground.beans;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class MyConfigurationOverride {

	@Bean
	public MyBean2 myBean2() {
		MyBean2 b = new MyBean2();
		b.setMe("I'm MyBean2 overridden");
		return b;
	}

	@Bean
	@ConditionalOnProperty("multiple.beans")
	public MyBean2 myBeanX() {
		MyBean2 b = new MyBean2();
		b.setMe("I'm myBean2 overridden");
		return b;
	}

}
