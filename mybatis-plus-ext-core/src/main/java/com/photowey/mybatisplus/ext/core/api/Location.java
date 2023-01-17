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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * {@code Location}
 *
 * @author photowey
 * @date 2022/11/05
 * @since 1.1.0
 */
@Data
public class Location implements Serializable {

    private static final long serialVersionUID = -8598860585386697586L;

    @ApiModelProperty(value = "经度", required = false, example = "101.111111")
    private BigDecimal lng;
    @ApiModelProperty(value = "纬度", required = false, example = "34.222222")
    private BigDecimal lat;
}
