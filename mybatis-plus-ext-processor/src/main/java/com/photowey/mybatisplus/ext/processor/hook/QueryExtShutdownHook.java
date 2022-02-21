package com.photowey.mybatisplus.ext.processor.hook;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@code QueryExtShutdownHook}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class QueryExtShutdownHook extends Thread {

    private final AtomicBoolean destroyed = new AtomicBoolean(false);

    static {
        // 交由-各种的 {@code BeanPostProcessor} 处理
        // Runtime.getRuntime().addShutdownHook(getInstance());
    }

    private QueryExtShutdownHook(final String name) {
        super(name);
    }

    private static class QueryExtShutdownHookFactory {
        private static final QueryExtShutdownHook INSTANCE = new QueryExtShutdownHook("query-ext-shutdown-hook");
    }

    public static QueryExtShutdownHook getInstance() {
        return QueryExtShutdownHookFactory.INSTANCE;
    }

    @Override
    public void run() {
        closeAll();
    }

    private void closeAll() {
        if (!destroyed.compareAndSet(false, true)) {
            return;
        }
        // 清理-缓存
        // 交由-各种的 {@code BeanPostProcessor} 处理
        // CriteriaAnnotationProcessorAdvisor.destroyProcessorCache();
        // TimeProcessorContainer.destroyProcessorCache();
    }
}
