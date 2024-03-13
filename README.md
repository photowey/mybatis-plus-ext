# `Mybatis-Plus` 扩展项目 `Mybatis-Plus-Ext`

> `Mybatis-Plus` 扩展, 目标是实现更大程度的简化开发
>
> > 文档暂未补全,详见示例工程 [mybatis-plus-ext-examples](https://github.com/photowey/mybatis-plus-ext-examples)
> >
> > - 目前: 已同步版本 `v1.5.0`



> 扩展项:

## `v1.0.0`

### 1.[`QueryWrapper` 扩展 ](./doc/query-wrapper-ext.md)

### 2.[`LambdaQueryWrapper` 扩展 ](./doc/lambda-query-wrapper-ext.md)

### 3.[`BaseMapper` 扩展 ](./doc/base-mapper.md)

### 4.[条件注解 ](./doc/condition-annotation.md)

- 新增 `@Select` 注解



## `v1.1.0`

### 1.新增统一响应数据结构

- `ApiResult`
- `PageResult`
- `EntityFactory` 抽象
- `EntityMapper `抽象

### 2.新增查询 `Selector` 抽象

> 支持链式操纵

- `QueryOrderSelector`
- `QueryPageSelector`
- `QuerySelector`

### 3.新增 `LambdaUpdateWrapperExt`

- 用于实现对 `qw.set(...)` 的扩展

- 实现 `thiz` 方法用于获取 `Wrapper` 自身对象, 以实现模板化代码

  - 通过回调函数实现模板代码块, 给开发者更高的自由度

  - ```java
    new LambdaUpdateWrapperExt<Hello>().thiz((qw)->{
    	// 模板代码
    }).eq(Hello::getId(), 1L);
    
    // ------------------------------------------------
    
    new LambdaUpdateWrapperExt<Hello>().thiz((qw)->{
     	qw.set(Hello::getName(), "hello");
    }).eq(Hello::getId(), 1L);
    ```

### 4.新增 `DynamicSelect` 注解

- `DynamicSelect` 允许开发者通过查询对象 实现动态 `select` 字段

- 注意

  - 在使用的过程中一点要注意 `SQL` 安全

- 推荐

  - 在内部调用的时候使用，能非常明确 `select` 的字段是安全的情况下使用

  - ```java
    // 实例
    public class Hello {
        // 通常该字段可以对前端隐藏
        // 由开发者在安全的情况下填充
        @DynamicSelect
        private Set<String> fields = new HashSet<>();
    }
    ```

  -

- 不推荐

  - 该字段接受前端请求

### 5.更新

> 新增 `thiz` 方法
>
> 在原有 `xxxIfPresent` 方法的基础上，重写了 `xxx` 原方法
>
> -> 其目的是为了统一获取 `this` 实例对象(XxxWrapperExt)
>
> -> 不重写, 返回的是 `XxxWrapper` 实例对象

- `LambdaQueryWrapperExt`
- `QueryWrapperExt`

### 6.更新

> 更新 `mybatis-plus` 版本至 `3.5.2`



## `v1.2.0`

### 1.更新 `Spring Boot` 版本

> `v2.7.5`

### 2.升级 `SPI`

> `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

## v1.3.0`

> 在版本对字段的填充(`mybatis-plus-ext-meta` 模块)有一个破坏性修改
>
> 建议: 该版本新项目可升级

### 1.重新定义 `Entity`

- `AbstractEntity`
  - 抽象实体
- `CreatorEntity`
  - 支持 `create_by` 和 `update_by` 实体
  - 继承 `StandardEntity`
- `RootEntity`
  - 根接口
- `SpecialEntity`
  - 需要对时间字段做特殊处理(添加别名)
    - `gmt_create`
    - `gmt_modified`
- `StandardEntity`
  - 未对时间字段做特殊处理(添加别名)
    - `create_time`
    - `update_time`

### 2.重新设计 `OperatorHandler`

- `Operator tryAcquireOperator()`
  - `com.photowey.mybatisplus.ext.core.domain.operator.Operator`

### 3.`MetaPropertiesFiller` 实例 适配新的 `Entity` 设计

- `CreatorMetaPropertiesFiller`
  - 支持 `CreatorEntity`
- `SpecialMetaPropertiesFiller`
  - 支持 `SpecialEntity`
- `DefaultMetaPropertiesFiller`
  - 支持 `StandardEntity`

### 4.`MetaPropertiesFiller` 注入

- 改 `ApplicationContext` 为 `BeanFactory`

### 5.`OperatorHandler` 加载策略修改

- `Map<String, OperatorHandler> beans = this.beanFactory.getBeansOfType(OperatorHandler.class)`
- 这样支持: `IOC` 容器中可能存在多个 `OperatorHandler` 实例场景

## v1.4.0`

- 新增: `mybatis-plus-ext-service`
- 新增: `AbstractPayload` 抽象
- 增强: 分页模型

## `v1.5.0`

1. 重载 `orderByAsc` `orderByDesc`
    1. 为了能够获取 `LambdaQueryWrapperExt` 和 `QueryWrapperExt`
2. 新增 `limitOne` 和 `limit` 方法
    1. 扩展 `.last("${sql}")`