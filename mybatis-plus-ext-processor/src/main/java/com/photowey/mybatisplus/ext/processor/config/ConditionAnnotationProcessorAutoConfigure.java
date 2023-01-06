/*
 * Copyright © 2022 the original author or authors (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photowey.mybatisplus.ext.processor.config;

import com.photowey.mybatisplus.ext.processor.component.ConditionProcessorBeanPostProcessor;
import com.photowey.mybatisplus.ext.processor.component.TimeProcessorBeanPostProcessor;
import com.photowey.mybatisplus.ext.processor.condition.DefaultConditionHandler;
import com.photowey.mybatisplus.ext.processor.condition.IConditionHandler;
import com.photowey.mybatisplus.ext.processor.context.ApplicationContextInjector;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code ConditionAnnotationProcessorAutoConfigure}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@AutoConfiguration
@Import(value = {
        ConditionProcessorBeanPostProcessor.class,
        TimeProcessorBeanPostProcessor.class,
        ConditionAnnotationProcessorAutoConfigure.ComponentConfigure.class,
})
public class ConditionAnnotationProcessorAutoConfigure {

    @Configuration
    @ComponentScan(value = {
            "com.photowey.mybatisplus.ext.processor.expression",
            "com.photowey.mybatisplus.ext.processor.time"
    })
    static class ComponentConfigure {

    }

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
}
