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
package com.photowey.mybatisplus.ext.annotation;

import java.lang.annotation.*;

/**
 * {@code ConditionProcessor}
 * 标记-修饰对象是一个条件处理器
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionProcessor {

    // 表征这是一个条件处理器

    /**
     * 目标注解
     *
     * @return 目标注解 {@link Class<? extends Annotation>}
     */
    Class<? extends Annotation> targetAnnotation();
}
