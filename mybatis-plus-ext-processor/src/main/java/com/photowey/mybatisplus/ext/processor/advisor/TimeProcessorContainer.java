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
package com.photowey.mybatisplus.ext.processor.advisor;

import com.photowey.mybatisplus.ext.processor.constant.QueryConstants;
import com.photowey.mybatisplus.ext.processor.time.*;
import com.photowey.mybatisplus.ext.processor.util.ScanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code TimeProcessorContainer}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class TimeProcessorContainer {

    private static Map<Class<?>, ITimeProcessor<?>> TIME_PROCESSOR_CACHE = new ConcurrentHashMap<>(16);

    static {
        // 改为: 采用 {@code Spring} {@code BeanPostProcessor} 处理
        // handleTimeProcessorCacheByPackageScan();
    }

    // --------------------------------------------------------------------------------------- HANDLE PROCESSOR

    public static Map<Class<?>, ITimeProcessor<?>> getTimeProcessorCache() {
        return TIME_PROCESSOR_CACHE;
    }

    public static void put(Class<?> targetClazz, ITimeProcessor<?> timeProcessor) {
        TIME_PROCESSOR_CACHE.put(targetClazz, timeProcessor);
    }

    private static void handleTimeProcessorCacheByPackageScan() {
        try {
            String basePackage = QueryConstants.TIME_PROCESSOR_SCAN_BASE_PACKAGE;
            TIME_PROCESSOR_CACHE = ScanUtils.doTimeScan(basePackage);
        } catch (Exception e) {
            handleTimeProcessorCacheByNew();
        }
    }

    private static void handleTimeProcessorCacheByNew() {
        // TIME_PROCESSOR_CACHE = new HashMap<>(8);

        // Date
        TIME_PROCESSOR_CACHE.put(Date.class, new DefaultTimeProcessor());

        // LocalDateTime
        TIME_PROCESSOR_CACHE.put(LocalDateTime.class, new LocalDateTimeProcessor());

        // LocalDate
        TIME_PROCESSOR_CACHE.put(LocalDate.class, new LocalDateProcessor());
        // LocalTime
        TIME_PROCESSOR_CACHE.put(LocalTime.class, new LocalTimeProcessor());

        // ZonedDateTime
        TIME_PROCESSOR_CACHE.put(ZonedDateTime.class, new ZonedDateTimeProcessor());
    }

    public static Collection<ITimeProcessor<?>> timeProcessors() {
        return TIME_PROCESSOR_CACHE.values();
    }

    public static void destroyProcessorCache() {
        if (null != TIME_PROCESSOR_CACHE && TIME_PROCESSOR_CACHE.size() > 0) {
            TIME_PROCESSOR_CACHE.clear();
        }
    }
}
