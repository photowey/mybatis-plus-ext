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
package com.photowey.mybatisplus.ext.processor.time;

/**
 * {@code ITimeProcessor}
 * 时间 {@code Processor}-根接口
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public interface ITimeProcessor<T> {

    /**
     * 是否支持处理改该 类型(T) 的时间
     *
     * @param clazz 时间类型
     * @return 布尔值
     */
    boolean supports(Class<?> clazz);

    /**
     * 处理时间
     *
     * @param timeStamp 时间戳
     * @param clazz     时间类型
     * @return T 类型
     */
    T handleTime(Long timeStamp, Class<?> clazz);
}
