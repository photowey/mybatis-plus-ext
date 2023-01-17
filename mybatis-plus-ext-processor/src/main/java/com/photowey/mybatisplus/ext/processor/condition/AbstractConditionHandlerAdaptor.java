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
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.photowey.mybatisplus.ext.enmus.ColumnNamingStrategy;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import com.photowey.mybatisplus.ext.processor.util.CriteriaUtils;

import java.lang.reflect.Field;

/**
 * {@code AbstractConditionHandlerAdaptor}
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
public abstract class AbstractConditionHandlerAdaptor implements IConditionHandler {

    @Override
    public void handleAnd(QueryWrapper queryWrapper, AbstractQuery query, Field field) {

    }

    @Override
    public void handleOr(QueryWrapper queryWrapper, AbstractQuery query, Field field) {

    }

    /**
     * 判断对象或者字符串是否为空
     *
     * @param value 指定值
     * @return 布尔值
     */
    public boolean isNullOrEmpty(final Object value) {
        return value == null || (value instanceof String && StringUtils.isBlank(String.valueOf(value)));
    }

    /**
     * 反射获取属性值
     *
     * @param field 属性对象
     * @param query 查询对象
     * @return 属性值
     */
    public Object columnValue(final Field field, final Object query) {
        return CriteriaUtils.columnValue(field, query);
    }

    /**
     * 根据不同的命名策略构造不同的属性字段名
     *
     * @param field    查询的属性值UserQuery
     * @param strategy 数据库命名策略
     * @return 用于 SQL 查询的字段名
     */
    public String columnName(final Field field, final ColumnNamingStrategy strategy) {
        return CriteriaUtils.columnName(field, strategy);
    }
}
