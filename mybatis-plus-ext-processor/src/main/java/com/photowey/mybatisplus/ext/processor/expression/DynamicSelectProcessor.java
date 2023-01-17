/*
 * Copyright © 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photowey.mybatisplus.ext.processor.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.annotation.DynamicSelect;
import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import org.springframework.util.ObjectUtils;

import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * {@code DynamicSelectProcessor}
 * {@link DynamicSelect} 动态字段过滤
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
@ExpressionProcessor(targetAnnotation = DynamicSelect.class)
@ConditionProcessor(targetAnnotation = DynamicSelect.class)
public class DynamicSelectProcessor<QUERY extends AbstractQuery<ENTITY>, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<DynamicSelect, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, DynamicSelect criteriaAnnotation) {
        final Object value = this.columnValue(field, query);
        if (this.isNullOrEmpty(value)) {
            return true;
        }

        if (!(value instanceof Collection)) {
            throw new ValidationException("Annotation @DynamicSelect can only modify properties of list type");
        }

        Collection<Object> fields = (Collection<Object>) value;
        if (ObjectUtils.isEmpty(fields)) {
            return true;
        }

        String[] fieldx = fields.stream().map(String::valueOf).toArray(String[]::new);
        queryWrapper.select(fieldx);

        return true;
    }
}

