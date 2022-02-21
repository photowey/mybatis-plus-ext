package com.photowey.mybatisplus.ext.processor.time;

import com.photowey.mybatisplus.ext.processor.util.TimeUtils;
import com.photowey.mybatisplus.ext.annotation.component.TimeProcessor;

import java.util.Date;

/**
 * {@code DateTimeProcessor}
 * 默认处理为: {@link Date}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
@TimeProcessor(dateType = Date.class)
public class DateTimeProcessor implements ITimeProcessor<Date> {

    @Override
    public boolean supports(Class<?> clazz) {
        return Date.class.equals(clazz);
    }

    @Override
    public Date handleTime(Long timeStamp, Class<?> clazz) {

        Date date = TimeUtils.toTime(timeStamp, clazz);
        return date;
    }
}
