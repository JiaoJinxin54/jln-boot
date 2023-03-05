# jln-boot

## 一 简介

该项目基于`SpringBoot`3.0.1，`JDK`17进行开发，用于简化`SpringBoot`web项目的创建，包含部分创建项目时均需要的公共代码。

## 二 背景

每次创建`SpringBoot`web项目时都需要创建部分基础代码，由此会造成如下问题：

### 1. 代码重复

由于都是基础且公共的代码，内容逻辑大致相同，会造成大量代码重复开发，例如：统一响应格式、异常处理等。

### 2. 风格不同

由于不同项目由不同开发人员创建，代码风格及习惯不尽相同，会造成公司内部代码风格不一致，例如：统一响应格式等。

### 3. 浪费时间

每次创建完基础代码后，都需要进行调试后才可以正常使用，会造成时间的大量浪费。

## 三 目的

该项目的初衷仅为方便搭建`SpringBoot`web项目，省略部分公共代码的重复编写、验证。

## 四 项目结构

```
jln-boot
├── jln-spring-boot-starter：starter，用于快速使用jln。
├── jln-dependencies：物料箱，用于统一管理各模块版本。
├── jln-event：事件模块，自定义事件及事件处理，用于解耦业务逻辑。
├── jln-exception：异常模块，提供基础异常，用于统一异常处理。
├── jln-log：日志模块，用于记录请求日志与审计日志。
├── jln-model：实体模块，用于提供数据传输实体的规范。
├── jln-mybatis-plus：Mybatis-Plus模块，提供DB实体规范及基础配置，用于快速集成Mybatis-Plus。
├── jln-redis：Redis模块，提供统一的Redis配置，用于快速集成Redis。
├── jln-response-code：响应码模块，提供统一的响应码获取逻辑，用于集中管理多语种的响应码。
├── README.md
└── pom.xml
```

## 五 使用

### 1. 方式一：使用集成包，直接引用`jln-spring-boot-starter`

```
<dependencies>
    <dependency>
        <groupId>top.jiaojinxin</groupId>
        <artifactId>jln-spring-boot-starter</artifactId>
        <version>0.1.5</version>
    </dependency>
</dependencies>
```

### 2. 方式二：按需引用，例如引用`jln-event`

若使用多个依赖包时，建议通过使用`jln-dependencies`来统一管理各依赖包的版本，避免造成版本冲突。

```
<dependencies>
    <dependency>
        <groupId>top.jiaojinxin</groupId>
        <artifactId>jln-event</artifactId>
        <version>0.1.6</version>
    </dependency>
</dependencies>
```

或

```
<dependencies>
    <dependency>
        <groupId>top.jiaojinxin</groupId>
        <artifactId>jln-event</artifactId>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>top.jiaojinxin</groupId>
            <artifactId>jln-dependencies</artifactId>
            <version>0.1.6</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## 六 各模块介绍

### 1. `jln-mode`

#### 1.1. 介绍

实体规范模块，提供了基础的数据传输规范。

#### 1.2. 结构及描述

```
top.jiaojinxin.jln.model
├─DTO：数据传输对象的顶级抽象，所有数据传输对象均需要实现该接口；
├─command
│  └─Command：命令传输对象，应用场景：会改变对象状态、不返回任何结果，多用于增、删、改；
├─query
│  ├─Query：查询传输对象，应用场景：不会改变对象状态、对系统没有副作用、会有返回结果，多用于查询；
│  ├─PageQuery：分页查询传输对象，特定于分页查询场景的查询传输对象；
│  └─PageCondition：分页查询传输对象中的条件实体抽象，用于定义分页查询条件；
└─resp
   ├─Resp：顶级响应对象，所有数据响应对象均需要继承该基础类；
   ├─SingletonResp<?>：单值响应对象，特定于返回单一对象的响应场景；
   ├─MultipartResp<?>：多值响应对象，特定于返回多个对象的响应场景，常用于响应数组、集合；
   └─PageResp<?>：分页响应对象，特定于分页查询场景，返回结果为单一对象，该单一对象中包含分页条件、数据总量、当前页的数据集；
