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
package com.photowey.mybatisplus.ext.core.api.struct;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.photowey.mybatisplus.ext.core.api.Meta;
import com.photowey.mybatisplus.ext.validator.ValueValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code ListStruct}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
@Data
public class ListStruct<T> implements Serializable {

    private static final long serialVersionUID = -514945485472820274L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页: 默认:1", example = "1")
    private int pageNo = 1;

    /**
     * 每页的条数
     */
    @ApiModelProperty(value = "每页的条数:偏移量:默认:20", example = "20")
    private int pageSize = 10;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数", example = "8848")
    private int count;

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private List<T> list = new ArrayList<>(0);

    /**
     * 附加参数
     */
    @ApiModelProperty(value = "附加参数")
    private Map<String, Object> additional = new HashMap<>();

    public ListStruct() {
    }

    public ListStruct(List<T> list) {
        this.pageNo = 1;
        this.pageSize = 20;
        this.count = 0;
        this.list = list;
    }

    public ListStruct(List<T> list, Meta meta) {
        this(list, meta, null);
    }

    public ListStruct(List<T> list, Meta meta, Map<String, Object> additional) {
        this.pageNo = ValueValidator.isNotNullOrEmpty(meta.getCurrent()) ? meta.getCurrent().intValue() : 1;
        this.pageSize = ValueValidator.isNotNullOrEmpty(meta.getPageSize()) ? meta.getPageSize().intValue() : 20;
        this.count = ValueValidator.isNotNullOrEmpty(meta.getTotal()) ? meta.getTotal().intValue() : 0;
        this.list = list;
        if (ValueValidator.isNullOrEmpty(additional)) {
            additional = new HashMap<>(2);
        }

        this.additional = additional;
    }

    public ListStruct(Long pageNo, Long pageSize, Long count, List<T> list) {
        this.pageNo = ValueValidator.isNotNullOrEmpty(pageNo) ? pageNo.intValue() : 1;
        this.pageSize = ValueValidator.isNotNullOrEmpty(pageSize) ? pageSize.intValue() : 20;
        this.count = ValueValidator.isNotNullOrEmpty(count) ? count.intValue() : 0;
        this.list = list;
    }

    public ListStruct(IPage<T> page) {
        this.pageNo = ValueValidator.isNotNullOrEmpty(page.getCurrent()) ? (int) page.getCurrent() : 1;
        this.pageSize = ValueValidator.isNotNullOrEmpty(page.getSize()) ? (int) page.getSize() : 20;
        this.count = ValueValidator.isNotNullOrEmpty(page.getTotal()) ? (int) page.getTotal() : 0;
        this.list = page.getRecords();
    }

    public static <D> ListStruct<D> create() {
        return new ListStruct<>();
    }

    public static <D> ListStruct<D> create(Meta meta) {
        ListStruct<D> listStruct = create();
        listStruct.pageNo(meta.getCurrent());
        listStruct.pageSize(meta.getPageSize());
        listStruct.count(meta.getTotal());

        return listStruct;
    }

    public static <D> ListStruct<D> of(List<D> data) {
        ListStruct<D> listStruct = create(Meta.populateDefaultMeta());
        listStruct.list(data);

        return listStruct;
    }

    public static <D> ListStruct<D> of(List<D> data, Meta meta) {
        ListStruct<D> listStruct = create(meta);
        listStruct.list(data);

        return listStruct;
    }

    public static <D> ListStruct<D> of(List<D> data, Meta meta, Map<String, Object> additional) {
        ListStruct<D> listStruct = create(meta);
        listStruct.list(data);
        listStruct.additional(additional);

        return listStruct;
    }

    public static <D> ListStruct<D> of(IPage<D> page) {
        Meta meta = Meta.populateMeta(page);
        return of(page.getRecords(), meta);
    }

    public static <D> ListStruct<D> of(IPage<D> page, Map<String, Object> additional) {
        Meta meta = Meta.populateMeta(page);
        return of(page.getRecords(), meta, additional);
    }

    public Long pageNo() {
        return (long) pageNo;
    }

    public Long pageSize() {
        return (long) pageSize;
    }

    public Long count() {
        return (long) count;
    }

    public List<T> list() {
        return list;
    }

    public Map<String, Object> additional() {
        return additional;
    }

    public ListStruct<T> pageNo(Long pageNo) {
        this.pageNo = ValueValidator.isNotNullOrEmpty(pageNo) ? pageNo.intValue() : 1;
        return this;
    }

    public ListStruct<T> pageSize(Long pageSize) {
        this.pageSize = ValueValidator.isNotNullOrEmpty(pageSize) ? pageSize.intValue() : 20;
        return this;
    }

    public ListStruct<T> count(Long count) {
        this.count = ValueValidator.isNotNullOrEmpty(count) ? count.intValue() : 0;
        return this;
    }

    public ListStruct<T> list(List<T> list) {
        this.list = list;
        return this;
    }

    public ListStruct<T> additional(Map<String, Object> additional) {
        if (ValueValidator.isNullOrEmpty(additional)) {
            additional = new HashMap<>(2);
        }
        this.additional = additional;

        return this;
    }
}
