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
package com.photowey.mybatisplus.ext.core.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * {@code RootEntity}
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
public interface RootEntity extends Serializable {

    /**
     * 设置-创建时间
     *
     * @param createTime 创建时间
     */
    void setCreateTime(LocalDateTime createTime);

    /**
     * 获取-创建时间
     *
     * @return 创建时间
     */
    LocalDateTime getCreateTime();

    /**
     * 设置-更新时间
     *
     * @param updateTime 更新时间
     */
    void setUpdateTime(LocalDateTime updateTime);

    /**
     * 获取-更新时间
     *
     * @return 更新时间
     */
    LocalDateTime getUpdateTime();

    /**
     * 设置-创建人
     *
     * @param createBy 创建人
     */
    void setCreateBy(String createBy);

    /**
     * 获取-创建人
     *
     * @return 创建人
     */
    String getCreateBy();

    /**
     * 设置-更新人
     *
     * @param updateBy 更新人
     */
    void setUpdateBy(String updateBy);

    /**
     * 获取-更新人
     *
     * @return 更新人
     */
    String getUpdateBy();

    /**
     * 设置-删除标记
     *
     * @param deleted 删除标记
     */
    void setDeleted(Integer deleted);
}
