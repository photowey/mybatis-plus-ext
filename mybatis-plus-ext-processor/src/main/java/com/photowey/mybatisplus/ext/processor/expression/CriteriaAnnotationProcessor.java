package com.photowey.mybatisplus.ext.processor.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * {@code CriteriaAnnotationProcessor}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public interface CriteriaAnnotationProcessor<A extends Annotation, QUERY extends AbstractQuery, WRAPPER extends QueryWrapper<ENTITY>, ENTITY> {
    /**
     * 处理注解
     *
     * @param queryWrapper       查询包装器
     * @param field              属性
     * @param query              query 对象
     * @param criteriaAnnotation 条件注解
     * @return 布尔值
     */
    boolean process(WRAPPER queryWrapper, final Field field, final QUERY query, final A criteriaAnnotation);
}
