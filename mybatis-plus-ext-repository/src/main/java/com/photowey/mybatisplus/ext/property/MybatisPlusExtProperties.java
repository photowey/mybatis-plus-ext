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