```

#### 1.3. 示例

以下为分页查询场景示例：

```java
/**
 * 分页查询条件
 */
public class DemoPageCondition extends PageCondition {
    /**
     * 名称条件
     */
    private ConditionItem<String> name;
    // get、set...
}
```

```java
/**
 * 分页查询结果体。
 * PageResp.PageResult设计为接口的原因是可以方便扩展额外的自定义响应属性。
 */
public class DemoPageResult implements PageResp.PageResult<Demo> {
    /**
     * 当前页的数据集
     */
    private Demo[] items;
    /**
     * 数据总量
     */
    private Long count;
    /**
     * 当前页码
     */
    private Long pageNum;
    /**
     * 每页查询数量
     */
    private Long pageSize;
    // 额外需要扩展的自定义响应属性...
    // get、set...
}
```

```java

@RestController
public class DemoController {
    @GetMapping("/demo/page")
    public Resp page(PageQuery<DemoPageCondition> pageQuery) {
        DemoPageResult pageResult = new DemoPageResult();
        pageResult.setItems(data);
        pageResult.setCount(totalCount);
        pageResult.setPageNum(pageQuery.getPageNum());
        pageResult.setPageSize(pageQuery.getPageSize());
        return PageResp.ok(pageResult);
    }
}
```

### 2. `jln-response-code`

#### 2.1. 介绍

响应码模块，该模块提供了响应码的标准、响应码的获取抽象。

#### 2.2. 核心接口/类与描述

##### 2.2.1. `RespCode`

定义了响应码的标准与规范。

##### 2.2.2. `RespCodeHolder`

`RespCode`的持有者，定义了`RespCode`的获取，用于自定义`RespCode`的存储，例如基于枚举、缓存、DB库、配置文件等......

`jln-spring-boot-starter`中提供了基于`org.springframework.context.MessageSource`的默认实现，天然支持国际化，只需要添加国际化配置文件即可。

需要注意的是，`jln-spring-boot-starter`
默认提供了中文、英文两种语言的基础响应码配置（例如成功、失败等......），只需要引入依赖即可使用，但若需要自定义国际化配置文件，请将默认配置一并加入自定义配置文件中，否则将导致基础响应码无法获取。

##### 2.2.3. `LocaleHolder`

`Locale`的持有者，定义了`Locale`的获取，用于支持`RespCode`的国际化，默认采用`LocaleContextHolder.getLocale()`。

#### 2.3. 示例

##### 2.3.1. 自定义国际化配置

若使用集成依赖`jln-spring-boot-starter`，可自定义国际化配置文件来存储响应码，配置`spring.messages.basename`
指定国际化文件路径及名称，如下所示：

`application.yml`示例：

```yaml
spring:
  messages:
    basename: messages
```

`application.properties`示例：

```properties
spring.messages.basename=messages
```

在resources文件夹下配置messages.properties以及其他语言文件，文件命名格式如：messages_es_US.properties，如下示例所示：

`messages.properties`文件：

```properties
0000=成功
0001=失败
0002=系统异常
0003=Jln异常
0004=业务异常
# ...其他自定义响应码
```

`messages_es_US.properties`文件：

```properties
0000=Success
0001=Fail
0002=System Exception
0003=Jln Exception
0004=Business Exception
# ...其他自定义响应码
```

##### 2.3.2. 获取响应码

使用工具类`RespCodeUtil#getRespCode`或`JlnUtil#getRespCode`获取响应码。

```java
public class Demo {

    public void demo() {
        // 其他业务逻辑......
        // code为响应码的code值，可通过常量定义管理，args为响应码描述的参数，可支持响应码描述的动态拼接，避免因描述相似而产生大量响应码。
        RespCode respCode = JlnUtil.getRespCode(code, args);
        // 其他业务逻辑......
    }
}
```

