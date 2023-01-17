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
package com.photowey.mybatisplus.ext.core.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * {@code ResultSupport}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
@Data
@NoArgsConstructor
@ApiModel(value = "响应基类", description = "响应基类")
public abstract class ResultSupport<T, R extends ResultSupport<T, R>> implements Serializable {

    private static final long serialVersionUID = -8479667319465108597L;

    public static final String API_OK = "000000";

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码  \n000000 表示成功", example = "401000")
    protected String code;

    /**
     * 返回提示信息
     */
    @ApiModelProperty(value = "返回提示信息", example = "我是相应描述信息")
    protected String message;

    protected ResultSupport(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造响应结果
     *
     * @param code    业务状态吗
     * @param message 响应消息
     * @param data    响应数据
     * @return {@link ResultSupport<T>} 响应结果
     */
    protected abstract ResultSupport<T, R> of(String code, String message, T data);

    /**
     * 构造响应结果
     *
     * @param code    业务状态吗
     * @param message 响应消息
     * @param data    响应数据
     * @param meta    响应元数据
     * @return {@link ResultSupport<T>} 响应结果
     */
    protected abstract ResultSupport<T, R> of(String code, String message, List<T> data, Meta meta);

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public R code(String code) {
        this.code = code;
        return (R) this;
    }

    public R message(String message) {
        this.message = message;
        return (R) this;
    }

    public boolean requestSuccessful() {
        return API_OK.equalsIgnoreCase(this.code);
    }

    public boolean requestFailure() {
        return !this.requestSuccessful();
    }
}