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
package com.photowey.mybatisplus.ext.meta.selector;

import com.photowey.mybatisplus.ext.meta.exception.MultiBeanException;
import com.photowey.mybatisplus.ext.meta.filler.DefaultMetaPropertiesFiller;
import com.photowey.mybatisplus.ext.meta.filler.MetaPropertiesFiller;
import com.photowey.mybatisplus.ext.validator.ValueValidator;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * {@code MetaPropertiesFillerSelector}
 * <p>
 * {@link MetaPropertiesFiller} 选择器
 * 为什么不用 {@link ImportSelector} 而是子类 {@link DeferredImportSelector}
 * 1.提供另外一种实现 {@link ImportSelector} 的方式
 * 2.方便后续分组扩展
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
public class MetaPropertiesFillerSelector implements /*ImportSelector*/ DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 将 {@link MetaPropertiesFiller} 实现集-注入到 Spring 容器
        List<String> metaPropertiesFillers = SpringFactoriesLoader.loadFactoryNames(MetaPropertiesFiller.class, ClassUtils.getDefaultClassLoader());
        if (ValueValidator.isNullOrEmpty(metaPropertiesFillers)) {
            metaPropertiesFillers.add(DefaultMetaPropertiesFiller.class.getName());
        }
        if (metaPropertiesFillers.size() > 1) {
            throw new MultiBeanException("MetaPropertiesFiller subclass must have exactly one");
        }


        return StringUtils.toStringArray(metaPropertiesFillers);
    }

    @Override
    public Class<? extends Group> getImportGroup() {
        return FillerDeferredImportSelectorGroup.class;
    }

    private static class FillerDeferredImportSelectorGroup implements DeferredImportSelector.Group {

        private List<Entry> candidates = new ArrayList<>();

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            String[] selectImports = selector.selectImports(metadata);
            Stream.of(selectImports).forEach((candidateClassName) -> {
                this.candidates.add(new Entry(metadata, candidateClassName));
            });
        }

        @Override
        public Iterable<Entry> selectImports() {
            return this.candidates;
        }
    }
}
