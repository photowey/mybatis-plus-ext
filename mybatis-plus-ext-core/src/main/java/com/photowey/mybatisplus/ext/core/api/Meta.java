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
package com.photowey.mybatisplus.ext.core.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * {@code Meta}
 * 请求元数据
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页元数据", description = "分页元数据")
public class Meta implements Serializable {

    private static final long serialVersionUID = -553396062548580887L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页: 默认:1", example = "1")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long current;

    /**
     * 每页的条数
     */
    @ApiModelProperty(value = "每页的条数:偏移量:默认:20", example = "20")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pageSize;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数", example = "8848")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long total;

    /**
     * 总页数
     */
    @JsonIgnore
    @ApiModelProperty(value = "页数", example = "8848")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long totalPages;

    /**
     * 设置 分页元数据
     *
     * @param page 分页对象
     * @return Meta
     * @see Meta
     */
    public static <D> Meta populateMeta(IPage<D> page) {
        return Meta.builder()
                .current(page.getCurrent())
                .pageSize(page.getSize())
                .total(page.getTotal())
                .totalPages(page.getPages())
                .build();
    }

    public static <D> Meta populateMeta(PageResult<D> page) {
        return Meta.builder()
                .current(page.getData().pageNo())
                .pageSize(page.getData().pageSize())
                .total(page.getData().count())
                .build();
    }

    public static Meta populateDefaultMeta() {
        return populateMeta(20L);
    }

    public static Meta populateMeta(Integer pageSize) {
        return populateMeta(Long.valueOf(pageSize));
    }

    public static Meta populateMeta(Long pageSize) {
        return populateMeta(0L, 1L, pageSize);
    }

    public static Meta populateMeta(Long total, Long pageNo, Long pageSize) {
        return Meta.builder()
                .current(pageNo)
                .pageSize(pageSize)
                .total(total)
                .totalPages(getPages(total, pageSize))
                .build();
    }

    private static long getPages(Long total, Long pageSize) {
        if (pageSize == 0L) {
            return 0L;
        } else {
            long pages = total / pageSize;
            if (total % pageSize != 0L) {
                ++pages;
            }

            return pages;
        }
    }
}