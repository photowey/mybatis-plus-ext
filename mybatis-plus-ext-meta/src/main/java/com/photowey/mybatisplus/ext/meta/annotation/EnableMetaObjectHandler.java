package com.photowey.mybatisplus.ext.meta.annotation;

import com.photowey.mybatisplus.ext.meta.config.MybatisExtMetaAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * {@code EnableMetaObjectHandler}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(MybatisExtMetaAutoConfigure.class)
public @interface EnableMetaObjectHandler {
}
