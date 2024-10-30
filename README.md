
<p align="center" style="text-align: center">
	<img alt="logo" src="./design/logo2.png" height="100px" width="220px">
</p>
<h3 align="center" style="margin: 30px 0 30px; font-weight: bold;">Felix-您的数字阅读伴侣</h3>

> **毕设完结，有一些功能没有完成，后序也不会继续完成**
> 
> 前台页面: https://github.com/mazhj180/felix-ui
## 项目简介
Felix，我的毕业设计项目，是一款专注于提供优质阅读体验的图书阅读应用。在这个数字化时代，Felix旨在重新定义移动阅读，将传统的阅读习惯与现代技术完美结合。

---

## 技术选型

<p> Spring Boot - https://spring.io/projects/spring-boot </p>
<p> Spring Cloud - https://spring.io/projects/spring-cloud </p>
<p> Nacos - https://nacos.io/zh-cn/ </p>
<p> Spring Cloud Gateway - https://spring.io/projects/spring-cloud-gateway </p>
<p> MyBatis - https://www.mybatis.org/mybatis-3/zh/index.html </p>
<p> Elasticsearch - https://www.elastic.co/elasticsearch/ </p>
<p> OpenFeign - https://github.com/OpenFeign/feign </p>
<p> Redis - https://redis.io/ </p>
<p> MySQL - https://www.mysql.com/ </p>
<p> Netty - https://netty.io/ </p>
<p> MinIo - https://min.io/</p>
<p> Caffeine - https://github.com/ben-manes/caffeine/wiki/Home-zh-CN </p>
<p> Nginx - https://nginx.org/en/</p>

---

## 项目模块层级图
```text
com.mazhj.felix
├── felix-auth-center       // 认证中心 [10002]
├── felix-gateway           // 网关模块 [8080]
├── felix-feign             // 服务调用客户端接口
│       └── felix-feign-user-client                   // user服务
│       └── felix-feign-book-client                   // book服务
│       └── felix-feign-search-client                 // search服务
├── felix-common            // 通用模块
│       └── felix-common-auth                         // 请求鉴权模块
│       └── felix-common-core                         // 核心模块
│       └── felix-common-minio                        // 文件管理模块
│       └── felix-common-pojo                         // 数据传输实体模块
│       └── felix-common-quartz                       // 定时任务模块
│       └── felix-common-redis                        // 缓存服务模块
│       └── felix-common-web                          // web服务模块
├── felix-system-service    // 系统业务模块
│       └── felix-user                                // 用户服务 [10003]
│       └── felix-book                                // 图书服务 [10004]
│       └── felix-control                             // 管理服务 [10005]
│       └── felix-search                              // 搜索服务 [10006]
│       └── felix-forum                               // 论坛服务 [10007、10008]
├── pom.xml                 // 公共依赖
```

---

## 系统模块
| No | 工程模块                 | 说明                   | 子模块                                                           |
|----|----------------------|----------------------|---------------------------------------------------------------|
| 1  | felix-common         | 通用模块，定义DTO、工具类、配置类等。 | auth、core、minio、pojo、quartz、redis、web                         |
| 2  | felix-auth-center    | 认证中心                 |                                                               |
| 3  | felix-gateway        | 服务网关、权限验证            |                                                               |
| 4  | felix-system-service | 系统服务模块，包含体业务模块       | felix-book、felix-user、felix-control、felix-search、felix-search |
| 5  | felix-feign          | Feign客户端，提供微服务的公用客户端 | book-client、user-client、search-client                         |

## 系统架构图
<img src="design/structure.png" height="600" width="900" alt="架构图">

---
