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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    protected int pageNo = DEFAULT_PAGE_NO;

    /**
     * 每页展示条数(偏移量)
     */
    @NotNull(message = "每页展示条数(偏移量)-不能为空")
    @Min(value = 1, message = "每页展示条数(偏移量)-最小值为 1")
    @Max(value = 100, message = "每页展示条数(偏移量)-最大值为 100")
    protected int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 参与排序的列表
     */
    protected List<SortColumn> sortColumns = new ArrayList<>();

    @Override
    public int getPageNo() {
        // 排除绕过框架验证 - 手动设置 非法的值
        return pageNo < PAGE_NO_THRESHOLD ? PAGE_NO_THRESHOLD : pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public int getPageSize() {
        return pageSize > PAGE_SIZE_THRESHOLD ? PAGE_SIZE_THRESHOLD : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<SortColumn> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<SortColumn> sortColumns) {
        this.sortColumns = sortColumns;
    }
}
