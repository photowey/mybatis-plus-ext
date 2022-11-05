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

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.time.LocalDateTime;

/**
 * {@code AbstractEntity}
 * 实体数据模型-抽象
 * 相比与 {@link  BaseEntity}
 * 最大的区别就是 - {@code createTime}和 {@code updateTime} 没有添加别名
 * 这个是 阿里不同版本开发手册的变化引起的
 *
 * @param <T> {@link T} 类型
 * @author photowey
 * @date 2022/02/18
 * @see <a src="https://developer.aliyun.com/topic/ebook">藏经阁</a>
 * @since 1.0.0
 */
public abstract class AbstractEntity<T> implements RootEntity {

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
    protected String createBy;
    /**
     * 更新者
     * <p>
     * 使用 String 类型的原因是,未来可能会存在非数值的情况,留好拓展性
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String updateBy;
    /**
     * 是否删除
     * deleted == 1 被删除 - 失效
     * deleted == 0 未被删除 - 有效
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    protected Integer deleted;

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
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}