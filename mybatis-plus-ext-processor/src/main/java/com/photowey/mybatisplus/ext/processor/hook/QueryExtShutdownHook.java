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
