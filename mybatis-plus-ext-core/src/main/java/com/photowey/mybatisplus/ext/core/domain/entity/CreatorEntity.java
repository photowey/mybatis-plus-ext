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
package com.photowey.mybatisplus.ext.core.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

/**
 * {@code CreatorEntity}
 * 实体数据模型-抽象
 *
 * @param <T> {@link T} 类型
 * @author photowey
 * @date 2023/04/26
 * @since 1.3.0
 */
public abstract class CreatorEntity<T> extends StandardEntity<T> implements RootEntity {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;
    /**
     * 最后更新时间
     * 第一次执行 == {@code createTime}
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 创建者
     * <p>
     * 使用 String 类型的原因是,未来可能会存在非数值的情况,留好拓展性
     */
    @TableField(fill = FieldFill.INSERT)
    protected Long createBy;
    /**
     * 更新者
     * <p>
     * 使用 String 类型的原因是,未来可能会存在非数值的情况,留好拓展性
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Long updateBy;

    @Override
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Long getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Override
    public Long getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}