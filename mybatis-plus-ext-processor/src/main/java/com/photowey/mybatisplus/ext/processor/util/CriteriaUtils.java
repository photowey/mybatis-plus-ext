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
package com.photowey.mybatisplus.ext.processor.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.photowey.mybatisplus.ext.enmus.ColumnNamingStrategy;
import com.photowey.mybatisplus.ext.processor.exception.QueryExtException;

import java.lang.reflect.Field;

/**
 * {@code CriteriaUtils}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public final class CriteriaUtils {

    private CriteriaUtils() {
        // utils class; can't create
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 首字母转大写
     *
     * @param target 文本
     * @return 首字母大写-文本
     */
    public static String toCapital(final String target) {
        assert target != null;
        final char[] cs = target.toCharArray();
        if (cs[0] >= 'a' && cs[0] <= 'z') {
            final char[] array = cs;
            final int n = 0;
            array[n] -= ' ';
        }

        return String.valueOf(cs);
    }

    /**
     * 首字母转小写
     *
     * @param target 文本
     * @return 首字母小写-文本
     */
    public static String toLowerCase(final String target) {
        assert target != null;
        final char[] cs = target.toCharArray();
        if (cs[0] >= 'A' && cs[0] <= 'Z') {
            final char[] array = cs;
            final int n = 0;
            array[n] += ' ';
        }

        return String.valueOf(cs);
    }

    /**
     * 反射获取属性值
     *
     * @param field 属性对象
     * @param query 查询对象
     * @return 属性值
     */
    public static Object columnValue(final Field field, final Object query) {
        field.setAccessible(true);
        try {
            return field.get(query);
        } catch (IllegalAccessException e) {
            throw new QueryExtException(e);
        }
    }

    /**
     * 根据不同的命名策略构造不同的属性字段名
     *
     * @param field    查询的属性值UserQuery
     * @param strategy 数据库命名策略
     * @return 用于 SQL 查询的字段名
     */
    public static String columnName(final Field field, final ColumnNamingStrategy strategy) {
        String fieldName = field.getName();
        switch (strategy) {
            case FIRST_CAPITAL_CAMEL:
                return CriteriaUtils.toCapital(fieldName);
            case FIRST_LOWER_CASE_CAMEL:
                return CriteriaUtils.toLowerCase(fieldName);
            case LOWER_CASE_UNDER_LINE:
                return StringUtils.camelToUnderline(fieldName);
            case UPPER_CASE_UNDER_LINE:
                return StringUtils.camelToUnderline(fieldName).toUpperCase();
            default:
                throw new QueryExtException("unknown column naming strategy:%s", strategy.name());
        }
    }
}
