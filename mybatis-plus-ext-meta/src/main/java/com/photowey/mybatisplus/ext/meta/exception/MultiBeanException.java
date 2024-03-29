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
package com.photowey.mybatisplus.ext.meta.exception;

/**
 * {@code MultiBeanException}
 * 查找到多个实现时抛出
 *
 * @author photowey
 * @date 2022/02/18
 * @since 1.0.0
 */
public final class MultiBeanException extends RuntimeException {

    public MultiBeanException() {
        super();
    }

    public MultiBeanException(String message) {
        super(message);
    }

    public MultiBeanException(String message, Object... params) {
        super(String.format(message, params));
    }

    public MultiBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultiBeanException(Throwable cause) {
        super(cause);
    }

    public MultiBeanException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
