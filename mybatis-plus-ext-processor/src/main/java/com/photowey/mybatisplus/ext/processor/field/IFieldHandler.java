package com.photowey.mybatisplus.ext.processor.field;

/**
 * {@code IFieldHandler}
 *
 * @author photowey
 * @date 2022/02/19
 * @since 1.0.0
 */
public interface IFieldHandler {

    /**
     * 判定是不是自己支持的 目标对象
     *
     * @param context {@link FieldContext} 属性上下文
     * @return {@code boolean}
     */
    default boolean supports(FieldContext context) {

        return false;
    }

    /**
     * 处理-目标对象-对应的字段逻辑
     *
     * @param context {@link FieldContext} 属性上下文
     * @return {@code boolean}
     */
    boolean handleField(FieldContext context);
}
