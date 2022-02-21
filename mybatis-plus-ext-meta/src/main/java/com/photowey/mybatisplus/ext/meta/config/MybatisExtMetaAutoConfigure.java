package com.photowey.mybatisplus.ext.meta.config;

import com.photowey.mybatisplus.ext.meta.selector.MetaPropertiesFillerSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code MybatisExtMetaAutoConfigure}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Configuration
@Import(MetaPropertiesFillerSelector.class)
public class MybatisExtMetaAutoConfigure {

    /**
     * 注释掉-采用 {@code SPI} 的方式加载
     * 因为: 由于 阿里《Java 开发手册》版本不同导致对 create_time 和 update_time 的命名方式有些不同,
     * 所以需要适配, 进而采用 {@code SPI} 的方式灵活扩展
     */
    /*@Bean
    @ConditionalOnMissingBean(MetaPropertiesFiller.class)
    public MetaPropertiesFiller metaPropertiesFiller() {
        return new StandardMetaPropertiesFiller();
    }*/
}
