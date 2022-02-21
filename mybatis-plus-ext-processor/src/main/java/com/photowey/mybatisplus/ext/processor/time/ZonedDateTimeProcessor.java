package com.photowey.mybatisplus.ext.processor.time;

import com.photowey.mybatisplus.ext.annotation.component.TimeProcessor;
import com.photowey.mybatisplus.ext.processor.util.TimeUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * {@code ZonedDateTimeProcessor}
 * 处理: {@link ZonedDateTime}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@TimeProcessor(dateType = LocalTimeProcessor.class)
public class ZonedDateTimeProcessor implements ITimeProcessor<ZonedDateTime> {

    @Override
    public boolean supports(Class<?> clazz) {
        return ZonedDateTime.class.equals(clazz);
    }

    @Override
    public ZonedDateTime handleTime(Long timeStamp, Class<?> clazz) {
        LocalDateTime localDateTime = TimeUtils.timestampToLocalDateTime(timeStamp);

        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }
}
