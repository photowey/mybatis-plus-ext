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
package com.photowey.mybatisplus.ext.annotation.component;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.time.LocalDateTime;

/**
 * {@code TimeProcessor}
 * 标记是一个 {@code ITimeProcessor} 处理器
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
@Inherited
@Documented
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeProcessor {

    /**
     * 目标注解
     *
     * @return 目标日期类型
     */
    Class<?> dateType() default LocalDateTime.class;
}
