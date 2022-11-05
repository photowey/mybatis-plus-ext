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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.photowey.mybatisplus.ext.annotation.validation.Emptable;
import com.photowey.mybatisplus.ext.annotation.validation.Nullable;
import com.photowey.mybatisplus.ext.validator.ValueValidator;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code QueryWrapperExt}
 * 扩展 {@code mybatis-plus} {@link QueryWrapper} 类, 增加如下:
 * 1.添加 {@code xxxIfPresent()} 方法, 用于扩展 {@code wrapper.xxx(condition,column,value)}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class QueryWrapperExt<T> extends QueryWrapper<T> {

    /**
     * ==
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> eqIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.eq(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> eq(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.eq(column, value);
    }


    /**
     * != || <>
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> neIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ne(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> ne(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ne(column, value);
    }

    /**
     * >
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> gtIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.gt(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> gt(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.gt(column, value);
    }

    /**
     * >=
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> geIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ge(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> ge(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ge(column, value);
    }

    /**
     * <
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> ltIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.lt(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> lt(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.lt(column, value);
    }

    /**
     * <=
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> leIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.le(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> le(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.le(column, value);
    }

    /**
     * LIKE
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> likeIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.like(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> like(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.like(column, value);
    }

    /**
     * LEFT LIKE
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> likeLeftIfPresent(String column, @Nullable V value) {
        return this.likeIfPresent(column, value, SqlLike.LEFT);
    }

    public <V> QueryWrapperExt<T> likeLeft(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.likeLeft(column, value);
    }

    /**
     * RIGHT LIKE
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> likeRightIfPresent(String column, @Nullable V value) {
        return this.likeIfPresent(column, value, SqlLike.RIGHT);
    }

    public <V> QueryWrapperExt<T> likeRight(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.likeRight(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    /**
     * LIKE
     *
     * @param column 字段名称
     * @param value  参数值
     * @param value  sqlLike {@link  SqlLike}
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> likeIfPresent(String column, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.likeIfPresent(column, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (QueryWrapperExt<T>) super.likeLeft(ValueValidator.isNotNullOrEmpty(value), column, value);
            case RIGHT:
                return (QueryWrapperExt<T>) super.likeRight(ValueValidator.isNotNullOrEmpty(value), column, value);
            default:
                return this.likeIfPresent(column, value);
        }
    }

    public <V> QueryWrapperExt<T> like(String column, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.like(column, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (QueryWrapperExt<T>) super.likeLeft(column, value);
            case RIGHT:
                return (QueryWrapperExt<T>) super.likeRight(column, value);
            default:
                return this.like(column, value);
        }
    }

    /**
     * NOT LIKE
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> notLikeIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.notLike(ValueValidator.isNotNullOrEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> notLike(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.notLike(column, value);
    }

    /**
     * IN {@link  Collection}
     *
     * @param column 字段名称
     * @param values 参数列表
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>} {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> inIfPresent(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.in(ValueValidator.isNotNullOrEmpty(values), column, values);
    }

    public <V> QueryWrapperExt<T> in(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.in(column, values);
    }

    /**
     * IN
     * ARRAY
     *
     * @param column 字段名称
     * @param values 参数列表
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> inIfPresent(String column, @Nullable @Emptable V... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (QueryWrapperExt<T>) super.in(column, values);
        }

        return this;
    }

    public <V> QueryWrapperExt<T> in(String column, @Nullable @Emptable V... values) {
        return (QueryWrapperExt<T>) super.in(column, values);
    }

    /**
     * NOT IN {@link  Collection}
     *
     * @param column 字段名称
     * @param values 参数列表
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> notInIfPresent(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.notIn(ValueValidator.isNotNullOrEmpty(values), column, values);
    }

    public <V> QueryWrapperExt<T> notIn(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.notIn(column, values);
    }


    /**
     * NOT IN
     * ARRAY
     *
     * @param column 字段名称
     * @param values 参数列表
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> notInIfPresent(String column, @Nullable @Emptable V... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (QueryWrapperExt<T>) super.notIn(column, values);
        }

        return this;
    }

    public <V> QueryWrapperExt<T> notIn(String column, @Nullable @Emptable V... values) {
        return (QueryWrapperExt<T>) super.notIn(column, values);
    }

    /**
     * BETWEEN
     *
     * @param column 字段名称
     * @param from   起始参数
     * @param to     结束参数
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> betweenIfPresent(String column, @Nullable V from, @Nullable V to) {
        if (from != null && to != null) {
            return (QueryWrapperExt<T>) super.between(column, from, to);
        }
        if (from != null) {
            return (QueryWrapperExt<T>) super.ge(column, from);
        }
        if (to != null) {
            return (QueryWrapperExt<T>) super.le(column, to);
        }

        return this;
    }

    public <V> QueryWrapperExt<T> between(String column, @Nullable V from, @Nullable V to) {
        return (QueryWrapperExt<T>) super.between(column, from, to);
    }

    /**
     * 用于获取 {@link QueryWrapper} 自身对象,从而实现一些模板代码,进而提升 {@link QueryWrapper} 的灵活性
     * <p>
     * For example:
     * <pre>
     * new QueryWrapperExt<Hello>().thiz((qw)->{}).eq("id", 1L);
     * </pre>
     *
     * @param fx {@link Consumer<QueryWrapperExt<T>>}
     * @return {@link QueryWrapperExt<T>}
     */
    public QueryWrapperExt<T> thiz(Consumer<QueryWrapperExt<T>> fx) {
        return this.thiz(true, fx);
    }

    public QueryWrapperExt<T> thiz(boolean condition, Consumer<QueryWrapperExt<T>> fx) {
        if (condition) {
            fx.accept(this);
        }

        return this;
    }
}
