package com.photowey.mybatisplus.ext.processor.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code DefaultConditionHandler}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class DefaultConditionHandler implements IConditionHandler {

    @Override
    public void handleAnd(QueryWrapper queryWrapper, AbstractQuery query, Field field) {
        queryWrapper.and((wrapper) -> {
        });
    }

    @Override
    public void handleOr(QueryWrapper queryWrapper, AbstractQuery query, Field field) {
        queryWrapper.or((wrapper) -> {
        });
    }
}
