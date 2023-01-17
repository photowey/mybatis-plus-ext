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
package com.photowey.mybatisplus.ext.annotation;

import com.photowey.mybatisplus.ext.enmus.ColumnNamingStrategy;
import com.photowey.mybatisplus.ext.enmus.CompareEnum;

import java.lang.annotation.*;
import java.time.LocalDateTime;

/**
 * {@code ObjectTime}
 *
 * @author photowey
 * @date 2022/11/11
 * @since 1.0.0
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectTime {

    /**
     * 自定义的属性值
     *
     * @return
     */
    String alias() default "";

    /**
     * 比较运行符
     *
     * @return CompareEnum
     * @see {@link CompareEnum}
     */
    CompareEnum compare() default CompareEnum.EQ;

    /**
     * 转换的时间对象
     * 默认采用 {@link LocalDateTime}
     *
     * @return
     * @see {@link LocalDateTime}
     */
    Class<?> clazz() default LocalDateTime.class;

    /**
     * 默认下划线
     *
     * @return ColumnNamingStrategy
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
