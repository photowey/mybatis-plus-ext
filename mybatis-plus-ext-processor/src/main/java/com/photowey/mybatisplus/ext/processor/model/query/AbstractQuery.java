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
package com.photowey.mybatisplus.ext.processor.model.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.photowey.mybatisplus.ext.core.pagination.AbstractPaginationModel;
import com.photowey.mybatisplus.ext.core.util.PaginationUtils;
import com.photowey.mybatisplus.ext.processor.advisor.CriteriaAnnotationProcessorAdvisor;
import com.photowey.mybatisplus.ext.query.QueryWrapperExt;

/**
 * {@code AbstractQuery}
 * 查询对象 - 抽象
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public abstract class AbstractQuery<T> extends AbstractPaginationModel {

    // WRAPPER

    /**
     * 通过注解自动包装查询 Wrapper
     *
     * @return {@link  QueryWrapper}
     * @see {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    public QueryWrapper<T> autoWrapper() {
        return CriteriaAnnotationProcessorAdvisor.advise(this, new QueryWrapper<>());
    }

    /**
     * 通过注解自动包装查询 Wrapper
     *
     * @return {@link  QueryWrapperExt}
     * @see {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    public QueryWrapperExt<T> autoWrapperExt() {
        return CriteriaAnnotationProcessorAdvisor.adviseExt(this, new QueryWrapperExt<>());
    }

    // PAGE

    /**
     * 如果需要分页的话
     * 获取分页对象
     *
     * @return {@link IPage}
     * @see {@link com.baomidou.mybatisplus.core.metadata.IPage}
     */
    public IPage populatePage() {
        return PaginationUtils.populatePage(this);
    }

}