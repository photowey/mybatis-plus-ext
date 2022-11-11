/*
 * Copyright © 2022 the original author or authors (photowey@gmail.com)
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
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.annotation.Ne;
import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code NeProcessor}
 * {@link Ne} 注解处理器
 *
 * @param <QUERY>  自定义查询 Query
 * @param <ENTITY> 查询想对应的实体类型
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@ExpressionProcessor(targetAnnotation = Ne.class)
@ConditionProcessor(targetAnnotation = Ne.class)
public class NeProcessor<QUERY extends AbstractQuery, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<Ne, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Ne criteriaAnnotation) {

        final Object value = this.columnValue(field, query);
        if (this.isNullOrEmpty(value)) {
            // 属性值为 Null OR Empty 跳过
            return true;
        }

        String columnName = criteriaAnnotation.alias();
        if (ObjectUtils.isEmpty(columnName)) {
            columnName = this.columnName(field, criteriaAnnotation.naming());
        }
        assert columnName != null;
        queryWrapper.ne(null != value, columnName, value);

        return true;
    }
}
