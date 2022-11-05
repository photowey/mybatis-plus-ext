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
package com.photowey.mybatisplus.ext.core.pagination;

import com.photowey.mybatisplus.ext.core.sort.SortColumn;

import java.util.HashSet;
import java.util.Set;

/**
 * {@code IPagination}
 * 分页-数据模型-抽象-根接口
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public interface IPagination {

    int DEFAULT_PAGE_NO = 1;
    int DEFAULT_PAGE_SIZE = 10;

    int PAGE_NO_THRESHOLD = 1;
    int PAGE_SIZE_THRESHOLD = 100;

    /**
     * 当前页码
     *
     * @return {@code int} 当前页码
     */
    int getPageNo();

    /**
     * 每页展示条数(偏移量)
     *
     * @return {@code int} 每页展示条数(偏移量)
     */
    int getPageSize();

    /**
     * 参与排序的字段列表
     *
     * @return {@link  SortColumn} 排序列表
     */
    default Set<SortColumn> getSortColumns() {
        return new HashSet<>();
    }
}