### 3. `jln-event`

#### 3.1. 介绍

自定义事件及处理模块，可通过使用事件的方式对代码进行解耦。

#### 3.2. 核心接口/类及描述

##### 3.2.1. `Event`

事件本身，用于记录事件信息；

##### 3.2.2. `EventHandler<E extends Event>`

事件处理器，用于处理事件，每一个事件可以有多个处理器；

##### 3.2.3. `EventHandlerRepository`

事件处理器存储库，用于存储事件与事件处理器的关系；

##### 3.2.4. `DefaultEventHandlerRepository`

默认的`EventHandlerRepository`实现；

##### 3.2.5. `EventPublisher`

事件发布者，用于发布事件；

##### 3.2.6. `AbstractEventPublisher`

基于`Disruptor`实现的抽象`EventPublisher`实现，用于异步发布事件；

##### 3.2.7. `DefaultEventPublisher`

`EventPublisher`的默认实现，继承自`AbstractEventPublisher`，用于异步并行发布事件；

#### 3.3. 相关配置参数介绍

| 配置项                          | 描述            | 类型           | 默认值          |
|:-----------------------------|:--------------|:-------------|:-------------|
| jln.event.ring-buffer-size   | RingBuffer的长度 | int          | 128          |
| jln.event.thread-name-prefix | 线程名称前缀        | string       | event-thread |
| jln.event.producer-type      | 生产者类型         | ProducerType | single       |

`application.yml`示例：

```yml
jln:
  event:
    ring-buffer-size: 128
    thread-name-prefix: event-thread
    producer-type: single
```

`application.properties`示例：

```properties
jln.event.ring-buffer-size=128
jln.event.thread-name-prefix=event-thread
jln.event.producer-type=single
```

#### 3.4. 示例

事件：

```java
public class DemoEvent implements Event {

    @Serial
    private static final long serialVersionUID = -1912973012765556092L;

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

事件处理器：

```java

@Component
public class DemoEventHandler implements EventHandler<DemoEvent> {

    @Override
    public void doHandle(DemoEvent event) {
        // 事件处理逻辑
    }

    @Override
    public Class<DemoEvent> eventClass() {
        return DemoEvent.class;
    }
}
```

发布事件：

```java

@Service
public class DemoService {

    private final EventPublisher eventPublisher;

    public DemoService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void demo() {
        DemoEvent event = new DemoEvent();
        event.setName("事件demo");
        // 方式一：使用EventPublisher bean进行发布
        eventPublisher.publish(event);
        // 方式二：使用静态管理类进行发布
        EventUtil.publish(event);
        JlnUtil.publish(event);
    }
}
```

### 4. `jln-log`

#### 4.1. 介绍

日志模块，用于提供审计日志。

#### 4.2. 核心接口/类及描述

##### 4.2.1. `AuditLog`

审计日志注解，在请求入口方法上使用该注解，可自动记录请求日志；

##### 4.2.2. `AuditLogEvent`

审计日志事件，用于记录被`AuditLog`注解注释的请求的审计日志详情；

##### 4.2.3. `AuditLogEventHandler<? extends AuditLogEvent>`

审计日志事件处理程序，用于输出审计日志详情；

##### 4.2.4. `AuditLogHandler<? extends AuditLogEvent>`

审计日志记录程序，用于在请求前、后记录日志信息，该程序仅记录日志，日志的输出由日志事件处理程序来处理；

##### 4.2.5. `StringFormatter`

文本格式化处理程序，用于日志打印工具方法中模版文本与参数的合并处理，默认实现中不对模版文本做任何处理直接输出。

`jln-spring-boot-starter`中提供了两种实现：

- 基于`java.lang.String#format`的实现，引入集成依赖`jln-spring-boot-starter`后的默认实现；
- 基于`基于cn.hutool.core.text.StrFormatter#formatWith`的实现，引入`hutool`依赖`hutool-core`后的默认实现；

