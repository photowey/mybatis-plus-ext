package com.photowey.mybatisplus.ext.core.pagination;

import com.photowey.mybatisplus.ext.core.sort.SortColumn;

import java.util.ArrayList;
import java.util.List;

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
    default List<SortColumn> sortColumns() {
        return new ArrayList<>();
    }
}
