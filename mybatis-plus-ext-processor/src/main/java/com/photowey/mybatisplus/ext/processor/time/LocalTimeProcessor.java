package com.photowey.mybatisplus.ext.processor.time;

import com.photowey.mybatisplus.ext.annotation.component.TimeProcessor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * {@code LocalTimeProcessor}
 * 处理: {@link LocalTime}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@TimeProcessor(dateType = LocalTimeProcessor.class)
public class LocalTimeProcessor implements ITimeProcessor<LocalTime> {

    @Override
    public boolean supports(Class<?> clazz) {
        return LocalTime.class.equals(clazz);
    }

    @Override
    public LocalTime handleTime(Long timeStamp, Class<?> clazz) {
        Instant instant = Instant.ofEpochMilli(timeStamp);

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
    }
}
