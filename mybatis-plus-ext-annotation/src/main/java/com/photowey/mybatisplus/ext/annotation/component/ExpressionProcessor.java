package com.photowey.mybatisplus.ext.annotation.component;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * {@code ExpressionProcessor}
 * 标记是一个 {@code CriteriaAnnotationProcessor} 处理器
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
@Inherited
@Documented
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpressionProcessor {

    /**
     * 目标注解
     *
     * @return 目标注解 {@link Class<? extends Annotation>}
     */
    Class<? extends Annotation> targetAnnotation();
}
