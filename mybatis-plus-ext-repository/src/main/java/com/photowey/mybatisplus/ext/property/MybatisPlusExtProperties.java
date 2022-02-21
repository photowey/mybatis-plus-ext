package com.photowey.mybatisplus.ext.property;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@code MybatisPlusExtProperties}
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "mybatis-plus.ext")
public class MybatisPlusExtProperties {

    private DbType dbType = DbType.MYSQL;
    private boolean overflow = true;
    private boolean useDeprecatedExecutor = false;

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    public boolean isUseDeprecatedExecutor() {
        return useDeprecatedExecutor;
    }

    public void setUseDeprecatedExecutor(boolean useDeprecatedExecutor) {
        this.useDeprecatedExecutor = useDeprecatedExecutor;
    }
}
