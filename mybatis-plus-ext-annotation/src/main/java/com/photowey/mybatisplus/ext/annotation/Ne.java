package com.photowey.mybatisplus.ext.annotation;

import com.photowey.mybatisplus.ext.enmus.ColumnNamingStrategy;

import java.lang.annotation.*;

/**
 * {@code Ne}
 * 不等于(!= | <>) 条件注解
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ne {

    /**
     * 自定义的属性-别名
     *
     * @return 别名
     */
    String alias() default "";

    /**
     * 默认下划线
     *
     * @return {@link ColumnNamingStrategy}
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
