package com.photowey.mybatisplus.ext.processor.component;

import com.photowey.mybatisplus.ext.processor.advisor.TimeProcessorContainer;
import com.photowey.mybatisplus.ext.annotation.component.TimeProcessor;
import com.photowey.mybatisplus.ext.processor.time.ITimeProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * {@code TimeProcessorBeanPostProcessor}
 * 时间类型: {@link BeanPostProcessor} 来用收集所有的 {@link ITimeProcessor}实例
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
public class TimeProcessorBeanPostProcessor implements BeanPostProcessor, DisposableBean {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(TimeProcessor.class)) {
            TimeProcessor timeProcessor = bean.getClass().getAnnotation(TimeProcessor.class);
            Class<?> dateType = timeProcessor.dateType();
            TimeProcessorContainer.put(dateType, (ITimeProcessor<?>) bean);
        }

        return bean;
    }

    @Override
    public void destroy() throws Exception {
        TimeProcessorContainer.destroyProcessorCache();
    }
}