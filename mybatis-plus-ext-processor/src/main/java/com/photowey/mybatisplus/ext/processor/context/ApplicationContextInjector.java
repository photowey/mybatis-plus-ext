package com.photowey.mybatisplus.ext.processor.context;

import com.photowey.mybatisplus.ext.enmus.SpringContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;

/**
 * {@code ApplicationContextInjector}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class ApplicationContextInjector implements ApplicationContextAware {

    @Override
    public void setApplicationContext(@NonNull final ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.INSTANCE.setApplicationContext((ConfigurableApplicationContext) applicationContext);
    }
}
