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

import com.photowey.mybatisplus.ext.core.api.factory.EmptyEntityFactory;
import com.photowey.mybatisplus.ext.core.enums.ExceptionStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code ApiResult}
 * 单对象响应结果
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "单对象响应结果", description = "单对象响应结果")
public class ApiResult<T> extends ResultSupportAdapter<T, ApiResult<T>> {

    private static final long serialVersionUID = 6844539603053310468L;

    public ApiResult() {
        this(ExceptionStatusEnum.OK.code(), ExceptionStatusEnum.OK.message());
    }

    /**
     * 创建对象
     *
     * @param <T> 泛型
     * @return ApiResult
     */
    public static <T> ApiResult<T> create() {
        return new ApiResult<>();
    }

    /**
     * 成功
     *
     * @param <T> {@link T} 类型
     * @return {@link ApiResult<T>}
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>(ExceptionStatusEnum.OK.code(), ExceptionStatusEnum.OK.message(), (T) EmptyEntityFactory.empty());
    }

    /**
     * 成功
     *
     * @param data 响应数据
     * @param <T>  {@link T} 类型
     * @return {@link ApiResult<T>}
     */
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = create();
        return apiResult.of(ExceptionStatusEnum.OK.code(), ExceptionStatusEnum.OK.message(), data);
    }

    /**
     * 成功
     *
     * @param data       响应数据
     * @param additional 附加参数列表
     * @param <T>        {@link T} 类型
     * @return {@link ApiResult<T>}
     */
    public static <T> ApiResult<T> success(T data, Map<String, Object> additional) {
        ApiResult<T> apiResult = create();
        return apiResult.of(ExceptionStatusEnum.OK.code(), ExceptionStatusEnum.OK.message(), data, additional);
    }

    /**
     * 失败
     *
     * @param <T> {@link T} 类型
     * @return {@link ApiResult<T>}
     */
    public static <T> ApiResult<T> failure() {
        return new ApiResult<>(ExceptionStatusEnum.INNER_ERROR.code(), ExceptionStatusEnum.INNER_ERROR.message());
    }

    /**
     * 失败
     *
     * @param additional 附加参数列表
     * @param <T>        {@link T} 类型
     * @return {@link ApiResult<T>}
     */
    public static <T> ApiResult<T> failure(Map<String, Object> additional) {
        ApiResult<T> apiResult = create();
        return apiResult.of(ExceptionStatusEnum.INNER_ERROR.code(), ExceptionStatusEnum.INNER_ERROR.message(), (T) EmptyEntityFactory.empty(), additional);
    }

    /**
     * 创建构建器
     *
     * @param <T> 泛型
     * @return ApiResultBuilder
     */
    public static <T> ApiResultBuilder<T> builder() {
        return new ApiResultBuilder<>();
    }

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    @ApiModelProperty(value = "附加参数")
    private Map<String, Object> additional = new HashMap<>();

    public ApiResult(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    protected ApiResult(String code, String message) {
        super(code, message);
    }

    /**
     * 构造响应结果
     *
     * @param code    业务状态吗
     * @param message 响应消息
     * @param data    响应数据
     * @return 响应结果
     */
    @Override
    public ApiResult<T> of(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;

        return this;
    }

    public ApiResult<T> of(String code, String message, T data, Map<String, Object> additional) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.additional = additional;

        return this;
    }

    /**
     * ApiResult 构建器
     *
     * @param <T> 泛型
     */
    @Data
    @NoArgsConstructor
    public static class ApiResultBuilder<T> {
        /**
         * 响应状态码
         */
        @ApiModelProperty(value = "响应状态码")
        protected String code;
        /**
         * 响应信息
         */
        @ApiModelProperty(value = "响应信息")
        protected String message;
        /**
         * 响应数据
         */
        @ApiModelProperty(value = "响应数据")
        protected T data;

        @ApiModelProperty(value = "附加参数")
        protected Map<String, Object> additional;

        public ApiResultBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public ApiResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResultBuilder<T> data(Map<String, Object> additional) {
            this.additional = additional;
            return this;
        }

        public ApiResult<T> build() {
            ApiResult<T> apiResult = ApiResult.create();
            apiResult.of(code, message, data, additional);
            return apiResult;
        }
    }
}