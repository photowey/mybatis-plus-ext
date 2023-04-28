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

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.photowey.mybatisplus.ext.core.domain.entity.CreatorEntity;
import com.photowey.mybatisplus.ext.core.domain.entity.RootEntity;
import com.photowey.mybatisplus.ext.core.domain.operator.Operator;
import com.photowey.mybatisplus.ext.meta.operator.OperatorHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * {@code AbstractMetaPropertiesFillerAdaptor}
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
public abstract class AbstractMetaPropertiesFillerAdaptor implements MetaPropertiesFiller {

    protected ListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    protected OperatorHandler tryAcquireOperatorHandler() {
        try {
            // Select first
            Map<String, OperatorHandler> beans = this.beanFactory.getBeansOfType(OperatorHandler.class);
            if (ObjectUtils.isNotEmpty(beans)) {
                return new ArrayList<>(beans.values()).get(0);
            }
        } catch (Exception ignored) {
        }

        throw new NullPointerException("the handler: [com.photowey.mybatisplus.ext.meta.operator.OperatorHandler] subclass not found(404)");
    }

    protected void handleInsertFill(RootEntity root) {
        LocalDateTime now = LocalDateTime.now();
        if (Objects.isNull(root.getCreateTime())) {
            root.setCreateTime(now);
        }
        if (Objects.isNull(root.getUpdateTime())) {
            root.setUpdateTime(now);
        }

        if (root instanceof CreatorEntity) {
            OperatorHandler operatorHandler = this.tryAcquireOperatorHandler();
            Operator operator = operatorHandler.tryAcquireOperator();
            if (Objects.nonNull(operator)
                    && Objects.nonNull(operator.getOperatorId()) && Objects.isNull(root.getCreateBy())) {
                root.setCreateBy(operator.getOperatorId());
            }
            if (Objects.nonNull(operator)
                    && Objects.nonNull(operator.getOperatorId()) && Objects.isNull(root.getUpdateBy())) {
                root.setUpdateBy(operator.getOperatorId());
            }
        }

        root.setDeleted(0);
    }

    protected void handleUpdateFill(MetaObject metaObject, RootEntity root) {
        LocalDateTime now = LocalDateTime.now();
        setFieldValByName("updateTime", now, metaObject);

        if (root instanceof CreatorEntity) {
            OperatorHandler operatorHandler = this.tryAcquireOperatorHandler();
            Operator operator = operatorHandler.tryAcquireOperator();
            if (Objects.nonNull(operator)
                    && Objects.nonNull(operator.getOperatorId())) {
                setFieldValByName("updateBy", operator.getOperatorId(), metaObject);
            }
        }
    }
}
