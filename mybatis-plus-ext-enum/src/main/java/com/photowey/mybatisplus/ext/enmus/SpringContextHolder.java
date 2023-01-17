/*
 * Copyright © 2022 the original author or authors.
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
package com.photowey.mybatisplus.ext.enmus;

import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

/**
 * {@code SpringContextHolder}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public enum SpringContextHolder {

    /**
     * Instance spring {@link org.springframework.context.ConfigurableApplicationContext} holder.
     */
    INSTANCE;

    /**
     * {@link org.springframework.context.ConfigurableApplicationContext}.
     */
    private ConfigurableApplicationContext applicationContext;

    public void registerBean(final Class<?> type, final Object object) {
        if (Objects.nonNull(this.applicationContext)) {
            this.applicationContext.getBeanFactory().registerSingleton(this.generateBeanName(type), object);
        }
    }

    public <T> T getBean(final Class<T> type) {
        notNull(type, "the type can't be null!");
        try {
            return this.applicationContext.getBean(type);
        } catch (BeansException e) {
            try {
                return this.getByName(type);
            } catch (BeansException ex) {
                return null;
            }
        }
    }

    public <T> T getBean(final String beanName, final Class<T> type) {
        notNull(type, "the type can't be null!");
        try {
            return this.applicationContext.getBean(this.generateBeanName(beanName), type);
        } catch (BeansException e) {
            return null;
        }
    }

    private <T> T getByName(final Class<T> type) {
        return this.applicationContext.getBean(this.generateBeanName(type), type);
    }

    // ==================================================================

    public void setApplicationContext(final ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ConfigurableApplicationContext applicationContext() {
        return this.applicationContext;
    }

    // ==================================================================

    public <T> String generateBeanName(Class<T> type) {
        return this.generateBeanName(type.getSimpleName());
    }

    public String generateBeanName(String beanName) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
    }

    public static void notNull(final Object target) {
        notNull(target, "argument invalid,Please check");
    }

    public static void notNull(final Object target, String message) {
        if (target == null) {
            throw new RuntimeException(message);
        }
    }
}
