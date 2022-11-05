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
package com.photowey.mybatisplus.ext.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.photowey.mybatisplus.ext.core.pagination.IPagination;
import com.photowey.mybatisplus.ext.core.sort.SortColumn;
import com.photowey.mybatisplus.ext.enmus.OrderByEnum;
import com.photowey.mybatisplus.ext.validator.ValueValidator;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * {@code PaginationUtils}
 * 分页-工具类
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public final class PaginationUtils {

    private PaginationUtils() {
        // utils class; can't create
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }

    public static <T, Q extends IPagination> IPage<T> populatePage(Q query) {

        return populatePage(query, null);
    }

    public static <T, Q extends IPagination> IPage<T> populatePage(Q query, Consumer<IPage<T>> callback) {
        Page<T> page = new Page<>(query.getPageNo(), query.getPageSize());

        Collection<SortColumn> columns = query.getSortColumns();
        if (ValueValidator.isNotNullOrEmpty(columns)) {
            page.addOrder(columns.stream()
                    .map(column -> OrderByEnum.ASC.equals(column.getOrderBy())
                            ? OrderItem.asc(column.getColumn())
                            : OrderItem.desc(column.getColumn()
                    )).collect(Collectors.toList()));
        }

        if (null != callback) {
            callback.accept(page);
        }

        return page;
    }
}
