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
package com.photowey.mybatisplus.ext.core.sort;

import com.photowey.mybatisplus.ext.enmus.OrderByEnum;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@code SortColumn}
 * 排序的字段
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class SortColumn implements Serializable {

    private static final long serialVersionUID = -7493258064912684126L;

    @ApiModelProperty(value = "列名", required = false, example = "id")
    private String column;
    @ApiModelProperty(value = "排序", required = false, example = "DESC")
    private OrderByEnum orderBy;

    public SortColumn() {
    }

    public SortColumn(String column, OrderByEnum orderBy) {
        this.column = column;
        this.orderBy = orderBy;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public OrderByEnum getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderByEnum orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SortColumn)) {
            return false;
        }
        SortColumn that = (SortColumn) o;
        return Objects.equals(column, that.column) && orderBy == that.orderBy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, orderBy);
    }
}