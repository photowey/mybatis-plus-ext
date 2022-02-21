package com.photowey.mybatisplus.ext.annotation.validation;

import java.lang.annotation.*;

/**
 * {@code Nullable}
 * 可为空-目前仅仅是-起一个提示作用-无实际意义
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Nullable {
}
