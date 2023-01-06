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
package com.photowey.mybatisplus.ext.processor.component;

import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.processor.advisor.CriteriaAnnotationProcessorAdvisor;
import com.photowey.mybatisplus.ext.processor.expression.CriteriaAnnotationProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Role;

import java.lang.annotation.Annotation;

/**
 * {@code ConditionProcessorBeanPostProcessor}
 * 条件注解类型: {@link BeanPostProcessor} 来用收集所有的 {@link CriteriaAnnotationProcessor}实例
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ConditionProcessorBeanPostProcessor implements BeanPostProcessor, DisposableBean {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(ExpressionProcessor.class)) {
            ExpressionProcessor expressionProcessor = bean.getClass().getAnnotation(ExpressionProcessor.class);
            Class<? extends Annotation> targetAnnotation = expressionProcessor.targetAnnotation();
            CriteriaAnnotationProcessorAdvisor.put(targetAnnotation, (CriteriaAnnotationProcessor) bean);
        }

        return bean;
    }

    @Override
    public void destroy() throws Exception {
        CriteriaAnnotationProcessorAdvisor.destroyProcessorCache();
    }
}
