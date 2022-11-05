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
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.annotation.OrderBy;
import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.enmus.OrderByEnum;
import com.photowey.mybatisplus.ext.enmus.OrderByMechanismEnum;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * {@code OrderByProcessor}
 * {@link OrderBy} 注解处理器
 *
 * @param <QUERY>  自定义查询 Query
 * @param <ENTITY> 查询想对应的实体类型
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@ExpressionProcessor(targetAnnotation = OrderBy.class)
@ConditionProcessor(targetAnnotation = OrderBy.class)
public class OrderByProcessor<QUERY extends AbstractQuery, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<OrderBy, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, OrderBy criteriaAnnotation) {
        Object value = this.columnValue(field, query);
        OrderByMechanismEnum handleType = criteriaAnnotation.mechanism();
        if (OrderByMechanismEnum.DYNAMIC.equals(handleType) && this.isNullOrEmpty(value)) {
            return true;
        }
        if (OrderByMechanismEnum.STATIC.equals(handleType)) {
            // 保证 参与排序
            value = "1";
        }
        String columnName = criteriaAnnotation.alias();
        if (StringUtils.isEmpty(columnName)) {
            columnName = this.columnName(field, criteriaAnnotation.naming());
        }
        assert columnName != null;
        OrderByEnum orderBy = criteriaAnnotation.orderBy();
        switch (orderBy) {
            case DESC:
                queryWrapper.orderByDesc(null != value, columnName);
                break;
            default:
                queryWrapper.orderByAsc(null != value, columnName);
                break;
        }

        return true;
    }
}
