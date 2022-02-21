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
