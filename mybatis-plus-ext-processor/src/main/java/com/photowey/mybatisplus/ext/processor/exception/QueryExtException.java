package com.photowey.mybatisplus.ext.processor.exception;

/**
 * {@code QueryExtException}
 * 查询异常
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public final class QueryExtException extends RuntimeException {

    public QueryExtException() {
        super();
    }

    public QueryExtException(String message) {
        super(message);
    }

    public QueryExtException(String message, Object... params) {
        super(String.format(message, params));
    }

    public QueryExtException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryExtException(Throwable cause) {
        super(cause);
    }

    public QueryExtException(String message, Throwable cause,
                             boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
