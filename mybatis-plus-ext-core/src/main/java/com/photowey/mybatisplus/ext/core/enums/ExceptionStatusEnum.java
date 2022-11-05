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
package com.photowey.mybatisplus.ext.core.enums;

/**
 * {@code ExceptionStatusEnum}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
public enum ExceptionStatusEnum {

    /**
     * 成功
     */
    OK("platform.ok", 200, "000000", "请求成功"),
    /**
     * 500xxx 系统错误
     */
    INNER_ERROR("platform.system.inner.error", 500, "500000", "系统未能正确处理请求,请稍后重试~"),
    ;

    private final String name;
    private final int status;
    private final String code;
    private final String message;

    ExceptionStatusEnum(String name, int status, String code, String message) {
        this.name = name;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ExceptionStatusEnum resolve(int status) {
        for (ExceptionStatusEnum anEnum : values()) {
            if (status == anEnum.status()) {
                return anEnum;
            }
        }

        return INNER_ERROR;
    }

    public static ExceptionStatusEnum resolve(String code) {
        for (ExceptionStatusEnum anEnum : values()) {
            if (anEnum.code().equalsIgnoreCase(code)) {
                return anEnum;
            }
        }

        return INNER_ERROR;
    }

    public String toName() {
        return name;
    }


    public int status() {
        return status;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
