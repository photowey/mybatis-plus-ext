package com.photowey.mybatisplus.ext.meta.filler;

import com.photowey.mybatisplus.ext.core.domain.entity.AbstractEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Objects;

/**
 * {@code StandardMetaPropertiesFiller}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class StandardMetaPropertiesFiller extends AbstractMetaPropertiesFillerAdaptor {

    /**
     * 特别注意: createTime 和 updateTime
     * 不同版本的: 阿里《Java 开发手册》-字段不一致: {@code gmt_create} {@code gmt_modified}
     *
     * @param metaObject {@link MetaObject}
     * @see {@link DefaultMetaPropertiesFiller}
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof AbstractEntity) {
            AbstractEntity abstractEntity = (AbstractEntity) metaObject.getOriginalObject();
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
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof AbstractEntity) {
            AbstractEntity abstractEntity = (AbstractEntity) metaObject.getOriginalObject();
            super.handleUpdateFill(metaObject, abstractEntity);
        }
    }
}
