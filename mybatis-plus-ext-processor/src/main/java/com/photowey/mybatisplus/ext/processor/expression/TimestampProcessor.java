package com.photowey.mybatisplus.ext.processor.expression;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.annotation.Timestamp;
import com.photowey.mybatisplus.ext.enmus.CompareEnum;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import com.photowey.mybatisplus.ext.annotation.component.ExpressionProcessor;
import com.photowey.mybatisplus.ext.processor.util.TimeUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * {@code TimestampProcessor}
 * {@link Timestamp} 注解处理器
 *
 * @param <QUERY>  自定义查询 Query
 * @param <ENTITY> 查询想对应的实体类型
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@ExpressionProcessor(targetAnnotation = Timestamp.class)
@ConditionProcessor(targetAnnotation = Timestamp.class)
public class TimestampProcessor<QUERY extends AbstractQuery, ENTITY>
        extends CriteriaAnnotationProcessorAdaptor<Timestamp, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Timestamp criteriaAnnotation) {

        final Object value = this.columnValue(field, query);
        if (this.isNullOrEmpty(value)) {
            // 属性值为 Null OR Empty 跳过
            return true;
        }
        String columnName = criteriaAnnotation.alias();
        if (StringUtils.isEmpty(columnName)) {
            columnName = this.columnName(field, criteriaAnnotation.naming());
        }
        assert columnName != null;
        long timeStamp = Long.valueOf(String.valueOf(value));
        CompareEnum compare = criteriaAnnotation.compare();
        // TODO 这儿考虑到有些是 JDK 7 的场景,所以默认采用 {@link java.util.Date}
        // TODO 如果是JDK 8 及以上的话 可以考虑采用 {@link java.time.LocalDateTime}
        Class<?> clazz = criteriaAnnotation.clazz();
        switch (compare) {
            case EQ:
                // queryWrapper.eq(null != value, columnName, TimeUtils.toTime(timeStamp, clazz));
                // @since 1.1.0
                queryWrapper.eq(null != value, columnName, TimeUtils.toTime(timeStamp, clazz));
                break;
            case NE:
                queryWrapper.ne(null != value, columnName, TimeUtils.toTime(timeStamp, clazz));
                break;
            case GE:
                queryWrapper.ge(null != value, columnName, TimeUtils.toTime(timeStamp, clazz));
                break;
            case GT:
                queryWrapper.gt(null != value, columnName, TimeUtils.toTime(timeStamp, clazz));
                break;
            case LE:
                queryWrapper.le(null != value, columnName, TimeUtils.toTime(timeStamp, clazz));
                break;
            default:
                queryWrapper.lt(null != value, columnName, TimeUtils.toTime(timeStamp, clazz));
                break;
        }

        return true;
    }

}
