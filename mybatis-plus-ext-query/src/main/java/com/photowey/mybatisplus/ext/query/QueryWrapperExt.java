package com.photowey.mybatisplus.ext.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.photowey.mybatisplus.ext.validator.ValueValidator;
import com.photowey.mybatisplus.ext.annotation.validation.Emptable;
import com.photowey.mybatisplus.ext.annotation.validation.Nullable;

import java.util.Collection;
import java.util.Objects;

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

    /**
     * LEFT LIKE
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型 {@code V}
     * @return {@link QueryWrapperExt<T>}
     */
    public <V> QueryWrapperExt<T> likeLeftIfPresent(String column, @Nullable V value) {
        return likeIfPresent(column, value, SqlLike.LEFT);
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
        return likeIfPresent(column, value, SqlLike.RIGHT);
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

}
