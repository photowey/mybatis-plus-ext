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
package com.photowey.mybatisplus.ext.processor.parser;

import com.photowey.mybatisplus.ext.annotation.CriteriaQuery;
import com.photowey.mybatisplus.ext.processor.callback.CriteriaFieldCallback;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * {@code CriteriaFieldParser}
 * 属性-解析器
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public final class CriteriaFieldParser {

    private static final Map<Class<?>, List<Field>> CLASS_FIELD_REF_CACHE = new ConcurrentHashMap<>(256);

    private CriteriaFieldParser() {
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }

    /**
     * 遍历字段
     *
     * @param query    查询对象
     * @param callback 回调函数
     * @param <QUERY>  查询类型
     */
    public static <QUERY extends AbstractQuery<?>> void foreachCriteriaField(final QUERY query, final CriteriaFieldCallback callback) {
        List<Field> fieldList = CLASS_FIELD_REF_CACHE.get(query.getClass());
        if (fieldList == null) {
            synchronized (CLASS_FIELD_REF_CACHE) {
                fieldList = CLASS_FIELD_REF_CACHE.get(query.getClass());
                if (fieldList == null) {
                    fieldList = new ArrayList<>();
                    for (Class<?> clazz = query.getClass(); clazz != Object.class;
                         clazz = clazz.getSuperclass()) {
                        final Field[] fields = clazz.getDeclaredFields();
                        fieldList.addAll(Arrays.stream(fields).collect(Collectors.toList()));
                    }
                    CLASS_FIELD_REF_CACHE.put(query.getClass(), fieldList);
                }
            }
        }
        if (fieldList.size() > 0) {
            CriteriaFieldParser.foreachCriteriaField(fieldList, callback);
        }
    }

    private static void foreachCriteriaField(final List<Field> fieldList, final CriteriaFieldCallback callback) {
        for (final Field field : fieldList) {
            final Annotation[] annotations = field.getDeclaredAnnotations();
            for (final Annotation annotation : annotations) {
                if (annotation.annotationType().isAnnotationPresent(CriteriaQuery.class)) {
                    if (!callback.invoke(field, annotation)) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 遍历字段
     *
     * @param clazz    查询对象 Class
     * @param callback 回调函数
     */
    private static void foreachCriteriaField(final Class<?> clazz, final CriteriaFieldCallback callback) {
        final Field[] fields = clazz.getDeclaredFields();
        for (final Field field : fields) {
            final Annotation[] annotations = field.getDeclaredAnnotations();
            for (final Annotation annotation : annotations) {
                if (annotation.annotationType().isAnnotationPresent(CriteriaQuery.class)) {
                    if (!callback.invoke(field, annotation)) {
                        break;
                    }
                }
            }
        }
    }
}
