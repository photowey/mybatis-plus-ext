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
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * {@code AbstractEntity}
 * 实体数据模型-抽象
 * 相比与 {@link  StandardEntity}
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
     * 是否删除
     * deleted == 1 被删除 - 失效
     * deleted == 0 未被删除 - 有效
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    protected Integer deleted;


    public Integer getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}