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
package com.photowey.mybatisplus.ext.processor.model.payload;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * {@code AbstractPayload}
 *
 * @author photowey
 * @date 2023/06/08
 * @since 1.0.0
 */
public abstract class AbstractPayload<T> implements Serializable {

    public void compositeAction() {
        this.preAction();
        this.validateAction();
    }

    public <C extends AbstractPayload<T>> void compositeAction(Consumer<C> fx) {
        fx.accept((C) this);
        this.preAction();
        this.validateAction();
    }

    public void preAction() {

    }

    public <C extends AbstractPayload<T>> void preAction(C ctx, Consumer<C> fx) {
        fx.accept(ctx);
    }

    public void postAction() {

    }

    public <C extends AbstractPayload<T>> void postAction(C ctx, Consumer<C> fx) {
        fx.accept(ctx);
    }

    public void validateAction() {

    }

    public <C extends AbstractPayload<T>> void validateAction(C ctx, Consumer<C> fx) {
        fx.accept(ctx);
    }

}
