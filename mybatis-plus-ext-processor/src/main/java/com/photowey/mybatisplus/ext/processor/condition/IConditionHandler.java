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
package com.photowey.mybatisplus.ext.processor.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.processor.field.FieldContext;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code IConditionHandler}
 * 说明: 在设计的时候, 为什么不在 {@link QueryWrapper} 和 {@link AbstractQuery} 设计泛型?
 * 1.因为有 {@link DefaultConditionHandler} 存在 - 添加泛型之后会给它的处理代理困惑.
 * 2.意义不大
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public interface IConditionHandler {

    /**
     * 字段属性-还可以进一步封装进 {@link FieldContext} 不过,这三个方法基本不会再添加参数了,所以不封装-问题也不大.
     */

    /**
     * 处理嵌套逻辑 {@code AND} 和 {@code OR} SQL
     *
     * @param queryWrapper {@link QueryWrapper}
     * @param query        当前查询实例
     * @param field        当前处理的字段
     */
    default void handleAnd(QueryWrapper queryWrapper, AbstractQuery query, Field field) {
        // TODO 需要解决的问题是- 如何更好的区分-不同的业务
        //  1.每个业务 独立的 {@code handler}
        //  2.根据 {@link Field} 字段进行判断
    }

    /**
     * 处理嵌套逻辑 {@code OR} SQL
     *
     * @param queryWrapper {@link QueryWrapper}
     * @param query        当前查询实例
     * @param field        当前处理的字段
     */
    default void handleOr(QueryWrapper queryWrapper, AbstractQuery query, Field field) {

    }
}
