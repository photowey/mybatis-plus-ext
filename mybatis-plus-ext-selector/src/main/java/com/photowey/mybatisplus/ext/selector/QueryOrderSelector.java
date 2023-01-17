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
package com.photowey.mybatisplus.ext.selector;

/**
 * {@code QueryOrderSelector}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
public interface QueryOrderSelector<SELECTOR extends QueryOrderSelector<SELECTOR, T, D>, T, D> extends QuerySelector<D> {

    /**
     * 升序排序
     * - 默认 主键标识 {@code id} 属性
     * <p>
     * 注意:
     * 这个是属性 - 而非数据库字段
     * - 由框架 自己处理
     *
     * @param properties 升序排序字段
     * @return {@link SELECTOR}
     */
    SELECTOR asc(String... properties);

    /**
     * 降序排序
     * - 默认 主键标识 {@code id} 属性
     * <p>
     * 注意:
     * 这个是属性 - 而非数据库字段
     * - 由框架 自己处理
     *
     * @param properties 升序排序字段
     * @return {@link SELECTOR}
     */
    SELECTOR desc(String... properties);

}
