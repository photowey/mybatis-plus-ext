package com.photowey.mybatisplus.ext.annotation;

import java.lang.annotation.*;

/**
 * {@code Select}
 *
 * @author photowey
 * @date 2022/10/23
 * @since 1.0.0
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {

    // 慎重使用 - 该写法可能导致 SQL 注入问题

    /**
     * 需要过滤的字段列表
     *
     * @return
     */
    String[] value() default {};
}
