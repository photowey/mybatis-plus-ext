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
    default <Q extends AbstractQuery<T>> IPage<T> dynamicPage(Q query) {
        IPage<T> page = query.populatePage();
        page.setRecords(new ArrayList<>(0));

        return page;
    }

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
        query.enableThresholdPageSize();
    }

}