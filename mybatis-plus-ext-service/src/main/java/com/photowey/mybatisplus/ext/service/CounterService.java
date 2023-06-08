package com.photowey.mybatisplus.ext.service;

import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.util.function.Function;

/**
 * {@code CounterService}
 *
 * @author photowey
 * @date 2023/06/08
 * @since 1.0.0
 */
public interface CounterService<T> {

    default <Q extends AbstractQuery<T>> Long determineCounter(Q query, Function<Q, Long> fx) {
        Long total = fx.apply(query);

        return null == total ? 0L : total;
    }
}
