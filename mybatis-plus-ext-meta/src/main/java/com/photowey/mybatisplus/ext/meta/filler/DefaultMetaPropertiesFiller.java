package com.photowey.mybatisplus.ext.meta.filler;

import com.photowey.mybatisplus.ext.core.domain.entity.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Objects;

/**
 * {@code DefaultMetaPropertiesFiller}
 * 默认的 {@link MetaPropertiesFiller} 实现
 * 数据库时间字段的设置, 还是开始采用: {@code gmt_create} {@code gmt_modified} 为了兼容按照老的《阿里开发手册》实现的项目
 * [藏经阁](https://developer.aliyun.com/topic/ebook)
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
public class DefaultMetaPropertiesFiller extends AbstractMetaPropertiesFillerAdaptor {

    /**
     * 特别注意: createTime 和 updateTime
     * 不同版本的: 阿里《Java 开发手册》-字段不一致: {@code gmt_create} {@code gmt_modified}
     *
     * @param metaObject {@link MetaObject}
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            super.handleInsertFill(baseEntity);
        }
    }

    /**
     * 更新-自动填充
     *
     * @param metaObject {@link MetaObject}
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            super.handleUpdateFill(metaObject, baseEntity);
        }

    }
}
