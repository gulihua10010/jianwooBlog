# 简窝博客

<p align=center>
  <a href="http://jianwoo.cn">
    <img src="./src/main/resources/static/comm/img/favicon.ico" alt="简窝博客" style="width:100px;height:100px">
  </a>
</p>
<p align=center>
   简窝博客，一个基于SpringBoot+Vue的前后端分离博客系统
</p>
<p align="center">
<a target="_blank" href="https://github.com/gulihua10010/jianwooBlog">
    	<img src="https://img.shields.io/hexpm/l/plug.svg" ></img>
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
        <img src="https://img.shields.io/badge/nodejs-14.x-green" ></img>
        <img src="https://img.shields.io/badge/springboot-2.4.1.RELEASE-green" ></img>
        <img src="https://img.shields.io/badge/alibaba--druid-1.1.9-green" ></img>
        <img src="https://img.shields.io/badge/mybatis--plus-2.1.1-green" ></img>
        <img src="https://img.shields.io/badge/springboot--security-2.4.1-green" ></img>
        <img src="https://img.shields.io/badge/vue-3.2.13-green" ></img>
<img src="https://img.shields.io/badge/element--plus-1.0.2--beta.28-green" ></img>
    <img src="https://img.shields.io/badge/layui-2.6.8-green" ></img>
    <img src="https://img.shields.io/badge/tinymce-5.6.2-green" ></img>
        <img src="https://img.shields.io/badge/element--plus-1.0.2--beta.28-green" ></img>

##  前言

