package com.photowey.mybatisplus.ext.processor.component;

import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.processor.expression.CriteriaAnnotationProcessor;
import com.photowey.mybatisplus.ext.processor.advisor.CriteriaAnnotationProcessorAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;

/**
 * {@code ConditionProcessorBeanPostProcessor}
 * 条件注解类型: {@link BeanPostProcessor} 来用收集所有的 {@link CriteriaAnnotationProcessor}实例
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
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
