package com.photowey.mybatisplus.ext.processor.time;

import com.photowey.mybatisplus.ext.annotation.component.TimeProcessor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * {@code LocalDateProcessor}
 * 处理: {@link LocalDate}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@TimeProcessor(dateType = LocalDate.class)
public class LocalDateProcessor implements ITimeProcessor<LocalDate> {

    @Override
    public boolean supports(Class<?> clazz) {
        return LocalDate.class.equals(clazz);
    }

    @Override
    public LocalDate handleTime(Long timeStamp, Class<?> clazz) {
        Instant instant = Instant.ofEpochMilli(timeStamp);

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }
}
