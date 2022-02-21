package com.photowey.mybatisplus.ext.processor.field;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.enmus.ColumnNamingStrategy;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * {@code FieldContext}
 * 字段上下文
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
public class FieldContext implements Serializable {

    private static final long serialVersionUID = 2157283919914482770L;

    private QueryWrapper queryWrapper;
    private AbstractQuery query;
    private Field field;
    private ColumnNamingStrategy naming;

    public FieldContext() {

    }

    public FieldContext(QueryWrapper queryWrapper, AbstractQuery query, Field field, ColumnNamingStrategy naming) {
        this.queryWrapper = queryWrapper;
        this.query = query;
        this.field = field;
        this.naming = naming;
    }

    public QueryWrapper getQueryWrapper() {
        return queryWrapper;
    }

    public void setQueryWrapper(QueryWrapper queryWrapper) {
        this.queryWrapper = queryWrapper;
    }

    public AbstractQuery getQuery() {
        return query;
    }

    public void setQuery(AbstractQuery query) {
        this.query = query;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ColumnNamingStrategy getNaming() {
        return naming;
    }

    public void setNaming(ColumnNamingStrategy naming) {
        this.naming = naming;
    }
}