##### 4.2.6. `JsonFormatter`

JSON序列化处理程序，用于日志打印工具方法中实体对象转换为JSON字符串进行输出，默认实现采用`toString()`方法。

`jln-spring-boot-starter`中提供了四种实现（若引入多个则按照优先级顺序，优先级越高及数值越小的生效）：

- 基于`fastjson2`的JSON序列化实现（引入`fastjson2`后生效，优先级：`1`）
- 基于`fastjson`的JSON序列化实现（引入`fastjson`后生效，优先级：`2`）
- 基于`gson`的JSON序列化实现（引入`gson`后生效，优先级：`3`）
- 基于`hutool`的JSON序列化实现（引入`hutool-json`后生效，优先级：`4`）

#### 4.3. 示例

所有请求均会被拦截，添加`@AuditLog`后即可记录审计日志，如下示例所示：

```java

@RestController
public class DemoController {

    @AuditLog(describe = "demo")
    @GetMapping("/demo")
    public Resp demo() {
        return Resp.ok();
    }
}
```

### 5. `jln-exception`

#### 5.1. 介绍

异常模块，该模块提供了基础异常，以及基础异常的统一处理。

#### 5.2. 核心类及描述

- `BaseException`：基础异常，所有自定义异常的基类；
- `SysException`：系统异常；
- `JlnException`：框架内部异常；
- `BizException`：业务异常；
- `JlnExceptionHandlerAutoConfiguration`：统一异常处理，默认只处理`BaseException`与`Throwable`，其他具体类型异常可自行扩展；

#### 5.3. 示例

该模块提供了创建异常、抛出异常、参数校验三大类工具方法，如下所示：

```java
public class DemoService {
    public void demo() {
        /**
         * throwable：为实际异常，使用SysException对其进行包装；
         * code：异常码code；
         * args：异常码提示信息参数，例如code对应的提示信息为"{}已存在"，args可传入"用户"，最终会提示"用户已存在"；
         *
         * obj：待验证的参数实体；
         * groups：参数验证的分组；
         */
        // 创建系统异常
        ExceptionUtil.sys(throwable);
        ExceptionUtil.sys(throwable, code, args);
        JlnUtil.sys(throwable);
        JlnUtil.sys(throwable, code, args);
        // 抛出系统异常
        ExceptionUtil.thrSys(throwable);
        ExceptionUtil.thrSys(throwable, code, args);
        JlnUtil.thrSys(throwable);
        JlnUtil.thrSys(throwable, code, args);
        // 创建框架异常
        ExceptionUtil.jln();
        ExceptionUtil.jln(code, args);
        JlnUtil.jln();
        JlnUtil.jln(code, args);
        // 抛出框架异常
        ExceptionUtil.thrJln();
        ExceptionUtil.thrJln(code, args);
        JlnUtil.thrJln();
        JlnUtil.thrJln(code, args);
        // 创建业务异常
        ExceptionUtil.biz();
        ExceptionUtil.biz(code, args);
        JlnUtil.biz();
        JlnUtil.biz(code, args);
        // 抛出业务异常
        ExceptionUtil.thrBiz();
        ExceptionUtil.thrBiz(code, args);
        JlnUtil.thrBiz();
        JlnUtil.thrBiz(code, args);
        // 参数验证
        ExceptionUtil.validated(obj, code, groups);
        JlnUtil.validated(obj, code, groups);
    }
}
```

### 6. `jln-redis`

#### 6.1. 介绍

`Redis`模块，提供了默认的`RedisTemplate`配置，默认使用`StringRedisTemplate`
，及key与value的序列化方式均采用`RedisSerializer.string()`。

#### 6.2. 相关配置参数介绍

