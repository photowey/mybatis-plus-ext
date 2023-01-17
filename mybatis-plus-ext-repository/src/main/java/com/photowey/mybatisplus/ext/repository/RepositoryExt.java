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
package com.photowey.mybatisplus.ext.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.photowey.mybatisplus.ext.annotation.validation.Emptable;
import com.photowey.mybatisplus.ext.annotation.validation.NotNull;
import com.photowey.mybatisplus.ext.annotation.validation.Nullable;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import com.photowey.mybatisplus.ext.query.QueryWrapperExt;
import com.photowey.mybatisplus.ext.validator.ValueValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * {@code RepositoryExt}
 * 仓储类-扩展
 * <p>
 * 总体来说:
 * 1.列表的查询 - 基本上参数值允许为空或者空列表
 * 2.单对象和部分 {@code COUNT(*)} 不支持空参数 - 这个需要注意代码的执行逻辑和校验
 * --可参考参数对应修饰的注解 {@link Emptable} {@link NotNull} {@link Nullable}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public interface RepositoryExt<T> extends BaseMapper<T> {

    /**
     * 分页查询
     *
     * @param query {@link AbstractQuery} 查询对象
     * @return {@link IPage<T>} 分页对象
     */
    default IPage<T> selectPage(AbstractQuery<T> query) {
        QueryWrapperExt<T> queryWrapperExt = query.autoWrapperExt();
        IPage<T> page = query.populatePage();
        return this.selectPage(page, queryWrapperExt);
    }

    /**
     * 分页查询
     *
     * @param query    {@link AbstractQuery} 查询对象
     * @param callback 回调函数
     * @return {@link IPage<T>} 分页对象
     */
    default IPage<T> selectPage(AbstractQuery<T> query, Consumer<QueryWrapperExt<T>> callback) {
        QueryWrapperExt<T> queryWrapperExt = query.autoWrapperExt();
        callback.accept(queryWrapperExt);

        IPage<T> page = query.populatePage();
        return this.selectPage(page, queryWrapperExt);
    }

    /**
     * 根据字段 {@code column} 和参数值 {@code value}查询一个对象
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型
     * @return {@link T} 类型
     */
    default <V> T selectOne(String column, @NotNull V value) {
        this.checkNull(column, value);

        return selectOne(this.createQueryWrapper().eq(column, value));
    }

    /**
     * 根据 {@link QueryWrapper} 回调函数-自定义查询一个对象
     *
     * @param callback wrapper 回调函数
     * @return {@link T} 类型
     */
    default T selectOne(Consumer<QueryWrapper<T>> callback) {
        QueryWrapper<T> queryWrapper = this.createQueryWrapper();
        callback.accept(queryWrapper);

        return selectOne(queryWrapper);
    }

    /**
     * 根据字段 {@code column} 和参数值 {@code value}查询一个对象
     *
     * @param column   字段名称
     * @param value    参数值
     * @param callback wrapper 回调函数
     * @param <V>      参数类型
     * @return {@link T} 类型
     */
    default <V> T selectOne(String column, @NotNull V value, Consumer<QueryWrapper<T>> callback) {
        this.checkNull(column, value);
        QueryWrapper<T> queryWrapper = this.createQueryWrapper().eq(column, value);
        callback.accept(queryWrapper);

        return selectOne(queryWrapper);
    }

    /**
     * 根据字段 {@code function} lambda 表达式和参数值 {@code value}查询一个对象
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型
     * @return {@link T} 类型
     */
    default <V> T selectOne(SFunction<T, ?> function, @NotNull V value) {
        this.checkNull(value);

        return selectOne(this.createLambdaQueryWrapper().eq(function, value));
    }

    /**
     * 根据 {@link LambdaQueryWrapper} 回调函数-自定义查询一个对象
     *
     * @param callback wrapper 回调函数
     * @param <V>      参数类型
     * @return {@link T} 类型
     */
    default <V> T lambdaOne(Consumer<LambdaQueryWrapper<T>> callback) {
        LambdaQueryWrapper<T> queryWrapper = this.createLambdaQueryWrapper();
        callback.accept(queryWrapper);

        return selectOne(queryWrapper);
    }

    /**
     * 根据字段 {@code function} lambda 表达式和参数值 {@code value}查询一个对象
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param callback wrapper 回调函数
     * @param <V>      参数类型
     * @return {@link T} 类型
     */
    default <V> T selectOne(SFunction<T, ?> function, @NotNull V value, Consumer<LambdaQueryWrapper<T>> callback) {
        this.checkNull(value);
        LambdaQueryWrapper<T> queryWrapper = this.createLambdaQueryWrapper().eq(function, value);
        callback.accept(queryWrapper);

        return selectOne(queryWrapper);
    }

    /**
     * 数量查询
     * 根据字段 {@code column} 和参数值 {@code value} 统计数量-如果查询结果为 {@code null} 默认返回 0
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型
     * @return {@link T} 类型
     */
    default <V> long selectCount(String column, @NotNull V value) {
        this.checkNull(value);

        return Optional.ofNullable(this.selectCount(this.createQueryWrapper().eq(column, value))).orElse(0L);
    }

    /**
     * 数量统计
     * 根据字段 {@code column} 和参数值 {@code value} 统计数量-如果查询结果为 {@code null} 默认返回 0
     *
     * @param column   字段名称
     * @param value    参数值
     * @param callback wrapper 回调函数
     * @param <V>      参数类型
     * @return {@link T} 类型
     */
    default <V> long selectCount(String column, @NotNull V value, Consumer<QueryWrapper<T>> callback) {
        this.checkNull(value);

        QueryWrapper<T> queryWrapper = this.createQueryWrapper().eq(column, value);
        callback.accept(queryWrapper);

        return Optional.ofNullable(this.selectCount(queryWrapper)).orElse(0L);
    }

    /**
     * 数量统计
     * 根据字段 {@code function} lambda 表达式和参数值 {@code value} 统计数量-如果查询结果为 {@code null} 默认返回 0
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param <V>      参数类型
     * @return {@link T} 类型
     */
    default <V> long selectCount(SFunction<T, ?> function, @NotNull V value) {
        this.checkNull(value);

        return Optional.ofNullable(this.selectCount(this.createLambdaQueryWrapper().eq(function, value))).orElse(0L);
    }

    /**
     * 数量统计
     * 根据字段 {@code function} lambda 表达式和参数值 {@code value} 统计数量-如果查询结果为 {@code null} 默认返回 0
     *
     * @param function 属性 {@code lambda} 表达式
     * @param value    参数值
     * @param callback wrapper 回调函数
     * @param <V>      参数类型
     * @return {@link T} 类型
     */
    default <V> long selectCount(SFunction<T, ?> function, @NotNull V value, Consumer<LambdaQueryWrapper<T>> callback) {
        this.checkNull(value);

        LambdaQueryWrapper<T> queryWrapper = this.createLambdaQueryWrapper().eq(function, value);
        callback.accept(queryWrapper);

        return Optional.ofNullable(this.selectCount(queryWrapper)).orElse(0L);
    }

    /**
     * 列表查询
     *
     * @return {@link T} 类型列表
     */
    default List<T> selectList() {
        return selectList(this.createQueryWrapper());
    }

    /**
     * 根据字段 {@code column} 和参数值 {@code value}查询列表
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(String column, @Nullable V value) {
        return selectList(this.createQueryWrapper().eq(ValueValidator.isNotNullOrEmpty(value), column, value));
    }

    /**
     * 根据字段 {@code column} 和参数值 {@code value}查询列表
     *
     * @param column   字段名称
     * @param value    参数值
     * @param callback wrapper 回调函数
     * @param <V>      参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(String column, @Nullable V value, Consumer<QueryWrapper<T>> callback) {
        QueryWrapper<T> queryWrapper = this.createQueryWrapper().eq(ValueValidator.isNotNullOrEmpty(value), column, value);
        callback.accept(queryWrapper);

        return selectList(queryWrapper);
    }

    /**
     * 根据字段 {@code function} lambda 表达式和参数值 {@code value}查询列表
     * ==
     *
     * @param function 字段名称
     * @param value    参数值
     * @param <V>      参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(SFunction<T, ?> function, @Nullable V value) {
        return selectList(this.createLambdaQueryWrapper().eq(ValueValidator.isNotNullOrEmpty(value), function, value));
    }

    /**
     * 根据字段 {@code function} lambda 表达式和参数值 {@code value}查询列表
     * ==
     *
     * @param function 字段名称
     * @param value    参数值
     * @param callback wrapper 回调函数
     * @param <V>      参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(SFunction<T, ?> function, @Nullable V value, Consumer<LambdaQueryWrapper<T>> callback) {
        LambdaQueryWrapper<T> queryWrapper = this.createLambdaQueryWrapper().eq(ValueValidator.isNotNullOrEmpty(value), function, value);
        callback.accept(queryWrapper);

        return selectList(queryWrapper);
    }


    /**
     * 根据字段 {@code column} 和参数值列表 {@code values} 查询列表
     * 如果参数列表为空 - 默认继续执行查询
     *
     * @param column 字段名称
     * @param values 参数值列表
     * @param <V>    参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(String column, Collection<V> values) {
        return selectList(column, values, true);
    }

    /**
     * 根据字段 {@code column} 和参数值列表 {@code values} 查询列表
     * 如果参数列表为空 - 默认继续执行查询
     * IN
     *
     * @param column     字段名称
     * @param values     参数值列表
     * @param emptyQuery 如果参数值列表为空 - 还是否进行后续查询操作 true: 依然执行 false: 不进行执行-直接返回空列表
     * @param <V>        参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(String column, @Nullable @Emptable Collection<V> values, boolean emptyQuery) {
        if (!emptyQuery && ValueValidator.isNullOrEmpty(values)) {
            // 未了采用 Collections.emptyList() || Lists.newArrayList(), 是为了避免出现 {@link java.lang.UnsupportedOperationException}
            return new ArrayList<>();
        }
        return selectList(this.createQueryWrapper().in(column, values));
    }

    /**
     * 根据字段 {@code function} lambda 表达式 和参数值列表 {@code values} 查询列表
     * 如果参数列表为空 - 默认继续执行查询
     * IN
     *
     * @param function 字段名称
     * @param values   参数值列表
     * @param <V>      参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(SFunction<T, ?> function, @Nullable @Emptable Collection<V> values) {
        return selectList(function, values, true);
    }

    /**
     * 根据字段 {@code function} lambda 表达式 和参数值列表 {@code values} 查询列表
     * 如果参数列表为空 - 默认继续执行查询
     * IN
     *
     * @param function   字段 lambda 表达式
     * @param values     参数值列表
     * @param emptyQuery 如果参数值列表为空 - 还是否进行后续查询操作 true: 依然执行 false: 不进行执行-直接返回空列表
     * @param <V>        参数类型
     * @return {@link T} 类型列表
     */
    default <V> List<T> selectList(SFunction<T, ?> function, @Nullable @Emptable Collection<V> values, boolean emptyQuery) {
        if (!emptyQuery && ValueValidator.isNullOrEmpty(values)) {
            // 未了采用 Collections.emptyList() || Lists.newArrayList(), 是为了避免出现 {@link java.lang.UnsupportedOperationException}
            return new ArrayList<>();
        }
        return selectList(this.createLambdaQueryWrapper().in(function, values));
    }

    /**
     * 删除数据库
     *
     * @return 受影响的行数
     */
    default int deleteAll() {
        return delete(this.createQueryWrapper());
    }

    /**
     * 根据字段 {@code column} 和参数-删除数据
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型
     * @return 受影响的行数
     */
    default <V> int delete(String column, @NotNull V value) {
        this.checkNull(column, value);
        return delete(this.createQueryWrapper().eq(column, value));
    }

    /**
     * 根据字段 {@code column} 和参数-删除数据
     *
     * @param column   字段名称
     * @param value    参数值
     * @param callback 回调函数
     * @param <V>      参数类型
     * @return 受影响的行数
     */
    default <V> int delete(String column, @NotNull V value, Consumer<QueryWrapper<T>> callback) {
        this.checkNull(column, value);
        QueryWrapper<T> queryWrapper = this.createQueryWrapper().eq(column, value);
        callback.accept(queryWrapper);

        return delete(queryWrapper);
    }

    /**
     * 根据字段 {@code function} lambda 表达式 和参数-删除数据
     *
     * @param function 字段 lambda 表达式
     * @param value    参数值
     * @param <V>      参数类型
     * @return 受影响的行数
     */
    default <V> int delete(SFunction<T, ?> function, @NotNull V value) {
        this.checkNull(value);
        return delete(this.createLambdaQueryWrapper().eq(function, value));
    }

    /**
     * 根据字段 {@code function} lambda 表达式 和参数-删除数据
     *
     * @param function 字段 lambda 表达式
     * @param value    参数值
     * @param callback 回调函数
     * @param <V>      参数类型
     * @return 受影响的行数
     */
    default <V> int delete(SFunction<T, ?> function, @NotNull V value, Consumer<LambdaQueryWrapper<T>> callback) {
        this.checkNull(value);
        LambdaQueryWrapper<T> queryWrapper = this.createLambdaQueryWrapper().eq(function, value);
        callback.accept(queryWrapper);

        return delete(queryWrapper);
    }

    /**
     * 验证 参数 {@code value} 是否为 null 否则抛出 {@link NullPointerException}
     *
     * @param column 字段名称
     * @param value  参数值
     * @param <V>    参数类型
     */
    default <V> void checkNull(String column, V value) {
        if (null == value) {
            throw new NullPointerException(String.format("the param:[%s] value can't be null", column));
        }
    }

    /**
     * 验证 参数 {@code value} 是否为 null 否则抛出 {@link NullPointerException}
     *
     * @param value 参数值
     * @param <V>   参数类型
     */
    default <V> void checkNull(V value) {
        if (null == value) {
            throw new NullPointerException("the param value can't be null");
        }
    }

    /**
     * 判断主键是否存在
     *
     * @param pk   主键标识
     * @param <PK> 主键类型
     * @return {@code boolean}
     */
    default <PK> boolean existz(PK pk) {
        return this.exists(this.createQueryWrapper().eq("id", pk));
    }

    /**
     * 判断主键是否存在
     * |- 如果存在, 则执行回调函数 {@code then}
     *
     * @param pk   主键标识
     * @param fx   {@link QueryWrapper}回调函数
     * @param then 回调函数
     * @param <PK> 主键类型
     * @return {@code boolean}
     */
    default <PK> boolean existz(PK pk, Consumer<QueryWrapper<T>> fx, Consumer<T> then) {
        QueryWrapper<T> wrapper = this.createQueryWrapper().eq("id", pk);
        fx.accept(wrapper);
        T t = this.selectOne(wrapper);
        boolean exists = ObjectUtils.isNotNull(t);
        if (exists) {
            then.accept(t);
        }

        return exists;
    }

    /**
     * 判断主键是否存在
     * |- 如果存在, 则执行回调函数 {@code then}
     *
     * @param pk   主键标识
     * @param fx   {@link LambdaQueryWrapper}回调函数
     * @param then 回调函数
     * @param <PK> 主键类型
     * @return {@code boolean}
     */
    default <PK> boolean lambdaExistz(PK pk, Consumer<LambdaQueryWrapper<T>> fx, Consumer<T> then) {
        LambdaQueryWrapper<T> wrapper = this.createLambdaQueryWrapper();
        fx.accept(wrapper);
        T t = this.selectOne(wrapper);
        boolean exists = ObjectUtils.isNotNull(t);
        if (exists) {
            then.accept(t);
        }

        return exists;
    }

    default QueryWrapper<T> createQueryWrapper() {
        return new QueryWrapper<>();
    }

    default LambdaQueryWrapper<T> createLambdaQueryWrapper() {
        return new LambdaQueryWrapper<>();
    }
}
