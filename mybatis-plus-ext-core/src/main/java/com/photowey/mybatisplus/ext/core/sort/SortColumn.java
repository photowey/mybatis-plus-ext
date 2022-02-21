package com.photowey.mybatisplus.ext.core.sort;

import com.photowey.mybatisplus.ext.enmus.OrderByEnum;

import java.io.Serializable;

/**
 * {@code SortColumn}
 * 排序的字段
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class SortColumn implements Serializable {

    private static final long serialVersionUID = -3509662954357616201L;

    private String column;
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
}
