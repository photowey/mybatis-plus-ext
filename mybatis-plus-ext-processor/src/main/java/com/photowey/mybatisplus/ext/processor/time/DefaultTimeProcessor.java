package com.photowey.mybatisplus.ext.processor.time;

import com.photowey.mybatisplus.ext.annotation.component.TimeProcessor;
import com.photowey.mybatisplus.ext.processor.util.TimeUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * {@code DefaultTimeProcessor}
 * 默认处理为: {@link LocalDateTime}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@TimeProcessor(dateType = LocalDateTime.class)
public class DefaultTimeProcessor implements ITimeProcessor<LocalDateTime> {

    @Override
    public boolean supports(Class<?> clazz) {
        return Date.class.equals(clazz);
    }

    @Override
    public LocalDateTime handleTime(Long timeStamp, Class<?> clazz) {

        LocalDateTime date = TimeUtils.toTime(timeStamp, clazz);
        return date;
    }
}
