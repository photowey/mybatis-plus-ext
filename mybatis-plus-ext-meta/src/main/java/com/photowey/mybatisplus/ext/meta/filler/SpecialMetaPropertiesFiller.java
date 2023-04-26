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
package com.photowey.mybatisplus.ext.meta.filler;

import com.photowey.mybatisplus.ext.core.domain.entity.SpecialEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Objects;

/**
 * {@code SpecialMetaPropertiesFiller}
 *
 * @author photowey
 * @date 2023/04/26
 * @since 1.3.0
 */
public class SpecialMetaPropertiesFiller extends AbstractMetaPropertiesFillerAdaptor {

    /**
     * 特别注意: createTime 和 updateTime
     * 不同版本的: 阿里《Java 开发手册》-字段不一致: {@code gmt_create} {@code gmt_modified}
     *
     * @param metaObject {@link MetaObject}
     * @see {@link DefaultMetaPropertiesFiller}
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof SpecialEntity) {
            SpecialEntity abstractEntity = (SpecialEntity) metaObject.getOriginalObject();
            super.handleInsertFill(abstractEntity);
        }
    }

    /**
     * 更新-自动填充
     *
     * @param metaObject {@link MetaObject}
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof SpecialEntity) {
            SpecialEntity abstractEntity = (SpecialEntity) metaObject.getOriginalObject();
            super.handleUpdateFill(metaObject, abstractEntity);
        }
    }
}
