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
