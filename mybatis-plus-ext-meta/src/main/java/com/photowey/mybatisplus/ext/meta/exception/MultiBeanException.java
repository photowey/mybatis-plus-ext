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
