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
package com.photowey.mybatisplus.ext.core.domain.operator;

import java.io.Serializable;

/**
 * {@code Operator}
 *
 * @author weichangjun
 * @date 2023/04/26
 * @since 1.3.0
 */
public class Operator implements Serializable {

    private Long operatorId;
    private String operatorName;

    public static OperatorBuilder builder() {
        return new OperatorBuilder();
    }

    public Long getOperatorId() {
        return this.operatorId;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public void setOperatorId(final Long operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperatorName(final String operatorName) {
        this.operatorName = operatorName;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Operator)) {
            return false;
        } else {
            Operator other = (Operator) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$operatorId = this.getOperatorId();
                Object other$operatorId = other.getOperatorId();
                if (this$operatorId == null) {
                    if (other$operatorId != null) {
                        return false;
                    }
                } else if (!this$operatorId.equals(other$operatorId)) {
                    return false;
                }

                Object this$operatorName = this.getOperatorName();
                Object other$operatorName = other.getOperatorName();
                if (this$operatorName == null) {
                    if (other$operatorName != null) {
                        return false;
                    }
                } else if (!this$operatorName.equals(other$operatorName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Operator;
    }

    public int hashCode() {
        int result = 1;
        Object $operatorId = this.getOperatorId();
        result = result * 59 + ($operatorId == null ? 43 : $operatorId.hashCode());
        Object $operatorName = this.getOperatorName();
        result = result * 59 + ($operatorName == null ? 43 : $operatorName.hashCode());
        return result;
    }

    public String toString() {
        return "Operator(operatorId=" + this.getOperatorId() + ", operatorName=" + this.getOperatorName() + ")";
    }

    public Operator() {
    }

    public Operator(final Long operatorId, final String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
    }

    public static class OperatorBuilder {
        private Long operatorId;
        private String operatorName;

        OperatorBuilder() {
        }

        public OperatorBuilder operatorId(final Long operatorId) {
            this.operatorId = operatorId;
            return this;
        }

        public OperatorBuilder operatorName(final String operatorName) {
            this.operatorName = operatorName;
            return this;
        }

        public Operator build() {
            return new Operator(this.operatorId, this.operatorName);
        }

        public String toString() {
            return "Operator.OperatorBuilder(operatorId=" + this.operatorId + ", operatorName=" + this.operatorName + ")";
        }
    }
}
