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
package com.photowey.mybatisplus.ext.processor.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * {@code CriteriaAnnotationProcessor}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public interface CriteriaAnnotationProcessor<A extends Annotation, QUERY extends AbstractQuery, WRAPPER extends QueryWrapper<ENTITY>, ENTITY> {
    /**
     * 处理注解
     *
     * @param queryWrapper       查询包装器
     * @param field              属性
     * @param query              query 对象
     * @param criteriaAnnotation 条件注解
     * @return 布尔值
     */
    boolean process(WRAPPER queryWrapper, final Field field, final QUERY query, final A criteriaAnnotation);
}
