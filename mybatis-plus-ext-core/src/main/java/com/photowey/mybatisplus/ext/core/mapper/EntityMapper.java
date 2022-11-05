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
package com.photowey.mybatisplus.ext.core.mapper;

import java.util.List;

/**
 * {@code EntityMapper}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.0.0
 */
public interface EntityMapper<D, E> {

    /**
     * Dto to Entity
     *
     * @param dto
     * @return Entity 类型
     */
    E toEntity(D dto);

    /**
     * Entity to Dto
     *
     * @param entity
     * @return Dto 类型
     */
    D toDto(E entity);

    /**
     * Dto to Entity
     *
     * @param dtoList
     * @return Entity 类型
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity to Dto
     *
     * @param entityList
     * @return Dto 类型
     */
    List<D> toDto(List<E> entityList);
}
