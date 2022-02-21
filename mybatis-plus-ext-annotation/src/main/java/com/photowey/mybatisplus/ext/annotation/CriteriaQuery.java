package com.photowey.mybatisplus.ext.annotation;

import java.lang.annotation.*;

/**
 * {@code CriteriaQuery}
 * 标记-修饰对象是一个条件注解
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Inherited
@Documented
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CriteriaQuery {
}
