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
package com.photowey.mybatisplus.ext.processor.util;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.photowey.mybatisplus.ext.annotation.ConditionProcessor;
import com.photowey.mybatisplus.ext.processor.constant.QueryConstants;
import com.photowey.mybatisplus.ext.processor.expression.CriteriaAnnotationProcessor;
import com.photowey.mybatisplus.ext.processor.time.ITimeProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code ScanUtils}
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public final class ScanUtils {

    private ScanUtils() {
        // utils class; can't create
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }

    /**
     * 执行包扫描
     *
     * @param basePackages 扫描的基础包列表
     * @return {@link Map<Class<? extends Annotation>, CriteriaAnnotationProcessor}
     * @throws IOException
     */
    public static Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> doScan(String... basePackages) throws IOException {
        Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> ANNOTATION_PROCESSOR_CACHE = new HashMap<>(32);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        for (String basePackage : basePackages) {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + '/' + QueryConstants.DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    if (classMetadata.isInterface() || classMetadata.isAbstract()) {
                        continue;
                    }
                    String className = classMetadata.getClassName();
                    Class<?> clazz = ClassUtils.forName(className, ScanUtils.class.getClassLoader());
                    if (clazz.isAnnotationPresent(ConditionProcessor.class)) {
                        ConditionProcessor annotation = clazz.getAnnotation(ConditionProcessor.class);
                        CriteriaAnnotationProcessor processor = (CriteriaAnnotationProcessor) clazz.getDeclaredConstructor().newInstance();
                        Class<? extends Annotation> condition = annotation.targetAnnotation();
                        ANNOTATION_PROCESSOR_CACHE.put(condition, processor);
                    }
                } catch (Exception e) {
                    // Ignore
                }
            }
        }

        return ANNOTATION_PROCESSOR_CACHE;
    }

    /**
     * 时间处理器扫描
     *
     * @param basePackages 扫描的基础包列表
     * @return {@link Map<Class<?>, TimeProcessor<?>>}
     * @throws IOException
     */
    public static Map<Class<?>, ITimeProcessor<?>> doTimeScan(String... basePackages) throws IOException {
        Map<Class<?>, ITimeProcessor<?>> TIME_PROCESSOR_CACHE = new HashMap<>(16);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        for (String basePackage : basePackages) {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + '/' + QueryConstants.DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    if (classMetadata.isInterface() || classMetadata.isAbstract()) {
                        continue;
                    }
                    String className = classMetadata.getClassName();
                    Class<?> clazz = ClassUtils.forName(className, ScanUtils.class.getClassLoader());
                    if (ITimeProcessor.class.isAssignableFrom(clazz)) {
                        ITimeProcessor processor = (ITimeProcessor) clazz.getDeclaredConstructor().newInstance();
                        TIME_PROCESSOR_CACHE.put(clazz, processor);
                    }
                } catch (Exception e) {
                    // Ignore
                }
            }
        }

        return TIME_PROCESSOR_CACHE;
    }

    public static String convertClassNameToResourcePath(String className) {
        Assert.notNull(className, "Class name must not be null");
        return className.replace(QueryConstants.PACKAGE_SEPARATOR, QueryConstants.PATH_SEPARATOR);
    }

    public static String resolveBasePackage(String basePackage) {
        return convertClassNameToResourcePath(basePackage);
    }
}
