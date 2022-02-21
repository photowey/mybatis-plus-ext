package com.photowey.mybatisplus.ext.processor.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.annotation.Or;
import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.enmus.SpringContextHolder;
import com.photowey.mybatisplus.ext.processor.condition.IConditionHandler;
import com.photowey.mybatisplus.ext.processor.exception.QueryExtException;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;

/**
 * {@code OrProcessor}
 * OR
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@ExpressionProcessor(targetAnnotation = Or.class)
@ConditionProcessor(targetAnnotation = Or.class)
public class OrProcessor<QUERY extends AbstractQuery, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<Or, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Or criteriaAnnotation) {

        final Object value = this.columnValue(field, query);
        if (this.isNullOrEmpty(value)) {
            return true;
        }
        String handler = criteriaAnnotation.handler();
        ConfigurableApplicationContext applicationContext = SpringContextHolder.INSTANCE.applicationContext();
        Object bean = applicationContext.getBean(handler);
        if (!(bean instanceof IConditionHandler)) {
            throw new QueryExtException("the handler [%s] not the sub-class of %s", handler, IConditionHandler.class.getName());
        }
        IConditionHandler conditionHandler = (IConditionHandler) bean;
        conditionHandler.handleOr(queryWrapper, query, field);

        return true;
    }
}
