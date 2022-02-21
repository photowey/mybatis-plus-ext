package com.photowey.mybatisplus.ext.processor.time;

import com.photowey.mybatisplus.ext.annotation.component.TimeProcessor;
import com.photowey.mybatisplus.ext.processor.util.TimeUtils;

import java.time.LocalDateTime;

/**
 * {@code LocalDateTimeProcessor}
 * 处理: {@link LocalDateTime}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@TimeProcessor(dateType = LocalDateTime.class)
public class LocalDateTimeProcessor implements ITimeProcessor<LocalDateTime> {

    @Override
    public boolean supports(Class<?> clazz) {
        return LocalDateTime.class.equals(clazz);
    }

    @Override
    public LocalDateTime handleTime(Long timeStamp, Class<?> clazz) {

        return TimeUtils.timestampToLocalDateTime(timeStamp);
    }
}
