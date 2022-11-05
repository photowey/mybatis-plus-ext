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

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.photowey.mybatisplus.ext.annotation.validation.Emptable;
import com.photowey.mybatisplus.ext.annotation.validation.Nullable;
import com.photowey.mybatisplus.ext.validator.ValueValidator;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code LambdaUpdateWrapperExt}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
public class LambdaUpdateWrapperExt<T> extends LambdaUpdateWrapper<T> {

    /**
     * ==
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> eqIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.eq(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> eq(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.eq(function, value);
    }

    /**
     * != || <>
     *
     * @param function 字段名称
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> neIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.ne(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> ne(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.ne(function, value);
    }

    /**
     * >
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> gtIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.gt(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> gt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.gt(function, value);
    }

    /**
     * >=
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> geIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.ge(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> ge(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.ge(function, value);
    }

    /**
     * <
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> ltIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.lt(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> lt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.lt(function, value);
    }

    /**
     * <=
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> leIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.le(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> le(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.le(function, value);
    }

    /**
     * LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> likeIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.like(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> like(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.like(function, value);
    }

    /**
     * LEFT LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> LambdaUpdateWrapperExt<T> likeLeftIfPresent(SFunction<T, V> function, @Nullable V value) {
        return this.likeIfPresent(function, value, SqlLike.LEFT);
    }

    public <V> LambdaUpdateWrapperExt<T> likeLeft(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.likeLeft(function, value);
    }

    /**
     * RIGHT LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> likeRightIfPresent(SFunction<T, V> function, @Nullable V value) {
        return this.likeIfPresent(function, value, SqlLike.RIGHT);
    }

    public <V> LambdaUpdateWrapperExt<T> likeRight(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.likeRight(function, value);
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
    public <V> LambdaUpdateWrapperExt<T> likeIfPresent(SFunction<T, V> function, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.likeIfPresent(function, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (LambdaUpdateWrapperExt<T>) super.likeLeft(ValueValidator.isNotNullOrEmpty(value), function, value);
            case RIGHT:
                return (LambdaUpdateWrapperExt<T>) super.likeRight(ValueValidator.isNotNullOrEmpty(value), function, value);
            default:
                return this.likeIfPresent(function, value);
        }
    }

    public <V> LambdaUpdateWrapperExt<T> like(SFunction<T, V> function, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.like(function, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (LambdaUpdateWrapperExt<T>) super.likeLeft(function, value);
            case RIGHT:
                return (LambdaUpdateWrapperExt<T>) super.likeRight(function, value);
            default:
                return this.like(function, value);
        }
    }

    /**
     * NOT LIKE
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> notLikeIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.notLike(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> notLike(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.notLike(function, value);
    }

    /**
     * IN {@link  Collection}
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>} {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> inIfPresent(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaUpdateWrapperExt<T>) super.in(ValueValidator.isNotNullOrEmpty(values), function, values);
    }

    public <V> LambdaUpdateWrapperExt<T> in(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaUpdateWrapperExt<T>) super.in(function, values);
    }

    /**
     * IN
     * ARRAY
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> inIfPresent(SFunction<T, V> function, @Nullable @Emptable V... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LambdaUpdateWrapperExt<T>) super.in(function, values);
        }

        return this;
    }

    public <V> LambdaUpdateWrapperExt<T> in(SFunction<T, V> function, @Nullable @Emptable V... values) {
        return (LambdaUpdateWrapperExt<T>) super.in(function, values);
    }

    /**
     * NOT IN {@link  Collection}
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> notInIfPresent(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaUpdateWrapperExt<T>) super.notIn(ValueValidator.isNotNullOrEmpty(values), function, values);
    }

    public <V> LambdaUpdateWrapperExt<T> notIn(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaUpdateWrapperExt<T>) super.notIn(function, values);
    }


    /**
     * NOT IN
     * ARRAY
     *
     * @param function 属性 {@code lambda} 表达式
     * @param values   参数列表
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> notInIfPresent(SFunction<T, V> function, @Nullable @Emptable V... values) {
        return (LambdaUpdateWrapperExt<T>) super.notIn(function, values);
    }

    public <V> LambdaUpdateWrapperExt<T> notIn(SFunction<T, V> function, @Nullable @Emptable V... values) {
        return (LambdaUpdateWrapperExt<T>) super.notIn(function, values);
    }

    /**
     * BETWEEN
     *
     * @param function 属性 {@code lambda} 表达式
     * @param from     起始参数
     * @param to       结束参数
     * @param <V>      参数类型 {@code V}
     * @return {@link LambdaUpdateWrapperExt <T>}
     */
    public <V> LambdaUpdateWrapperExt<T> betweenIfPresent(SFunction<T, V> function, @Nullable V from, @Nullable V to) {
        if (from != null && to != null) {
            return (LambdaUpdateWrapperExt<T>) super.between(function, from, to);
        }
        if (from != null) {
            return (LambdaUpdateWrapperExt<T>) super.ge(function, from);
        }
        if (to != null) {
            return (LambdaUpdateWrapperExt<T>) super.le(function, to);
        }

        return this;
    }

    public <V> LambdaUpdateWrapperExt<T> between(SFunction<T, ?> function, @Nullable V from, @Nullable V to) {
        return (LambdaUpdateWrapperExt<T>) super.between(function, from, to);
    }

    public <V> LambdaUpdateWrapperExt<T> setIfPresent(SFunction<T, V> column, V value) {
        return (LambdaUpdateWrapperExt<T>) super.set(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> LambdaUpdateWrapperExt<T> setIfPresent(SFunction<T, V> column, V value, String mapping) {
        return (LambdaUpdateWrapperExt<T>) super.set(ValueValidator.isNotNullOrEmpty(value), column, value, mapping);
    }

    @Override
    public LambdaUpdateWrapperExt<T> set(SFunction<T, ?> column, Object value) {
        return (LambdaUpdateWrapperExt<T>) super.set(column, value);
    }

    @Override
    public LambdaUpdateWrapperExt<T> set(boolean condition, SFunction<T, ?> column, Object value) {
        return (LambdaUpdateWrapperExt<T>) super.set(condition, column, value);
    }

    @Override
    public LambdaUpdateWrapperExt<T> set(SFunction<T, ?> column, Object value, String mapping) {
        return (LambdaUpdateWrapperExt<T>) super.set(column, value, mapping);
    }

    @Override
    public LambdaUpdateWrapperExt<T> set(boolean condition, SFunction<T, ?> column, Object value, String mapping) {
        return (LambdaUpdateWrapperExt<T>) super.set(condition, column, value, mapping);
    }

    /**
     * 用于获取 {@link LambdaUpdateWrapper} 自身对象,从而实现一些模板代码,进而提升 {@link LambdaUpdateWrapper} 的灵活性
     * <p>
     * For example:
     * <pre>
     * new LambdaUpdateWrapperExt<Hello>().thiz((qw)->{
     *    qw.set(Hello::getName(), "hello");
     * }).eq(Hello::getId(), 1L);
     * </pre>
     *
     * @param fx {@link Consumer<LambdaUpdateWrapperExt<T>>}
     * @return {@link LambdaUpdateWrapperExt<T>}
     */
    public LambdaUpdateWrapperExt<T> thiz(Consumer<LambdaUpdateWrapperExt<T>> fx) {
        return this.thiz(true, fx);
    }

    public LambdaUpdateWrapperExt<T> thiz(boolean condition, Consumer<LambdaUpdateWrapperExt<T>> fx) {
        if (condition) {
            fx.accept(this);
        }

        return this;
    }
}
