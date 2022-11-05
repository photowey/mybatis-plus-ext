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
package com.photowey.mybatisplus.ext.selector;

import com.photowey.mybatisplus.ext.core.api.PageResult;

/**
 * {@code QueryPageSelector}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
public interface QueryPageSelector<SELECTOR extends QueryPageSelector<SELECTOR, T, D>, T, D> extends QueryOrderSelector<SELECTOR, T, D> {

    /**
     * 分页页码
     *
     * @return {@link SELECTOR}
     */
    SELECTOR pageNo(int pageNo);

    /**
     * 分页的数量
     *
     * @return {@link SELECTOR}
     */
    SELECTOR pageSize(int pageSize);

    /**
     * @param properties 期望返回的字段列表
     * @return {@link PageResult <D>}
     */
    PageResult<D> page(String... properties);

    /**
     * @param pageNo     分页页码
     * @param pageSize   分页的数量
     * @param properties 期望返回的字段列表
     * @return {@link PageResult<D>}
     */
    PageResult<D> page(int pageNo, int pageSize, String... properties);
}
