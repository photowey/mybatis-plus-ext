package com.photowey.mybatisplus.ext.annotation;

import com.photowey.mybatisplus.ext.enmus.CompareEnum;
import com.photowey.mybatisplus.ext.enmus.ColumnNamingStrategy;

import java.lang.annotation.*;
import java.time.LocalDateTime;

/**
 * {@code Timestamp}
 * 时间戳 条件注解
 * 使用场景:
 * 有些时候前端是将时间戳传入后台 这个时候需要自动将时间戳转换为时间对象
 * Long -> LocalDateTime
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Timestamp {

    /**
     * 自定义的属性值
     *
     * @return
     */
    String alias() default "";

    /**
     * 比较运行符
     *
     * @return CompareEnum
     * @see {@link CompareEnum}
     */
    CompareEnum compare() default CompareEnum.EQ;

    /**
     * 转换的时间对象
     * 默认采用 {@link LocalDateTime}
     *
     * @return
     * @see {@link LocalDateTime}
     */
    Class<?> clazz() default LocalDateTime.class;

    /**
     * 默认下划线
     *
     * @return ColumnNamingStrategy
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
