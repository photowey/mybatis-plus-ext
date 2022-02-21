package com.photowey.mybatisplus.ext.annotation.component;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.time.LocalDateTime;

/**
 * {@code TimeProcessor}
 * 标记是一个 {@code ITimeProcessor} 处理器
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
public @interface TimeProcessor {

    /**
     * 目标注解
     *
     * @return 目标日期类型
     */
    Class<?> dateType() default LocalDateTime.class;
}
