package com.photowey.mybatisplus.ext.repository;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * {@code BatchRepositoryExt}
 * 批量执行-扩展
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public interface BatchRepositoryExt<T> extends RepositoryExt<T> {

    Log log = LogFactory.getLog(BatchRepositoryExt.class);

    /**
     * 执行批量保存
     * 默认: 批次大小为: 1000
     * <p>
     * 为什么 需要手动传入-实体类型？
     * 因为: 在接口里面不好通过泛型获取实体类型，故将其作为一个参数传入
     *
     * @param entityList  实体对象列表
     * @param entityClass 实体对象类型
     * @return {@code boolean}
     */
    default boolean batchInserts(Collection<T> entityList, Class<T> entityClass) {
        return this.executeBatch(entityList, entityClass, 1000, SqlMethod.INSERT_ONE);
    }

    /**
     * 执行批量更新
     * 默认: 批次大小为: 1000
     * <p>
     * 为什么 需要手动传入-实体类型？
     * 因为: 在接口里面不好通过泛型获取实体类型，故将其作为一个参数传入
     *
     * @param entityList  实体对象列表
     * @param entityClass 实体对象类型
     * @return {@code boolean}
     */
    default boolean batchUpdates(Collection<T> entityList, Class<T> entityClass) {
        return this.executeBatch(entityList, entityClass, 1000, SqlMethod.UPDATE_BY_ID);
    }

    /**
     * 执行批量保存
     *
     * @param entityList  实体对象列表
     * @param entityClass 实体对象类型
     * @param batchSize   每批次执行的数量
     * @return {@code boolean}
     */
    default boolean executeBatch(Collection<T> entityList, Class<T> entityClass, int batchSize, SqlMethod sqlMethod) {
        return this.executeBatch(entityList, entityClass, batchSize, (sqlSession, entity) -> {
            if (SqlMethod.INSERT_ONE.equals(sqlMethod)) {
                this.insert(entity);
            } else if (SqlMethod.UPDATE_BY_ID.equals(sqlMethod)) {
                this.updateById(entity);
            }
        });
    }

    /**
     * 执行批量
     *
     * @param entityList  实体对象列表
     * @param entityClass 实体对象类型
     * @param batchSize   每批次执行的数量
     * @param callback    执行回调函数
     * @return {@code boolean}
     */
    default boolean executeBatch(Collection<T> entityList, Class<T> entityClass, int batchSize, BiConsumer<SqlSession, T> callback) {
        return SqlHelper.executeBatch(entityClass, this.log(), entityList, batchSize, callback);
    }

    /**
     * 获取日志对象
     *
     * @return {@link Log}
     */
    default Log log() {
        return log;
    }
}