本项目是由原来的ThinkPHP项目(https://gitee.com/gulihua/jianwoo)重构,由于本人的主要的编程语言是JAVA, 所以打算不维护之前老的php项目(原项目说明:https://jianwoo.cn/#/detail?id=2), 再加上之前的php构建的网站经常有不明黑客攻击,导致服务器和域名被腾讯云封禁好几回,之后直接挂了一个静态的网站(http://old.jianwoo.cn/)在上面。



<img src="https://cdn.jianwoo.cn/jwblog/upload/202208/26c66bb089a4438fa629876f78c42db7_20220821001627174.png" alt="腾讯云封禁邮件" style="width:200">  </a>



## 项目介绍

简窝博客( **JianwooBlog** )，一个**基于Spingboot的前后端分离博客系统**。**Web** 端使用 **Vue** + **ElementUi** , 移动端使用 **uniapp**(还在开发)。后端使用 **SpringBoot** + **Mybatis-plus**进行开发，使用 **Jwt** + **SpringSecurity** 做登录验证和权限校验，使用  **Solr** (准备集成)作为全文检索服务，使用 **Github Actions**完成博客的持续集成，使用 **logback+janino** 收集博客日志，文件支持**上传本地**、**七牛云** .

- 简窝博客大部分功能是我个人进行开发的，因能力有限，其中很多技术都是一边学习一边使用的，可以说简窝博客也是一个我用来熟悉技术的项目，所以很多地方可能考虑不周，故有能改正的地方，还请各位老哥能够指出~
- 现在挺多是SSM或者SSH的博客管理系统，很多博客前后端都是集成在一个系统, 所以我想用 **SpringBoot**  + **Vue** 的前后端分离进行尝试项目的构建，里面很多功能可能只是**为了满足自己的学习需求**而引入的, 因此本博客也是一个非常好的 **SpringBoot**以及 **Vue** 技术的入门学习项目。
- 原来做过 **Vue** + **ElementUi** 做过管理系统，所以现在打算做一套自己的、基于当前最新技术栈的博客系统。
- [简窝博客](https://jianwoo.cn)中的一些文章，很多都来自我平时的学习笔记，还有就是工作中遇到的一写问题和bug,里面涵盖了 **Java**，**Spring**，**SpringBoot** ,**JavaScript**, **Vue**、**Linux** ,**Mysql**等 ，感兴趣的小伙伴可以跳转该仓库 [Star支持](https://github.com/gulihua10010/jianwooBlog) 一下

## 运行配置

最低配置：1核2G 

推荐配置：2核4G 【[狂欢特惠](https://curl.qcloud.com/TYzPgyNC)】【博主目前配置】

最近，腾讯云和阿里云的优惠力度非常大，如果有需求的小伙伴，可以了解一下~

> 【阿里云】云服务器狂欢特惠，**2核2G5M** 轻量级应用服务器 **60 元/年** [点我传送](https://www.aliyun.com/minisite/goods?taskPkg=1111ydsrwb&pkgSid=617&recordId=953032&userCode=w7aungxw)
>
> 【腾讯云】云产品限时秒杀，爆款 **2核4G8M** 云服务器，首年 **74元/年、222/3年**【**博主强烈推荐**】[点我传送](https://curl.qcloud.com/TYzPgyNC)

- 

## 项目特点

- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过 **Json** 进行数据交互，前端再也不用关注后端技术
- 页面交互使用 **Vue3.x**，极大的提高了开发效率。
- 引入七牛云对象存储，同时支持本地文件存储
- 采用**自定义参数校验注解**，轻松实现后端参数校验
- 采用 **AOP** + 自定义注解 + **Redis** 实现限制IP接口访问次数
- 采用自研的评论模块，实现评论邮件通知
- 支持文本编辑器的多种功能, 包括一键排版, Word导入, 文件上传等

## 项目地址

目前项目托管在 **Gitee** 和 **Github** 平台上中，欢迎大家 **Star** 和 **Fork** 支持~

- Gitee地址：https://gitee.com/gulihua/jianwooBlog
- Github地址：https://github.com/gulihua10010/jianwooBlog

### 后端技术

|      技术      |          说明           |                             官网                             |
| :------------: | :---------------------: | :----------------------------------------------------------: |
|   SpringBoot   |         MVC框架         | [ https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| SpringSecurity |     认证和授权框架      |          https://spring.io/projects/spring-security          |
|  MyBatis-Plus  |         ORM框架         |                   https://mp.baomidou.com/                   |
|      Solr      |        搜索引擎         |                http://lucene.apache.org/solr/                |
|     Redis      |       分布式缓存        |                      https://redis.io/                       |
|     Docker     |       容器化部署        |      [ https://www.docker.com](https://www.docker.com/)      |
|     Druid      |      数据库连接池       | [ https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
|     七牛云     |    七牛云 - 对象储存    |         https://developer.qiniu.com/sdk#official-sdk         |
|      JWT       |       JWT登录支持       |                 https://github.com/jwtk/jjwt                 |
|    logBack     |        日志框架         |                   https://logback.qos.ch/                    |
|     Lombok     |    简化对象封装工具     | [ https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |
|     Nginx      | HTTP和反向代理web服务器 |                      http://nginx.org/                       |
|     Hutool     |     Java工具包类库      |                  https://hutool.cn/docs/#/                   |
|    阿里大于    |      短信发送平台       |            https://doc.alidayu.com/doc2/index.htm            |
|   Ip2region    |    离线IP地址定位库     |          https://github.com/lionsoul2014/ip2region           |
| Docker Compose |     Docker容器编排      |               https://docs.docker.com/compose/               |
|   Portainer    |    Docker可视化管理     |            https://github.com/portainer/portainer            |
|  UidGenerator  |   百度ID雪花生成框架    |           https://github.com/baidu/uid-generator/            |
|   pagehelper   |     Mybatis分页插件     |                https://pagehelper.github.io/                 |

### 前端技术

|                  说明                   |                             官网                             |
| :-------------------------------------: | :----------------------------------------------------------: |
|                前端框架                 |                      https://vuejs.org/                      |
|                路由框架                 |                  https://router.vuejs.org/                   |
|            全局状态管理框架             |                   https://vuex.vuejs.org/                    |
|        创建服务端渲染 (SSR) 应用        |                    https://zh.nuxtjs.org/                    |
|               前端ui框架                |    [ https://element.eleme.io](https://element.eleme.io/)    |
|              前端HTTP框架               | [ https://github.com/axios/axios](https://github.com/axios/axios) |
|              富文本编辑器               |                    http://tinymce.ax-z.cn                    |
|            代码语法高亮插件             |       [https://*prism*js.com/](https://*prism*js.com/)       |
|             Markdown编辑器              |                    http://tinymce.ax-z.cn                    |
|              图片裁剪组件               |           https://github.com/xyxiao001/vue-cropper           |
|           vue图片剪裁上传组件           |      https://github.com/dai-siki/vue-image-crop-upload       |
|          Vue Emoji表情评论组件          |       https://github.com/pppercyWang/vue-emoji-comment       |
|            现代化的拷贝文字             |                  http://www.clipboardjs.cn/                  |
|           美化JavaScript代码            |         https://github.com/beautify-web/js-beautify          |
|            保存文件在客户端             |           https://github.com/eligrey/FileSaver.js            |
|       功能强大的JavaScript 拖拽库       |                  http://www.sortablejs.com/                  |
|               目录导航栏                |        https://github.com/yaowei9363/vue-side-catalog        |
| 用Javascript编写的Markdown 到Html转换器 |            https://github.com/showdownjs/showdown            |
| 用JavaScript编写的HTML到Markdown转换器  |           https://github.com/domchristie/turndown            |
|        geetest前端登录图形验证码        |                   https://www.geetest.com/                   |

## 快速开始

### 【推荐】一键部署博客系统

```bash
nohup java -jar /www/application/blog-1.0.0-SNAPSHOT.jar --spring.profiles.active=prod -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 > /dev/null 2>&1
```

## 环境搭建

### 开发工具

|      工具       |       说明        |                             官网                             |
| :-------------: | :---------------: | :----------------------------------------------------------: |
|      IDEA       |    Java开发IDE    |           https://www.jetbrains.com/idea/download            |
|    WebStorm     |    前端开发IDE    |             https://www.jetbrains.com/webstorm/              |
|  RedisDesktop   |  Redis可视化工具  | [ https://redisdesktop.com/download](https://redisdesktop.com/download) |
|   SwitchHosts   |   本地Host管理    |             https://oldj.github.io/SwitchHosts/              |
|     Termius     | Linux远程连接工具 |                   https://www.termius.com/                   |
|    Transmit     | Linux文件传输工具 |               https://www.panic.com/transmit/                |
| Navicat Premium |  数据库连接工具   |               https://sqlyog.en.softonic.com/                |
|   ScreenToGif   |    Gif录制工具    | [ https://www.screentogif.com/](https://www.screentogif.com/) |

### 开发环境

| 工具  | 版本号 |                             下载                             |
| :---: | :----: | :----------------------------------------------------------: |
|  JDK  |  1.8   | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Maven | 3.3.0+ |                   http://maven.apache.org/                   |
| Solr  |  7.0   |                http://lucene.apache.org/solr/                |
| MySQL |  8.0   |                    https://www.mysql.com/                    |
| Nginx |  1.10  |              http://nginx.org/en/download.html               |
| Redis | 3.3.0  |                  https://redis.io/download                   |



### 部署平台

|   平台   |           说明           |               官网               |
| :------: | :----------------------: | :------------------------------: |
| 宝塔面板 | 安全高效的服务器运维面板 | https://www.bt.cn/new/index.html |

## 版本日志
### v1.0.0 
1. 从之前的ThinkPHP 项目重构为 springBoot+Vue前后端分离的 JAVA 项目
2. 优化了后台管理页面的显示，增加了接口安全性
3. 新增了留言板，公告，操作业务日志，用户，邮件，站内信消息通知，网站黑名单等功能
4. 优化了业务逻辑
5. 优化了评论模块
6. 增加了定时任务，自动任务模型
7. 修复了一些已知问题
### v1.1.0
1. 博客支持移动端
2. 优化了前端 vue 页面
3. 修复了一些已知问题
4. 浏览文章支持目录
5. 首页支持音乐播放插件
6. 支持二次元桌面精灵

## 贡献代码

开源项目离不开大家的支持，如果您有好的想法，遇到一些 **BUG** 并修复了，欢迎小伙伴们提交 **Pull Request** 参与开源贡献

1. **fork** 本项目到自己的 **repo**
2. 把 **fork** 过去的项目也就是你仓库中的项目 **clone** 到你的本地
3. 修改代码
4. **commit** 后 **push** 到自己的库
5. 发起**PR**（ **pull request**） 请求，提交到  **Nacos** 分支
6. 等待作者合并

## 开源协议

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.html)

## 赞赏

**服务器**和**域名**等服务的购买和续费都会**产生一定的费用**，为了**维持项目的正常运作**，如果觉得本项目**对您有帮助**的话，欢迎朋友能够**给予一些支持

## 网站截图

![image-20220821010735370](/Users/gulihua/Library/Application Support/typora-user-images/image-20220821010735370.png)



![image-20220821010752465](/Users/gulihua/Library/Application Support/typora-user-images/image-20220821010752465.png)

![image-20220821010951771](/Users/gulihua/Library/Application Support/typora-user-images/image-20220821010951771.png)

![image-20220821010813918](/Users/gulihua/Library/Application Support/typora-user-images/image-20220821010813918.png)

![image-20220821010835448](/Users/gulihua/Library/Application Support/typora-user-images/image-20220821010835448.png)

![image-20220821010854262](/Users/gulihua/Library/Application Support/typora-user-images/image-20220821010854262.png)
