package com.photowey.mybatisplus.ext.processor.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.And;
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.enmus.SpringContextHolder;
import com.photowey.mybatisplus.ext.processor.condition.IConditionHandler;
import com.photowey.mybatisplus.ext.processor.exception.QueryExtException;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;

/**
 * {@code AndProcessor}
 * AND
 * 注意: {@link ExpressionProcessor} 和 {@link ConditionProcessor} 可以重叠设计, 但是此处还是分开处理
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@ExpressionProcessor(targetAnnotation = And.class)
@ConditionProcessor(targetAnnotation = And.class)
public class AndProcessor<QUERY extends AbstractQuery, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<And, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, And criteriaAnnotation) {

        final Object value = this.columnValue(field, query);
        if (this.isNullOrEmpty(value)) {
            return true;
        }
        String handler = criteriaAnnotation.handler();
        ConfigurableApplicationContext applicationContext = SpringContextHolder.INSTANCE.applicationContext();
        Object bean = applicationContext.getBean(handler);
        if (!(bean instanceof IConditionHandler)) {
            throw new QueryExtException("the handler [%s] not the sub-class of %s", handler, IConditionHandler.class);
        }
        IConditionHandler conditionHandler = (IConditionHandler) bean;
        conditionHandler.handleAnd(queryWrapper, query, field);

        return true;
    }
}
