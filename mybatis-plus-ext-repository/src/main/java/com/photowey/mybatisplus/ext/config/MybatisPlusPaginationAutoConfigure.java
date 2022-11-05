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
package com.photowey.mybatisplus.ext.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.photowey.mybatisplus.ext.property.MybatisPlusExtProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code MybatisPlusPaginationAutoConfigure}
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
@Configuration
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
@EnableConfigurationProperties(MybatisPlusExtProperties.class)
public class MybatisPlusPaginationAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    public MybatisPlusInterceptor mybatisPlusInterceptor(MybatisPlusExtProperties props) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(props.getDbType());
        paginationInnerInterceptor.setOverflow(props.isOverflow());
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean(ConfigurationCustomizer.class)
    public ConfigurationCustomizer configurationCustomizer(MybatisPlusExtProperties props) {
        return configuration -> configuration.setUseDeprecatedExecutor(props.isUseDeprecatedExecutor());
    }
}
