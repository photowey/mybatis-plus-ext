package com.photowey.mybatisplus.ext.processor.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.annotation.Select;
import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;

/**
 * {@code SelectProcessor}
 * Select 字段过滤
 *
 * @author photowey
 * @date 2022/10/23
 * @since 1.0.0
 */
@ExpressionProcessor(targetAnnotation = Select.class)
@ConditionProcessor(targetAnnotation = Select.class)
public class SelectProcessor<QUERY extends AbstractQuery<ENTITY>, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<Select, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Select criteriaAnnotation) {
        String[] fields = criteriaAnnotation.value();
        if (ObjectUtils.isEmpty(fields)) {
            return true;
        }

        queryWrapper.select(fields);

        return true;
    }
}

