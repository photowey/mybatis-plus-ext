package com.photowey.mybatisplus.ext.processor.callback;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * {@code CriteriaFieldCallback}
 * 回调函数 - 查找属性对应的注解处理器进行处理
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@FunctionalInterface
public interface CriteriaFieldCallback {

    /**
     * 调用注解处理器 - 处理字段
     *
     * @param field              Query对象的属性
     * @param criteriaAnnotation 条件注解
     * @return 布尔值
     */
    boolean invoke(final Field field, final Annotation criteriaAnnotation);
}
