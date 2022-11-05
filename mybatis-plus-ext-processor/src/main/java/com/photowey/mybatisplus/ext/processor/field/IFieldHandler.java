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
package com.photowey.mybatisplus.ext.processor.field;

/**
 * {@code IFieldHandler}
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
public interface IFieldHandler {

    /**
     * 判定是不是自己支持的 目标对象
     *
     * @param context {@link FieldContext} 属性上下文
     * @return {@code boolean}
     */
    default boolean supports(FieldContext context) {

        return false;
    }

    /**
     * 处理-目标对象-对应的字段逻辑
     *
     * @param context {@link FieldContext} 属性上下文
     * @return {@code boolean}
     */
    boolean handleField(FieldContext context);
}
