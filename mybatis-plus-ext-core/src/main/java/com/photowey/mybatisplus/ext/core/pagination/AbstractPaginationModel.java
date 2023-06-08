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
package com.photowey.mybatisplus.ext.core.pagination;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.photowey.mybatisplus.ext.annotation.DynamicSelect;
import com.photowey.mybatisplus.ext.core.sort.SortColumn;
import com.photowey.mybatisplus.ext.enmus.OrderByEnum;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code AbstractPaginationModel}
 * 分页-数据模型-抽象
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public abstract class AbstractPaginationModel implements IPagination, Serializable {

    /**
     * 当前页码
     */
    @NotNull(message = "当前页码-不能为空")
    @Min(value = 1, message = "当前页码-最小值为 1")
    @ApiModelProperty(value = "当前页码", required = false, example = "1")
    protected int pageNo = DEFAULT_PAGE_NO;

    /**
     * 每页展示条数(偏移量)
     */
    @NotNull(message = "每页展示条数(偏移量)-不能为空")
    @Min(value = 1, message = "每页展示条数(偏移量)-最小值为 1")
    @Max(value = 100, message = "每页展示条数(偏移量)-最大值为 100")
    @ApiModelProperty(value = "每页展示条数(偏移量)", required = false, example = "10")
    protected int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 是否为列表查询
     *
     * @since 1.1.0
     */
    @ApiModelProperty(hidden = true)
    protected Boolean queryList = Boolean.FALSE;

    /**
     * 参与排序的字段列表
     *
     * @since 1.3.0 隐藏属性 {@code sortColumns},不接受前端传值, 所有的操作,开发者必须心知肚明
     */
    @JsonIgnore
    @ApiModelProperty(value = "参与排序的字段列表", required = false, hidden = true)
    protected Set<SortColumn> sortColumns = new HashSet<>();

    /**
     * @since 1.1.0
     */
    @DynamicSelect
    @ApiModelProperty(hidden = true)
    protected Set<String> fields = new HashSet<>();

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public Integer getLimit() {
        return this.getPageSize();
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public Integer getOffset() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    @Override
    public int getPageNo() {
        // 排除绕过框架验证 - 手动设置 非法的值
        return this.pageNo < PAGE_NO_THRESHOLD ? PAGE_NO_THRESHOLD : this.pageNo;
    }

    @Override
    public int getPageSize() {
        return this.pageSize > PAGE_SIZE_THRESHOLD ? PAGE_SIZE_THRESHOLD : this.pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void selectOne() {
        this.selectPage(1, 1);
    }

    public void selectLimit(int pageSize) {
        this.selectPage(1, pageSize);
    }

    public void selectPage(int current, int pageSize) {
        this.setPageNo(current);
        this.setPageSize(pageSize);
    }

    @JsonIgnore
    public Boolean isQueryList() {
        return this.getQueryList();
    }

    public Boolean getQueryList() {
        return null != this.queryList ? this.queryList : Boolean.FALSE;
    }

    public void setQueryList(Boolean queryList) {
        this.queryList = queryList;
    }

    /**
     * 强制设置 {@code pageSize}
     *
     * @since 1.1.0
     */
    public void enableThresholdPageSize() {
        this.pageSize = PAGE_SIZE_THRESHOLD;
    }

    @Override
    @JsonIgnore
    public Set<SortColumn> getSortColumns() {
        return this.sortColumns;
    }

    public Set<String> getFields() {
        return this.fields;
    }

    public Set<String> fields() {
        return this.fields;
    }

    @ApiModelProperty(hidden = true)
    public void setFields(Set<String> fields) {
        if (ObjectUtils.isEmpty(fields)) {
            return;
        }
        this.fields = fields;
    }

    @ApiModelProperty(hidden = true)
    public void setFields(Set<String> fields, boolean replace) {
        if (ObjectUtils.isEmpty(fields)) {
            return;
        }
        if (replace) {
            this.setFields(fields);
        } else {
            if (ObjectUtils.isNotEmpty(fields)) {
                this.fields.addAll(fields);
            } else {
                this.setFields(fields);
            }
        }
    }

    @ApiModelProperty(hidden = true)
    public void setFieldz(String... fields) {
        if (ObjectUtils.isEmpty(fields)) {
            return;
        }
        this.fields = this.toSet(fields);
    }

    @ApiModelProperty(hidden = true)
    public void setFieldz(boolean replace, String... fields) {
        if (ObjectUtils.isEmpty(fields)) {
            return;
        }
        Set<String> toSet = this.toSet(fields);
        if (replace) {
            this.fields = toSet;
        } else {
            if (ObjectUtils.isNotEmpty(fields)) {
                this.fields.addAll(toSet);
            } else {
                this.fields = toSet;
            }
        }
    }

    public synchronized void addSortColumn(SortColumn sortColumn) {
        if (ObjectUtils.isEmpty(sortColumn)) {
            return;
        }
        if (ObjectUtils.isEmpty(sortColumn.getColumn())) {
            throw new NullPointerException("请传入: 排序字段");
        }
        if (ObjectUtils.isEmpty(sortColumn.getOrderBy())) {
            throw new NullPointerException("请传入: 排序方式");
        }

        this.checkNull();

        this.sortColumns.add(sortColumn);
    }

    public void sort(String column, OrderByEnum orderBy) {
        this.addSortColumn(new SortColumn(column, orderBy));
    }

    public void asc(String column) {
        this.sort(column, OrderByEnum.ASC);
    }

    public void desc(String column) {
        this.sort(column, OrderByEnum.DESC);
    }

    /**
     * 默认采用下划线格式
     *
     * @param properties 属性列表
     * @return {@link Set<String>}
     * @since 1.1.0
     */
    public Set<String> toSet(String... properties) {
        return Stream.of(properties).map(prop -> {
            return isUnderline(prop) ? prop : StringUtils.camelToUnderline(prop);
        }).collect(Collectors.toSet());
    }

    public static boolean isUnderline(String word) {
        return StringUtils.matches(".*[_]+.*", word);
    }

    private void checkNull() {
        if (this.sortColumns == null) {
            this.sortColumns = new HashSet<>();
        }
    }
}
