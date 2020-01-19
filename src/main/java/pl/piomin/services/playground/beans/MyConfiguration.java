package pl.piomin.services.playground.beans;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;

public class MyConfiguration {

	@Bean
	@ConditionalOnClass(MyBean2.class)
	public MyBean1 myBean1() {
		return new MyBean1();
	}

	@Bean
	@ConditionalOnProperty("myBean2.enabled")
	public MyBean2 myBean2() {
		return new MyBean2();
	}

	@Bean
	@ConditionalOnJava(range = ConditionalOnJava.Range.EQUAL_OR_NEWER, value = JavaVersion.NINE)
	public MyBean3 myBean3() {
		return new MyBean3();
	}

	@Bean
	@ConditionalOnClass({MyBean1.class, MyBean2.class})
	@ConditionalOnBean({MyBean1.class, MyBean2.class})
	public MyBean4 myBean4() {
		return new MyBean4();
	}

}
