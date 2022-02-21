package com.photowey.mybatisplus.ext.processor.advisor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.photowey.mybatisplus.ext.annotation.*;
import com.photowey.mybatisplus.ext.processor.exception.QueryExtException;
import com.photowey.mybatisplus.ext.processor.expression.*;
import com.photowey.mybatisplus.ext.processor.model.query.AbstractQuery;
import com.photowey.mybatisplus.ext.processor.parser.CriteriaFieldParser;
import com.photowey.mybatisplus.ext.processor.util.ScanUtils;
import com.photowey.mybatisplus.ext.query.QueryWrapperExt;
import com.photowey.mybatisplus.ext.processor.constant.QueryConstants;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code CriteriaAnnotationProcessorAdvisor}
 * 注解处理器增强
 *
 *
 * @author photowey
 * @date 2022/02/17
 * @since 1.0.0
 */
public class CriteriaAnnotationProcessorAdvisor {

    /**
     * 条件注解处理器缓存
     */
    protected static Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> ANNOTATION_PROCESSOR_CACHE = new ConcurrentHashMap<>(32);

    static {
        // 改为: 采用 {@code Spring} {@code BeanPostProcessor} 处理
        // handleProcessorCacheByPackageScan();
    }

    public static Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> getTimeProcessorCache() {
        return ANNOTATION_PROCESSOR_CACHE;
    }

    public static void put(Class<? extends Annotation> targetClazz, CriteriaAnnotationProcessor annotationProcessor) {
        ANNOTATION_PROCESSOR_CACHE.put(targetClazz, annotationProcessor);
    }

    /**
     * 从缓存中查询制定的处理器
     *
     * @param processorClazz 处理器 Class
     * @return 指定的条件注解处理器
     */
    protected static CriteriaAnnotationProcessor findProcessor(final Class<?> processorClazz) {
        final CriteriaAnnotationProcessor processor = ANNOTATION_PROCESSOR_CACHE.get(processorClazz);
        if (null == processor) {
            throw new QueryExtException("No processor found:%s", processorClazz);
        }

        return processor;
    }

    /**
     * 通过条件注解完成自动包装
     *
     * @param query        自定义的查询对象
     * @param queryWrapper 查询包装器
     * @param <QUERY>      自定义的查询类型
     * @param <ENTITY>     实体类型
     * @return {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @see {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    public static <QUERY extends AbstractQuery, ENTITY> QueryWrapper<ENTITY> advise(final QUERY query, QueryWrapper<ENTITY> queryWrapper) {
        CriteriaFieldParser.foreachCriteriaField(query, (field, criteriaAnnotation) -> {
            final CriteriaAnnotationProcessor processorCached = findProcessor(criteriaAnnotation.annotationType());
            assert processorCached != null;
            return processorCached.process(queryWrapper, field, query, criteriaAnnotation);
        });

        return queryWrapper;
    }

    /**
     * 通过条件注解完成自动包装
     *
     * @param query        自定义的查询对象
     * @param queryWrapper 查询包装器
     * @param <QUERY>      自定义的查询类型
     * @param <ENTITY>     实体类型
     * @return {@link QueryWrapperExt}
     * @see {@link QueryWrapperExt}
     */
    public static <QUERY extends AbstractQuery, ENTITY> QueryWrapperExt<ENTITY> adviseExt(final QUERY query, QueryWrapperExt<ENTITY> queryWrapper) {
        CriteriaFieldParser.foreachCriteriaField(query, (field, criteriaAnnotation) -> {
            final CriteriaAnnotationProcessor processorCached = findProcessor(criteriaAnnotation.annotationType());
            assert processorCached != null;
            return processorCached.process(queryWrapper, field, query, criteriaAnnotation);
        });

        return queryWrapper;
    }

    // --------------------------------------------------------------------------------------- HANDLE PROCESSOR

    private static void handleProcessorCacheByPackageScan() {
        // TODO 采用包扫描的方式,替换NEW的方式, 如果失败了,再采用 NEW 的 方式
        try {
            String basePackage = QueryConstants.ANNOTATION_PROCESSOR_SCAN_BASE_PACKAGE;
            ANNOTATION_PROCESSOR_CACHE = ScanUtils.doScan(basePackage);
        } catch (Exception e) {
            handleProcessorCacheByNew();
        }
    }

    private static void handleProcessorCacheByNew() {
        // TODO 根据 实际情况来分配容量
        // TODO 采用包扫描实现
        ANNOTATION_PROCESSOR_CACHE = new HashMap<>(32);

        // EQ
        ANNOTATION_PROCESSOR_CACHE.put(Eq.class, new EqProcessor<>());
        // NE
        ANNOTATION_PROCESSOR_CACHE.put(Ne.class, new NeProcessor());

        // GE
        ANNOTATION_PROCESSOR_CACHE.put(Ge.class, new GeProcessor());
        // GT
        ANNOTATION_PROCESSOR_CACHE.put(Gt.class, new GtProcessor());

        // IN
        ANNOTATION_PROCESSOR_CACHE.put(In.class, new InProcessor());
        // NOT IN
        ANNOTATION_PROCESSOR_CACHE.put(NotIn.class, new NotInProcessor());

        // IS NULL
        ANNOTATION_PROCESSOR_CACHE.put(IsNull.class, new IsNullProcessor());
        // IS NOT NULL
        ANNOTATION_PROCESSOR_CACHE.put(IsNotNull.class, new IsNotNullProcessor());

        // LE
        ANNOTATION_PROCESSOR_CACHE.put(Le.class, new LeProcessor());
        // LT
        ANNOTATION_PROCESSOR_CACHE.put(Lt.class, new LtProcessor());

        // LIKE
        ANNOTATION_PROCESSOR_CACHE.put(Like.class, new LikeProcessor());
        // NOT LIKE
        ANNOTATION_PROCESSOR_CACHE.put(NotLike.class, new NotLikeProcessor());

        // EXISTS
        ANNOTATION_PROCESSOR_CACHE.put(Exists.class, new ExistsProcessor());
        // NOT EXISTS
        ANNOTATION_PROCESSOR_CACHE.put(NotExists.class, new NotExistsProcessor());

        // GROUP BY
        ANNOTATION_PROCESSOR_CACHE.put(GroupBy.class, new GroupByProcessor());
        // HAVING
        ANNOTATION_PROCESSOR_CACHE.put(Having.class, new HavingProcessor());

        // ORDER BY
        ANNOTATION_PROCESSOR_CACHE.put(OrderBy.class, new OrderByProcessor());
        // TIMESTAMP
        ANNOTATION_PROCESSOR_CACHE.put(Timestamp.class, new TimestampProcessor());

        // AND
        ANNOTATION_PROCESSOR_CACHE.put(And.class, new AndProcessor());
        // OR
        ANNOTATION_PROCESSOR_CACHE.put(Or.class, new OrProcessor());
    }

    public static void destroyProcessorCache() {
        if (null != ANNOTATION_PROCESSOR_CACHE && ANNOTATION_PROCESSOR_CACHE.size() > 0) {
            ANNOTATION_PROCESSOR_CACHE.clear();
        }
    }
}
