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
