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
package com.photowey.mybatisplus.ext.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.photowey.mybatisplus.ext.annotation.validation.Emptable;
import com.photowey.mybatisplus.ext.annotation.validation.Nullable;
import com.photowey.mybatisplus.ext.validator.ValueValidator;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

    public <V> LambdaQueryWrapperExt<T> eq(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.eq(function, value);
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

    public <V> LambdaQueryWrapperExt<T> ne(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(function, value);
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

    public <V> LambdaQueryWrapperExt<T> gt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.gt(function, value);
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

    public <V> LambdaQueryWrapperExt<T> ge(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ge(function, value);
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

    public <V> LambdaQueryWrapperExt<T> lt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.lt(function, value);
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

    public <V> LambdaQueryWrapperExt<T> le(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.le(function, value);
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

    public <V> LambdaQueryWrapperExt<T> like(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.like(function, value);
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
        return this.likeIfPresent(function, value, SqlLike.LEFT);
    }

    public <V> LambdaQueryWrapperExt<T> likeLeft(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.likeLeft(function, value);
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
        return this.likeIfPresent(function, value, SqlLike.RIGHT);
    }

    public <V> LambdaQueryWrapperExt<T> likeRight(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.likeRight(function, value);
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

    public <V> LambdaQueryWrapperExt<T> like(SFunction<T, V> function, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.like(function, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (LambdaQueryWrapperExt<T>) super.likeLeft(function, value);
            case RIGHT:
                return (LambdaQueryWrapperExt<T>) super.likeRight(function, value);
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
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public <V> LambdaQueryWrapperExt<T> notLikeIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.notLike(ValueValidator.isNotNullOrEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> notLike(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.notLike(function, value);
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

    public <V> LambdaQueryWrapperExt<T> in(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.in(function, values);
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

    public <V> LambdaQueryWrapperExt<T> in(SFunction<T, V> function, @Nullable @Emptable V... values) {
        return (LambdaQueryWrapperExt<T>) super.in(function, values);
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

    public <V> LambdaQueryWrapperExt<T> notIn(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.notIn(function, values);
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

    public <V> LambdaQueryWrapperExt<T> notIn(SFunction<T, V> function, @Nullable @Emptable V... values) {
        return (LambdaQueryWrapperExt<T>) super.notIn(function, values);
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

    public <V> LambdaQueryWrapperExt<T> between(SFunction<T, V> function, @Nullable V from, @Nullable V to) {
        return (LambdaQueryWrapperExt<T>) super.between(function, from, to);
    }

    /**
     * 实现父类的 {@link LambdaQueryWrapper#select(SFunction[])} 方法
     *
     * @param columns 查询字段列表
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    @SafeVarargs
    public final LambdaQueryWrapperExt<T> selector(SFunction<T, ?>... columns) {
        super.select(columns);

        return this;
    }

    /**
     * 实现父类的 {@link LambdaQueryWrapper#select(Class, Predicate)} 方法
     *
     * @param entityClass 实体 {@link Class}
     * @param predicate   过滤方式
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public LambdaQueryWrapperExt<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        super.select(entityClass, predicate);

        return this;
    }

    // ----------------------------------------------------------------

    public LambdaQueryWrapperExt<T> orderByAsc(SFunction<T, ?> column) {
        return this.orderByAsc(true, column);
    }

    public LambdaQueryWrapperExt<T> orderByAsc(boolean condition, SFunction<T, ?> column) {
        super.orderByAsc(condition, column);

        return this;
    }

    public LambdaQueryWrapperExt<T> orderByAsc(List<SFunction<T, ?>> columns) {
        return this.orderByAsc(true, columns);
    }

    public LambdaQueryWrapperExt<T> orderByAsc(boolean condition, List<SFunction<T, ?>> columns) {
        super.orderByAsc(condition, columns);

        return this;
    }

    public LambdaQueryWrapperExt<T> orderByAsc(SFunction<T, ?> column, SFunction<T, ?>... columns) {
        return this.orderByAsc(true, column, columns);
    }

    public LambdaQueryWrapperExt<T> orderByAsc(boolean condition, SFunction<T, ?> column, SFunction<T, ?>... columns) {
        super.orderByAsc(condition, column, columns);

        return this;
    }

    // ----------------------------------------------------------------

    /**
     * 扩展的目的
     * |- 是为了有机会能拿到 {@link LambdaQueryWrapperExt} 对象 而不是 {@link LambdaQueryWrapper}
     *
     * <pre>
     * LambdaQueryWrapperExt<Employee> wrapper = new LambdaQueryWrapperExt<Employee>()
     *                 .selector(Employee::getId, Employee::getEmployeeNo)
     *                 .eq(Employee::getOrgId, 9527L)
     *                 .orderByDesc(Employee::getId)
     *                 .limit(1);
     *
     *         Employee employee = this.employeeRepository.selectOne(wrapper);
     *         String targetSql = wrapper.getTargetSql();
     *
     *         // SELECT id,employee_no FROM sys_employee WHERE deleted=0 AND (org_id = ?) ORDER BY id DESC LIMIT 1
     *         Assertions.assertNull(employee);
     *         Assertions.assertEquals("(org_id = ?) ORDER BY id DESC LIMIT 1", targetSql);
     * </pre>
     *
     * @param column {@link SFunction<T, ?>}
     * @return {@link LambdaQueryWrapperExt<T>}
     * @since 1.5.0
     */
    public LambdaQueryWrapperExt<T> orderByDesc(SFunction<T, ?> column) {
        return this.orderByDesc(true, column);
    }

    public LambdaQueryWrapperExt<T> orderByDesc(boolean condition, SFunction<T, ?> column) {
        super.orderByDesc(condition, column);

        return this;
    }

    public LambdaQueryWrapperExt<T> orderByDesc(List<SFunction<T, ?>> columns) {
        return this.orderByDesc(true, columns);
    }

    public LambdaQueryWrapperExt<T> orderByDesc(boolean condition, List<SFunction<T, ?>> columns) {
        super.orderByDesc(condition, columns);

        return this;
    }

    public LambdaQueryWrapperExt<T> orderByDesc(SFunction<T, ?> column, SFunction<T, ?>... columns) {
        return this.orderByDesc(true, column, columns);
    }

    public LambdaQueryWrapperExt<T> orderByDesc(boolean condition, SFunction<T, ?> column, SFunction<T, ?>... columns) {
        super.orderByDesc(condition, column, columns);

        return this;
    }

    // ----------------------------------------------------------------

    /**
     * LIMIT 1 == wrapper.last("LIMIT 1")
     *
     * @return {@link LambdaQueryWrapperExt<T>}
     * @since 1.5.0
     */
    public LambdaQueryWrapperExt<T> limitOne() {
        return this.limit(1);
    }

    /**
     * LIMIT ${limit} == wrapper.last("LIMIT ${limit}")
     *
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public LambdaQueryWrapperExt<T> limit(int limit) {
        if (limit <= 0) {
            throw new MybatisPlusException("分页参数错误");
        }

        super.last(String.format("LIMIT %d", limit));

        return this;
    }

    // ----------------------------------------------------------------

    /**
     * 用于获取 {@link LambdaQueryWrapper} 自身对象,从而实现一些模板代码,进而提升 {@link LambdaQueryWrapper} 的灵活性
     * <p>
     * For example:
     * <pre>
     * new LambdaQueryWrapperExt<Hello>().thiz((qw)->{}).eq(Hello::getId(), 1L);
     * </pre>
     *
     * @param fx {@link Consumer<LambdaQueryWrapperExt<T>>}
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public LambdaQueryWrapperExt<T> thiz(Consumer<LambdaQueryWrapperExt<T>> fx) {
        return this.thiz(true, fx);
    }

    public LambdaQueryWrapperExt<T> thiz(boolean condition, Consumer<LambdaQueryWrapperExt<T>> fx) {
        if (condition) {
            fx.accept(this);
        }

        return this;
    }
}
