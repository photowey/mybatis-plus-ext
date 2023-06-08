package com.photowey.mybatisplus.ext.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code GracefulPaginationService}
 *
 * @author photowey
 * @date 2023/06/08
 * @since 1.0.0
 */
public interface GracefulPaginationService<T> extends CounterService<T> {

    /**
     * 动态单表分页查询
     *
     * @param query {@link Q}
     * @return {@link IPage <T>}
     */
    <Q extends AbstractQuery<T>> IPage<T> dynamicPage(Q query);

    /**
     * 优雅的动态
     * 单表-列表查询
     *
     * @param query {@link Q}
     * @return {@link List <T>}
     */
    default <Q extends AbstractQuery<T>> List<T> gracefulList(Q query) {
        int pageNo = 1;
        List<T> tts = new ArrayList<>();
        this.enableThresholdPageSize(query);

        while (true) {
            query.setPageNo(pageNo);
            IPage<T> page = this.dynamicPage(query);
            List<T> records = page.getRecords();
            if (null != records && records.isEmpty()) {
                break;
            } else {
                tts.addAll(records);
                pageNo++;
            }

            if (page.getCurrent() == page.getPages()) {
                break;
            }
        }

        return tts;
    }

    default <Q extends AbstractQuery<T>> void enableThresholdPageSize(Q query) {
        query.setPageNo(AbstractQuery.DEFAULT_PAGE_NO);
        query.setPageSize(AbstractQuery.DEFAULT_PAGE_SIZE);
    }

}