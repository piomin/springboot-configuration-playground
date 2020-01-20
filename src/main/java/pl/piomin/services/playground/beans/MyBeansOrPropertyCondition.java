package pl.piomin.services.playground.beans;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class MyBeansOrPropertyCondition extends AnyNestedCondition {

    public MyBeansOrPropertyCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnBean(MyBean1.class)
    static class MyBean1ExistsCondition {

    }

    @ConditionalOnBean(MyBean2.class)
    static class MyBean2ExistsCondition {

    }

    @ConditionalOnProperty("multipleBeans.enabled")
    static class MultipleBeansPropertyExists {

    }

}
