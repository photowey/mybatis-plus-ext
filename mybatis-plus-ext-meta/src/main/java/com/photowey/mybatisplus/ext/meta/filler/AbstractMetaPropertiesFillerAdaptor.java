package com.photowey.mybatisplus.ext.meta.filler;

import com.photowey.mybatisplus.ext.core.domain.entity.RootEntity;
import com.photowey.mybatisplus.ext.meta.operator.OperatorHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * {@code AbstractMetaPropertiesFillerAdaptor}
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
public abstract class AbstractMetaPropertiesFillerAdaptor implements MetaPropertiesFiller {

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected OperatorHandler loginUserHandler() {
        OperatorHandler operatorHandler = null;
        try {
            operatorHandler = this.applicationContext.getBean(OperatorHandler.class);
        } catch (Exception ignored) {
        }
        if (null == operatorHandler) {
            throw new NullPointerException(String.format("the [%s] subclass not found(404)", OperatorHandler.class.getName()));
        }

        return operatorHandler;
    }

    protected void handleInsertFill(RootEntity rootEntity) {
        LocalDateTime now = LocalDateTime.now();
        if (Objects.isNull(rootEntity.getCreateTime())) {
            rootEntity.setCreateTime(now);
        }
        if (Objects.isNull(rootEntity.getUpdateTime())) {
            rootEntity.setUpdateTime(now);
        }
        OperatorHandler operatorHandler = this.loginUserHandler();
        String operator = operatorHandler.loadOperator();
        if (Objects.nonNull(operator) && Objects.isNull(rootEntity.getCreateBy())) {
            rootEntity.setCreateBy(operator);
        }
        if (Objects.nonNull(operator) && Objects.isNull(rootEntity.getUpdateBy())) {
            rootEntity.setUpdateBy(operator);
        }

        rootEntity.setDeleted(0);
    }

    protected void handleUpdateFill(MetaObject metaObject, RootEntity rootEntity) {
        LocalDateTime now = LocalDateTime.now();
        setFieldValByName("updateTime", now, metaObject);
        OperatorHandler operatorHandler = this.loginUserHandler();
        String operator = operatorHandler.loadOperator();
        if (Objects.nonNull(operator)) {
            setFieldValByName("updateBy", operator, metaObject);
        }
    }
}
