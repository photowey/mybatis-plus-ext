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
package com.photowey.mybatisplus.ext.processor.util;

import com.photowey.mybatisplus.ext.processor.advisor.TimeProcessorContainer;
import com.photowey.mybatisplus.ext.processor.exception.QueryExtException;
import com.photowey.mybatisplus.ext.processor.time.ITimeProcessor;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

/**
 * {@code TimeUtils}
 * 时间-工具类
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public final class TimeUtils {

    private TimeUtils() {
        // utils class; can't create
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }

    // ----------------------------------------------------------------===========================

    /**
     * 将时间戳对象 转换为 Date 类型时间
     *
     * @param time 时间戳
     * @return 转换后的时间
     */
    public static Date toTime(Timestamp time) {
        if (null == time) {
            return null;
        }
        return new Date(time.getTime());
    }

    /**
     * 将长整型-时间戳 转换为 Date 类型时间
     *
     * @param timeStamp 时间戳
     * @return 转换后的时间
     */
    public static Date toTime(Long timeStamp) {
        if (null == timeStamp) {
            return null;
        }

        return new Date(timeStamp);
    }

    /**
     * 时间戳 转 时间对象
     *
     * @param timeStamp 时间戳
     * @param clazz     时间类型
     * @param <T>       T 泛型
     * @return T 类型
     */
    public static <T> T toTime(Long timeStamp, Class<?> clazz) {
        if (null == timeStamp) {
            return null;
        }

        Collection<ITimeProcessor<?>> timeProcessors = TimeProcessorContainer.timeProcessors();
        for (ITimeProcessor<?> timeProcessor : timeProcessors) {
            if (timeProcessor.supports(clazz)) {
                return (T) timeProcessor.handleTime(timeStamp, clazz);
            }
        }

        return (T) new Date(timeStamp);
    }

    // ----------------------------------------------------------------

    public static Date epochMilliToDate(Long timestamp) {
        if (null == timestamp) {
            return null;
        }

        return new Date(timestamp);
    }
    
    /**
     * LocalDateTime -> Timestamp(Long)
     *
     * @param localDateTime {@link LocalDateTime}
     * @return 时间戳 {@link Long}
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zoneId).toInstant();
            return instant.toEpochMilli();
        } catch (Exception e) {
            throw new QueryExtException("convert the LocalDateTime to EpochMilli exception", e);
        }
    }

    /**
     * Timestamp(Long) -> LocalDateTime
     *
     * @param timestamp 时间戳 {@link Long}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        } catch (Exception e) {
            throw new QueryExtException("convert the EpochMilli to  LocalDateTime exception", e);
        }
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date {@link Date}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (Exception e) {
            throw new QueryExtException("convert the Date to  LocalDateTime exception", e);
        }
    }

    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime {@link LocalDateTime}
     * @return {@link Date}
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
            throw new QueryExtException("convert the LocalDateTime to  Date exception", e);
        }
    }

}
