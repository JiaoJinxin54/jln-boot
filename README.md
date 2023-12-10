# jln-boot

## 一 背景

每次搭建`SpringBoot`项目时都需要创建部分基础代码，由此会造成如下问题：

1. 代码重复

   由于都是基础且公共的代码，内容逻辑大致相同，会造成大量代码重复开发，例如：统一响应格式、异常处理等。

2. 风格不同

   由于不同项目由不同开发人员创建，代码风格及习惯不尽相同，会造成公司内部代码风格不一致，例如：统一响应格式等。

3. 浪费时间

   每次创建完基础代码后，都需要进行调试后才可以正常使用，会造成时间的大量浪费。

## 二 目的

该项目的初衷仅为搭建`SpringBoot`项目提供标准/基础实现，省略部分公共代码的重复编写与验证，加快项目的初始搭建。

## 三 快速开始

示例：引用`jln-core`

```
<dependencies>
    <dependency>
        <groupId>top.jiaojinxin</groupId>
        <artifactId>jln-core</artifactId>
        <version>3.1.6.0</version>
    </dependency>
</dependencies>
```

或

```
<dependencies>
    <dependency>
        <groupId>top.jiaojinxin</groupId>
        <artifactId>jln-core</artifactId>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>top.jiaojinxin</groupId>
            <artifactId>jln-dependencies</artifactId>
            <version>3.1.6.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## 四 模块说明

| 模块                                     | 说明                           |
|:---------------------------------------|:-----------------------------|
| jln-dependencies                       | 物料箱，用于统一管理各模块及依赖版本           |
| jln-core                               | 核心模块，包含多语言码、数据实体的规范及抽象、基础异常等 |
| jln-audit-log                          | 审计日志模块，用于记录审计日志              |
| jln-oss                                | 对象存储模块，用于进行文件存储              |
| jln-sign                               | 签名模块，用于进行签名、验签               |

## 五 版本说明

该项目版本与`SpringBoot`版本对应，且在`SpringBoot`版本号后增加小分支，用于同版本内迭代，例如：

`3.1.6.x`版本的`jln-boot`对应使用`3.1.6`版本的`SpringBoot`；

`2.7.18.x`版本的`jln-boot`对应使用`2.7.18`版本的`SpringBoot`；