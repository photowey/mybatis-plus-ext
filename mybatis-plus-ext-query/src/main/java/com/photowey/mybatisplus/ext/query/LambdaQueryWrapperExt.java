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
package com.photowey.mybatisplus.ext.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.photowey.mybatisplus.ext.annotation.validation.Emptable;
import com.photowey.mybatisplus.ext.annotation.validation.Nullable;
import com.photowey.mybatisplus.ext.validator.ValueValidator;

import java.util.Collection;
import java.util.Objects;

/**
 * {@code LambdaQueryWrapperExt}
 * 扩展 {@code mybatis-plus} {@link QueryWrapper} 类, 增加如下:
 * 1.添加 {@code xxxIfPresent()} 方法, 用于扩展 {@code wrapper.xxx(condition,function,value)}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class LambdaQueryWrapperExt<T> extends LambdaQueryWrapper<T> {

    /**
     * ==
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> eqIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.eq(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * != || <>
     *
     * @param function 字段名称
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> neIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * >
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> gtIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.gt(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * >=
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> geIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ge(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * <
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> ltIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.lt(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * <=
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> leIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.le(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> likeIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.like(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * LEFT LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> likeLeftIfPresent(SFunction<T, V> function, @Nullable V value) {
        return likeIfPresent(function, value, SqlLike.LEFT);
    }

    /**
     * RIGHT LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> likeRightIfPresent(SFunction<T, V> function, @Nullable V value) {
        return likeIfPresent(function, value, SqlLike.RIGHT);
    }

    /**
     * LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param value    sqlLike {@link  SqlLike}
     * @param <V>      参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> likeIfPresent(SFunction<T, V> function, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.likeIfPresent(function, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (LambdaQueryWrapperExt<T>) super.likeLeft(ValueValidator.isNotNullOrEmpty(value), function, value);
            case RIGHT:
                return (LambdaQueryWrapperExt<T>) super.likeRight(ValueValidator.isNotNullOrEmpty(value), function, value);
            default:
                return this.likeIfPresent(function, value);
        }
    }

    /**
     * NOT LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> notLikeIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.notLike(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    /**
     * IN {@link  Collection}
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>} {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> inIfPresent(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.in(ValueValidator.isNotNullOrEmpty(values), function, values);
    }

    /**
     * IN
     * ARRAY
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> inIfPresent(SFunction<T, V> function, @Nullable @Emptable V... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LambdaQueryWrapperExt<T>) super.in(function, values);
        }

        return this;
    }

    /**
     * NOT IN {@link  Collection}
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> notInIfPresent(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.notIn(ValueValidator.isNotNullOrEmpty(values), function, values);
    }

    /**
     * NOT IN
     * ARRAY
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> notInIfPresent(SFunction<T, V> function, @Nullable @Emptable V... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LambdaQueryWrapperExt<T>) super.notIn(function, values);
        }

        return this;
    }

    /**
     * BETWEEN
     *
     * @param function 属性 {@code lambda} 表达式
     * @param from     起始参数
     * @param to       结束参数
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> betweenIfPresent(SFunction<T, V> function, @Nullable V from, @Nullable V to) {
        if (from != null && to != null) {
            return (LambdaQueryWrapperExt<T>) super.between(function, from, to);
        }
        if (from != null) {
            return (LambdaQueryWrapperExt<T>) super.ge(function, from);
        }
        if (to != null) {
            return (LambdaQueryWrapperExt<T>) super.le(function, to);
        }

        return this;
    }

}
