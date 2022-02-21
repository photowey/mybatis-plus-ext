package com.photowey.mybatisplus.ext.annotation;

import java.lang.annotation.*;

/**
 * {@code ConditionProcessor}
 * 标记-修饰对象是一个条件处理器
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionProcessor {

    // 表征这是一个条件处理器

    /**
     * 目标注解
     *
     * @return 目标注解 {@link Class<? extends Annotation>}
     */
    Class<? extends Annotation> targetAnnotation();
}
