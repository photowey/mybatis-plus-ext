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