| 配置项                 | 描述       | 类型       | 默认值                   |
|:--------------------|:---------|:---------|:----------------------|
| jln.redis.expire    | 过期时间     | Long     | 30000                 |
| jln.redis.sleep     | 循环等待间隔时间 | Long     | 100                   |
| jln.redis.time-unit | 时间单位     | TimeUnit | TimeUnit.MILLISECONDS |

`application.yml`示例：

```yml
jln:
  redis:
    expire: 30000
    sleep: 100
    time-unit: milliseconds
```

`application.properties`示例：

```properties
jln.redis.expire=30000
jln.redis.sleep=100
jln.redis.time-unit=milliseconds
```

### 7. `jln-mybatis-plus`

#### 7.1. 介绍

`Mybatis-Plus`集成模块，提供了基础配置，定义了基础实体，提供了部分常用方法的封装。

由于数据库多为`Mysql`，故该模块中规定主键使用`int`类型，且使用ID自增的主键策略，可通过额外的字段做数据的唯一标识。

#### 7.2. 核心接口/类及描述

- `BaseEntity`：数据库实体的基类，定义了所有表中均有的基础属性；
- `IBaseDAO<E extends BaseEntity>`：DAO基类；
- `IBaseBO<E extends BaseEntity>`：service基类，定义了部分常用方法；
- `BaseBOImpl<E extends BaseEntity, D extends IBaseDAO<E>>`：service实现类基类，实现了`IBaseBO`中定义的常用方法；
- `MpCurrUser`：当前用户接口，用于获取当前用户的唯一标识及租户表示，用于字段自动填充及SQL条件自动拼接；
- `MpCurrUserHolder`：当前用户持有者，用于定义当前用户的获取；

#### 7.3. 相关配置参数介绍

| 项                                     | 描述              | 类型       | 默认值   |
|:--------------------------------------|:----------------|:---------|:------|
| jln.mybatis-plus.db-type              | 数据库类型           | DbType   | -     |
| jln.mybatis-plus.ignore-tenant-tables | 忽略租户字段条件的表名集合   | string[] | {}    |
| jln.mybatis-plus.max-limit            | 单页分页条数限制        | long     | 100   |
| jln.mybatis-plus.overflow             | 溢出总页数后是否进行处理    | boolean  | true  |
| jln.mybatis-plus.block-attack         | 是否使用防止全表更新与删除插件 | boolean  | true  |
| jln.mybatis-plus.data-permission      | 是否使用数据权限处理插件    | boolean  | false |
| jln.mybatis-plus.dynamic-table-name   | 是否使用动态表名插件      | boolean  | false |
| jln.mybatis-plus.illegal-sql          | 是否使用垃圾SQL拦截插件   | boolean  | false |
| jln.mybatis-plus.replace-placeholder  | 是否使用占位符替换插件     | boolean  | false |
| jln.mybatis-plus.escape-symbol        | 使用占位符替换时的占位符    | string   | -     |

`application.yml`示例：

```yml
jln:
  mybatis-plus:
    db-type: mysql
    ignore-tenant-tables: sys_tenant
    max-limit: 100
    overflow: true
    block-attack: true
    data-permission: false
    dynamic-table-name: false
    illegal-sql: false
    replace-placeholder: false
    escape-symbol: ''
```

`application.properties`示例：

```properties
jln.mybatis-plus.db-type=mysql
jln.mybatis-plus.ignore-tenant-tables=sys_tenant
jln.mybatis-plus.max-limit=100
jln.mybatis-plus.overflow=true
jln.mybatis-plus.block-attack=true
jln.mybatis-plus.data-permission=false
jln.mybatis-plus.dynamic-table-name=false
jln.mybatis-plus.illegal-sql=false
jln.mybatis-plus.replace-placeholder=false
jln.mybatis-plus.escape-symbol=''
```

#### 7.4. 示例

这里不在赘述`Mybatis-Plus`的使用方法，具体可参考[官方网站](https://baomidou.com/)。