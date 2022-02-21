package com.photowey.mybatisplus.ext.processor.config;

import com.photowey.mybatisplus.ext.processor.component.ConditionProcessorBeanPostProcessor;
import com.photowey.mybatisplus.ext.processor.component.TimeProcessorBeanPostProcessor;
import com.photowey.mybatisplus.ext.processor.condition.DefaultConditionHandler;
import com.photowey.mybatisplus.ext.processor.condition.IConditionHandler;
import com.photowey.mybatisplus.ext.processor.context.ApplicationContextInjector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * {@code ConditionAnnotationProcessorAutoConfigure}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Configuration
@ComponentScan(value = {
        "com.photowey.mybatisplus.ext.processor.expression",
        "com.photowey.mybatisplus.ext.processor.time"
})
public class ConditionAnnotationProcessorAutoConfigure {

    @Bean
    public ApplicationContextInjector applicationContextInjector() {
        return new ApplicationContextInjector();
    }

    /**
     * 是否可以考虑 {@code IOC} 里面 始终有一个 {@link DefaultConditionHandler}
     * - 这样 {@code @ConditionalOnMissingBean(IConditionHandler.class)} 就不是 必须的了.
     *
     * @return {@link IConditionHandler} 默认实例 {@link DefaultConditionHandler}
     */
    @Bean
    @ConditionalOnMissingBean(IConditionHandler.class)
    public IConditionHandler defaultConditionHandler() {
        return new DefaultConditionHandler();
    }

    @Bean
    public ConditionProcessorBeanPostProcessor conditionProcessorBeanPostProcessor() {
        return new ConditionProcessorBeanPostProcessor();
    }

    @Bean
    public TimeProcessorBeanPostProcessor timeProcessorBeanPostProcessor() {
        return new TimeProcessorBeanPostProcessor();
    }
}
