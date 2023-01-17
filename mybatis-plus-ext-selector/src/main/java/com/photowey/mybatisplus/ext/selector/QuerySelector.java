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
package com.photowey.mybatisplus.ext.selector;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.List;

/**
 * {@code QuerySelector}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
public interface QuerySelector<D> {

    /**
     * 查询单值对象
     *
     * @param properties 属性列表
     * @return {@link D}
     */
    D one(String... properties);

    /**
     * 查询列表对象
     *
     * @param properties 期望返回的字段列表
     * @return {@link List <D>}
     */
    List<D> list(String... properties);

    /**
     * 判断
     * 命名是否为: 下划线模式
     *
     * @param word 单词
     * @return {@code boolean}
     */
    default boolean isUnderline(String word) {
        return StringUtils.matches(".*[_]+.*", word);
    }

    /**
     * 转下划线
     *
     * @param word 单词
     * @return {@code boolean}
     */
    default String toColumn(String word) {
        return this.isUnderline(word) ? word : StringUtils.camelToUnderline(word);
    }
}
