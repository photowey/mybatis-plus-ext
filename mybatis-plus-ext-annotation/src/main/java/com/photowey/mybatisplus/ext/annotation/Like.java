package com.photowey.mybatisplus.ext.annotation;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.photowey.mybatisplus.ext.enmus.ColumnNamingStrategy;

import java.lang.annotation.*;

/**
 * {@code Like}
 * 模糊查询(LIKE) 条件注解
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Like {

    /**
     * 自定义的属性值
     *
     * @return
     */
    String alias() default "";

    /**
     * 匹配模式
     *
     * @return SqlLike
     * @see {@link SqlLike}
     */
    SqlLike like() default SqlLike.DEFAULT;

    /**
     * 默认下划线
     *
     * @return ColumnNamingStrategy
     */
    ColumnNamingStrategy naming() default ColumnNamingStrategy.LOWER_CASE_UNDER_LINE;
}
