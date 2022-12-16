-- 这是表的创建脚本，部署网站需要执行!!!!!!
/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : jianwoo_test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 22/08/2022 09:19:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ACCESS_IP_CTRL
-- ----------------------------
DROP TABLE IF EXISTS `ACCESS_IP_CTRL`;
CREATE TABLE `ACCESS_IP_CTRL` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `ACCESS_IP` varchar(20) NOT NULL COMMENT '访问ip',
  `INTERFACE_URL` varchar(50) NOT NULL COMMENT '接口URL',
  `ACCESS_TIME` datetime NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=34359 DEFAULT CHARSET=utf8 COMMENT='网站访问IP控制表';

-- ----------------------------
-- Table structure for ADMIN
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN`;
CREATE TABLE `ADMIN` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(64) NOT NULL COMMENT '密码',
  `USER_NICK` varchar(50) DEFAULT NULL,
  `USER_PHONE` varchar(20) DEFAULT NULL COMMENT '手机号',
  `USER_EMAIL` varchar(30) DEFAULT NULL COMMENT '电子邮件',
  `USER_SEX` varchar(10) DEFAULT NULL COMMENT '性别[10:男, 20:女]',
  `REGISTER_IP` varchar(20) DEFAULT NULL COMMENT '注册ip地址',
  `REGISTER_REGION` varchar(30) DEFAULT NULL COMMENT '注册地区',
  `AVATAR_SRC` varchar(200) DEFAULT NULL COMMENT '头像',
  `LAST_LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '最后登录ip',
  `LAST_LOGIN_REGION` varchar(30) DEFAULT NULL COMMENT '最后登录地区',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Table structure for ANNOUNCEMENT_MSG
-- ----------------------------
DROP TABLE IF EXISTS `ANNOUNCEMENT_MSG`;
CREATE TABLE `ANNOUNCEMENT_MSG` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `TITLE` varchar(100) NOT NULL COMMENT '标题',
  `CONTENT` text NOT NULL COMMENT '内容',
  `PUSH_TIME` datetime NOT NULL COMMENT '发布时间',
  `PUSH_BY` varchar(50) NOT NULL COMMENT '发布人',
  `EXPIATION_TIME` datetime NOT NULL COMMENT '过期时间',
  `STATUS` varchar(2) NOT NULL DEFAULT '00' COMMENT '00:有效,91:无效',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Table structure for ARTICLE
-- ----------------------------
DROP TABLE IF EXISTS `ARTICLE`;
CREATE TABLE `ARTICLE` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `AUTHOR` varchar(10) NOT NULL COMMENT '文章作者',
  `PUSH_TIME` datetime NOT NULL COMMENT '发布时间',
  `PUSH_BY` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '发布的用户',
  `PUSH_IP` varchar(20) NOT NULL COMMENT '文章发布的IP地址',
  `PUSH_REGION` varchar(30) DEFAULT NULL COMMENT '文章发布的地区',
  `TITLE` varchar(100) NOT NULL COMMENT '文章标题',
  `CONTENT` longtext NOT NULL COMMENT '文章内容(html格式)',
  `MODIFIED_TIME` datetime NOT NULL COMMENT '修改时间',
  `CATEGORY_ID` int DEFAULT NULL COMMENT '文章类型',
  `CATEGORY_NAME` varchar(20) DEFAULT NULL COMMENT '文章类别名称',
  `READ_COUNT` bigint NOT NULL COMMENT '阅读次数',
  `PRAISE_COUNT` bigint NOT NULL COMMENT '赞数量',
  `IS_COMMENT` char(1) NOT NULL DEFAULT '1' COMMENT '是否可以被评论',
  `FLAG_ORIGINAL` varchar(1) DEFAULT '1' COMMENT '是否原创',
  `ORIGINAL_URL` varchar(100) DEFAULT NULL COMMENT '转载源链接',
  `IMG_SRC` varchar(1024) DEFAULT NULL COMMENT '文章缩略图',
  `TEXT` longtext NOT NULL COMMENT '文章(纯文本)',
  `ACCESS_TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '20' COMMENT '公开度[私密:10, 密码:11, 公开:20]',
  `TOP_PLACE_STATUS` varchar(2) DEFAULT '00' COMMENT '置顶状态,00:未置顶,50:置顶',
  `COMMENT_COUNT` bigint DEFAULT NULL COMMENT '评论次数',
  `TOTAL_COMMENT_FLOORS` bigint DEFAULT '0' COMMENT '评论总楼数',
  `STATUS` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00' COMMENT '文章状态 [已发布:90, 草稿:00, 回收站:91,  文章删除状态:99]',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '当公开度为-1时设置',
  `DEL_TIME` datetime DEFAULT NULL COMMENT '删除日期 当状态为99时设置',
  `REMOVE_RECYCLE_TIME` datetime DEFAULT NULL COMMENT '移动到回收站的时间 当状态为91时设置',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=2859053145067872257 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for ARTICLE_ACCESS
-- ----------------------------
DROP TABLE IF EXISTS `ARTICLE_ACCESS`;
CREATE TABLE `ARTICLE_ACCESS` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `ACCESS_IP` varchar(20) DEFAULT NULL COMMENT '访问ip',
  `ACCESS_REGION` varchar(30) DEFAULT NULL COMMENT '访问地区',
  `ARTICLE_OID` bigint NOT NULL COMMENT '文章id',
  `ARTICLE_TITLE` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `ACCESS_TIME` datetime NOT NULL COMMENT '访问时间',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=2264 DEFAULT CHARSET=utf8 COMMENT='访问表';

-- ----------------------------
-- Table structure for ARTICLE_TAGS
-- ----------------------------
DROP TABLE IF EXISTS `ARTICLE_TAGS`;
CREATE TABLE `ARTICLE_TAGS` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `TAGS_OID` int NOT NULL COMMENT '标签id',
  `ARTICLE_OID` bigint NOT NULL COMMENT '文章id',
  `ART_FLAG_ACTIVITY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '文章是否是已经发布的活动文章(ART.STATUS=90)',
  `ART_FLAG_PRIVATE` varchar(1) DEFAULT '0' COMMENT '文章是否是私密的文章(ACCESS_TYPE=10)',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8 COMMENT='文章对应标签表';

-- ----------------------------
-- Table structure for ASYNC_PROC_AUTO_TASK
-- ----------------------------
DROP TABLE IF EXISTS `ASYNC_PROC_AUTO_TASK`;
CREATE TABLE `ASYNC_PROC_AUTO_TASK` (
  `TASK_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `TASK_TYPE` varchar(6) NOT NULL,
  `TASK_DATA` varchar(2000) NOT NULL,
  `TIMES_MAX_RETRY` int unsigned NOT NULL DEFAULT '0',
  `STATUS_PROC` varchar(2) NOT NULL COMMENT '00:未处理,90:成功,91:失败',
  `STATUS_PROC_DESC` varchar(500) DEFAULT NULL,
  `PROC_START_TIME` datetime DEFAULT NULL,
  `PROC_END_TIME` datetime DEFAULT NULL,
  `TIMES_PROC_FAILED` int unsigned DEFAULT '0',
  `FAILED_REASON_CODE` varchar(10) DEFAULT NULL,
  `FAILED_REASON_DESC` varchar(500) DEFAULT NULL,
  `CREATED_TIME` datetime NOT NULL,
  `LAST_UPD_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`),
  KEY `IDX_BPAT_2` (`TASK_TYPE`) USING BTREE,
  KEY `IDX_BPAT_3` (`STATUS_PROC`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2606 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ASYNC_PROC_TASK_TYPE_CFG
-- ----------------------------
DROP TABLE IF EXISTS `ASYNC_PROC_TASK_TYPE_CFG`;
CREATE TABLE `ASYNC_PROC_TASK_TYPE_CFG` (
  `TASK_TYPE` varchar(6) NOT NULL,
  `TASK_TYPE_NAME` varchar(100) DEFAULT NULL,
  `EXEC_SRV_ID` varchar(100) DEFAULT NULL,
  `TIMES_MAX_RETRY` int unsigned NOT NULL DEFAULT '0',
  `CREATED_TIME` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  PRIMARY KEY (`TASK_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for BIZ_EVENT_LOG
-- ----------------------------
DROP TABLE IF EXISTS `BIZ_EVENT_LOG`;
CREATE TABLE `BIZ_EVENT_LOG` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `LOGIN_ID` varchar(20) NOT NULL COMMENT '登录ID',
  `USER_NAME` varchar(20) NOT NULL COMMENT '登录用户名',
  `EVENT_TYPE` varchar(5) NOT NULL COMMENT '事件类型[10:ARTICLE;20:COMMENT;30:TAGS;40:MENU;50:WEBCONF:60:EMAILTPL]',
  `OPT_TYPE` varchar(5) NOT NULL COMMENT '操作类型[10:,创建, 1010:创建至发布(article), 1020:创建保存草稿(article), 1030:创建至回收站(article),\r\n  20:更新, 2000:编辑文章更新(article), 2010:编辑文章并发布(article), 2020:编辑文章,发布恢复为草稿(article), 2030:编辑文章,移动至回收站(article), 2050:编辑文章信息更新(article), \r\n  2010S:草稿发布(article,只针对status修改), 2020S:恢复为草稿(article,只针对status修改), 2030S:移动至回收站(article,只针对status修改),\r\n  2040S:回收站恢复(article,只针对status修改), 2110:MENU排序, 40:删除, ]',
  `OPT_ENTITY_ID` varchar(20) DEFAULT NULL COMMENT '操作表的主键',
  `OPT_ENTITY_DESC` varchar(100) DEFAULT NULL COMMENT '操作表的描述',
  `TRIGGER_TIME` datetime NOT NULL COMMENT '触发时间',
  `TRIGGER_IP` varchar(20) DEFAULT NULL COMMENT '触发IP',
  `PROCESS_STATUS` varchar(5) NOT NULL DEFAULT '90' COMMENT '成功状态[90:成功; 91:失败, (目前只登记成功的)]',
  `FAILED_REASON` varchar(200) DEFAULT NULL COMMENT '失败理由',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=442 DEFAULT CHARSET=utf8 COMMENT='事件日志表';

-- ----------------------------
-- Table structure for BIZ_PRAISE
-- ----------------------------
DROP TABLE IF EXISTS `BIZ_PRAISE`;
CREATE TABLE `BIZ_PRAISE` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `BIZ_OID` bigint NOT NULL COMMENT '文章/评论oid',
  `USER_IP` varchar(20) DEFAULT NULL COMMENT '用户ip',
  `TYPE` varchar(2) DEFAULT NULL COMMENT '类型,10:文章,20:评论',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='文章/评论赞记录表';

-- ----------------------------
-- Table structure for COMMENTS
-- ----------------------------
DROP TABLE IF EXISTS `COMMENTS`;
CREATE TABLE `COMMENTS` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `ARTICLE_OID` bigint unsigned DEFAULT NULL COMMENT '文章id',
  `ARTICLE_TITLE` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `ARTICLE_AUTHOR` varchar(10) DEFAULT NULL COMMENT '文章作者',
  `ARTICLE_PUSH_BY` varchar(20) DEFAULT 'admin' COMMENT '发布的用户',
  `USER_ID` varchar(20) NOT NULL COMMENT '评论的用户ID',
  `USER_NAME` varchar(30) DEFAULT NULL COMMENT '评论用户',
  `USER_NICK` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `CLIENT_IP` varchar(10) DEFAULT NULL COMMENT '评论ip',
  `USER_REGION` varchar(30) DEFAULT NULL COMMENT '用户地区',
  `COMMENT_TIME` datetime NOT NULL COMMENT '评论日期',
  `CONTENT` text NOT NULL COMMENT '评论内容',
  `PARENT_OID` bigint NOT NULL COMMENT '父评论OID',
  `REPLY_ROOT_OID` bigint DEFAULT NULL COMMENT '回复的根评论的主键',
  `REPLY_TO_USER_ID` varchar(20) DEFAULT NULL COMMENT '回复的父评论的用户ID',
  `REPLY_TO_USER_NAME` varchar(30) DEFAULT NULL COMMENT '回复的父评论的用户名',
  `REPLY_TO_USER_NICK` varchar(50) DEFAULT NULL COMMENT '回复的父评论的用户昵称',
  `PRAISE_COUNT` bigint DEFAULT NULL COMMENT '评论所得赞数量',
  `CONTACT_QQ` varchar(20) DEFAULT NULL COMMENT '评论者qq',
  `CONTACT_WECHAT` varchar(50) DEFAULT NULL COMMENT '评论者微信',
  `CONTACT_WEIBO` varchar(50) DEFAULT NULL COMMENT '评论者微博',
  `CONTACT_TEL` varchar(20) DEFAULT NULL COMMENT '评论者联系手机号',
  `CONTACT_EMAIL` varchar(30) DEFAULT NULL COMMENT '用户电子邮箱',
  `AVATAR_SRC` varchar(200) DEFAULT NULL COMMENT '头像',
  `ART_DEL_STATUS` varchar(2) DEFAULT '00' COMMENT '文章删除时(回收站)为91,未删除为00',
  `READ_STATUS` varchar(10) DEFAULT '00' COMMENT '已读状态[00:未读,10:已读]',
  `FLOOR_NUMBER` bigint DEFAULT NULL COMMENT '评论楼数',
  `REPLY_COUNT` bigint DEFAULT '0' COMMENT '下一级回复评论数量',
  `TOTAL_REPLY_COUNT` bigint DEFAULT '0' COMMENT '所有回复评论数量',
  `IS_DELETE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '是否已经删除',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for EMAIL
-- ----------------------------
DROP TABLE IF EXISTS `EMAIL`;
CREATE TABLE `EMAIL` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `TO_EMAIL` varchar(200) NOT NULL COMMENT '收件人, JSON数组',
  `EMAIL_TPL_CODE` varchar(20) DEFAULT NULL COMMENT '邮件模板编号',
  `SUBJECT` varchar(500) NOT NULL COMMENT '邮件主题',
  `CONTENT` text COMMENT '邮件内容',
  `JSON_DATA` varchar(2000) DEFAULT NULL COMMENT '组装的JSON数据',
  `FILES` varchar(200) DEFAULT NULL COMMENT '邮件附件',
  `PROC_STATUS` varchar(10) DEFAULT '00',
  `PROC_DESC` varchar(2000) DEFAULT NULL,
  `PROC_TIME` datetime NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=809 DEFAULT CHARSET=utf8 COMMENT='邮箱表';

-- ----------------------------
-- Table structure for EMAIL_TEMPLATE
-- ----------------------------
DROP TABLE IF EXISTS `EMAIL_TEMPLATE`;
CREATE TABLE `EMAIL_TEMPLATE` (
  `EMAIL_TPL_CODE` varchar(20) NOT NULL COMMENT '邮件模板编号',
  `DESC` varchar(100) NOT NULL COMMENT '描述',
  `SUBJECT` varchar(500) NOT NULL COMMENT '邮件模板主题',
  `CONTENT` text NOT NULL COMMENT '邮件模板内容,HTML格式',
  `TEST_JSON_DATA` varchar(2000) DEFAULT NULL COMMENT '测试的JSON数据',
  `STATUS_USED` varchar(1) DEFAULT '1' COMMENT '是否在使用',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`EMAIL_TPL_CODE`),
  UNIQUE KEY `EMAIL_TPL_INDEX` (`EMAIL_TPL_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件模板';

-- ----------------------------
-- Table structure for FILE_UPLOAD
-- ----------------------------
DROP TABLE IF EXISTS `FILE_UPLOAD`;
CREATE TABLE `FILE_UPLOAD` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `FILE_UUID` varchar(50) NOT NULL,
  `FILE_NAME` varchar(100) NOT NULL,
  `OLD_FILE_NAME` varchar(100) NOT NULL,
  `SIZE` bigint DEFAULT NULL,
  `TYPE` varchar(100) NOT NULL,
  `MEDIA_INFO` varchar(1000) DEFAULT NULL,
  `PATH` varchar(100) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `CDN_URL` varchar(200) DEFAULT NULL COMMENT '上传至CDN的URL',
  `FILE_MD5` varchar(100) DEFAULT NULL,
  `IS_CHUNK` char(1) DEFAULT NULL,
  `IS_DELETE` char(1) DEFAULT NULL,
  `UPLOAD_TIME` datetime NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=666 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for IP_BLACK_LIST
-- ----------------------------
DROP TABLE IF EXISTS `IP_BLACK_LIST`;
CREATE TABLE `IP_BLACK_LIST` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `ACCESS_IP` varchar(20) NOT NULL COMMENT '访问ip',
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=2293 DEFAULT CHARSET=utf8 COMMENT='IP黑名单';

-- ----------------------------
-- Table structure for LOGIN_FAILED_EVENT
-- ----------------------------
DROP TABLE IF EXISTS `LOGIN_FAILED_EVENT`;
CREATE TABLE `LOGIN_FAILED_EVENT` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `LOGIN_ID` varchar(20) NOT NULL COMMENT 'LOGIN ID',
  `LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '登录ip',
  `FAILED_TIMES` int DEFAULT '0' COMMENT '失败次数',
  `IS_BLOCK` char(1) DEFAULT '0' COMMENT '是否冻结',
  `BLOCK_TIME` datetime DEFAULT NULL COMMENT '冻结时间',
  `UNBLOCK_TIME` datetime DEFAULT NULL COMMENT '解冻时间',
  `STATUS` varchar(20) DEFAULT '00' COMMENT '状态:00有效,91废弃',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='登录失败事件表';

-- ----------------------------
-- Table structure for LOGIN_LOG
-- ----------------------------
DROP TABLE IF EXISTS `LOGIN_LOG`;
CREATE TABLE `LOGIN_LOG` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `LOGIN_ID` varchar(20) NOT NULL COMMENT '登录ID',
  `USER_NAME` varchar(20) NOT NULL COMMENT '登录用户名',
  `EVENT_TYPE` varchar(5) NOT NULL COMMENT '事件类型[10:登录; 20:退出; 30:修改密码]',
  `TRIGGER_TIME` datetime NOT NULL COMMENT '触发时间',
  `TRIGGER_DESC` varchar(200) DEFAULT NULL COMMENT '触发描述',
  `TRIGGER_IP` varchar(20) DEFAULT NULL COMMENT '触发IP',
  `TRIGGER_REGION` varchar(30) DEFAULT NULL COMMENT '触发地区',
  `TRIGGER_DEVICE` varchar(200) DEFAULT NULL COMMENT '触发设备',
  `PROCESS_STATUS` varchar(5) NOT NULL DEFAULT '90' COMMENT '成功状态[90:成功; 91:失败]',
  `FAILED_REASON` varchar(200) DEFAULT NULL COMMENT '失败理由',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=269 DEFAULT CHARSET=utf8 COMMENT='登录日志表';

-- ----------------------------
-- Table structure for MENU
-- ----------------------------
DROP TABLE IF EXISTS `MENU`;
CREATE TABLE `MENU` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `NAME` varchar(50) NOT NULL COMMENT '菜单名称',
  `INDEX` int NOT NULL COMMENT '菜单索引',
  `TYPE` varchar(10) NOT NULL COMMENT '菜单类型[10:前台；20：后台]',
  `ICON` varchar(50) DEFAULT NULL,
  `TEXT` varchar(20) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `FLAG_CATEGORY` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否是文章类别',
  `VALID` char(1) DEFAULT '1',
  `PARENT_OID` bigint NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for MESSAGE_BOARD
-- ----------------------------
DROP TABLE IF EXISTS `MESSAGE_BOARD`;
CREATE TABLE `MESSAGE_BOARD` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `USER_ID` varchar(20) NOT NULL COMMENT '留言的用户ID',
  `USER_NAME` varchar(30) DEFAULT NULL COMMENT '留言用户',
  `USER_NICK` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `CLIENT_IP` varchar(10) DEFAULT NULL COMMENT '评论ip',
  `USER_REGION` varchar(30) DEFAULT NULL COMMENT '用户地区',
  `PUSH_TIME` datetime NOT NULL COMMENT '发表日期',
  `CONTENT` text NOT NULL COMMENT '留言内容',
  `PARENT_OID` bigint NOT NULL COMMENT '父留言OID',
  `REPLY_ROOT_OID` bigint DEFAULT NULL COMMENT '回复的根留言的主键',
  `REPLY_TO_USER_ID` varchar(20) DEFAULT NULL COMMENT '回复的父留言的用户ID',
  `REPLY_TO_USER_NAME` varchar(30) DEFAULT NULL COMMENT '回复的父留言的用户名',
  `REPLY_TO_USER_NICK` varchar(50) DEFAULT NULL COMMENT '回复的父留言的用户昵称',
  `PRAISE_COUNT` bigint DEFAULT NULL COMMENT '留言所得赞数量',
  `CONTACT_QQ` varchar(20) DEFAULT NULL COMMENT '留言者qq',
  `CONTACT_WECHAT` varchar(50) DEFAULT NULL COMMENT '留言者微信',
  `CONTACT_WEIBO` varchar(50) DEFAULT NULL COMMENT '留言者微博',
  `CONTACT_TEL` varchar(20) DEFAULT NULL COMMENT '留言者联系手机号',
  `CONTACT_EMAIL` varchar(30) DEFAULT NULL COMMENT '用户电子邮箱',
  `AVATAR_SRC` varchar(200) DEFAULT NULL COMMENT '头像',
  `READ_STATUS` varchar(10) DEFAULT '00' COMMENT '已读状态[00:未读,10:已读]',
  `REPLY_COUNT` bigint DEFAULT '0' COMMENT '下一级回复留言数量',
  `TOTAL_REPLY_COUNT` bigint DEFAULT '0' COMMENT '所有回复留言数量',
  `IS_DELETE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '是否已经删除',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='留言表';

-- ----------------------------
-- Table structure for MESSAGE_PROFILE
-- ----------------------------
DROP TABLE IF EXISTS `MESSAGE_PROFILE`;
CREATE TABLE `MESSAGE_PROFILE` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `BUSI_SCENE_CODE` varchar(8) NOT NULL COMMENT 'BUSI_TYPE+MSG_TYPE+RECEIVER_TYPE+OPT_TYPE组成',
  `BUSI_TYPE` varchar(2) NOT NULL COMMENT '00:博客平台,10:文章,20:评论,30:留言',
  `MSG_TYPE` varchar(2) NOT NULL COMMENT '10:通知,20:任务',
  `RECEIVER_TYPE` varchar(2) NOT NULL COMMENT '接收类型:00:博主,10:用户',
  `OPT_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '01:赞,02:评论/留言,03:更新,04:回复',
  `MSG_TITLE` varchar(256) DEFAULT NULL,
  `MSG_CONTENT` mediumblob NOT NULL,
  `MSG_LINK` varchar(200) DEFAULT NULL,
  `BIZ_ID` varchar(30) NOT NULL,
  `BIZ_PARAM` mediumblob,
  `RECEIVER_ID` varchar(50) NOT NULL,
  `RECEIVER_NAME` varchar(100) NOT NULL,
  `RECEIVER_EMAIL` varchar(100) DEFAULT NULL,
  `RECEIVER_MOBILE_NO` varchar(30) DEFAULT NULL,
  `EMAIL_NOTIFY` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否邮件通知',
  `EMAIL_TPL_CODE` varchar(20) DEFAULT NULL COMMENT '邮件模板编号,EMAIL_NOTIFY为1时必填',
  `EMAIL_SEND_RESULT` varchar(2) DEFAULT NULL COMMENT '邮件发送结果,90:成功,91:失败',
  `FLAG_READ` varchar(1) NOT NULL DEFAULT '0',
  `FLAG_POPUP` varchar(1) NOT NULL DEFAULT '0',
  `FLAG_POPUP_MAIN` varchar(1) NOT NULL DEFAULT '0',
  `READ_TIME` datetime DEFAULT NULL,
  `PROC_RSLT` varchar(2) DEFAULT NULL COMMENT '处理结果,90:成功,91:失败',
  `PROC_TIME` datetime DEFAULT NULL,
  `SEND_TIME` datetime NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=2477 DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Table structure for MESSAGE_TEMPLATE
-- ----------------------------
DROP TABLE IF EXISTS `MESSAGE_TEMPLATE`;
CREATE TABLE `MESSAGE_TEMPLATE` (
  `BUSI_SCENE_CODE` varchar(8) NOT NULL COMMENT 'BUSI_TYPE+MSG_TYPE+RECEIVER_TYPE+OPT_TYPE组成',
  `BUSI_TYPE` varchar(2) NOT NULL COMMENT '00:博客平台,10:文章,20:评论,30:留言',
  `MSG_TYPE` varchar(2) NOT NULL COMMENT '10:通知,20:任务',
  `RECEIVER_TYPE` varchar(2) NOT NULL COMMENT '接收类型:00:博主,10:用户',
  `OPT_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '01:赞,02:评论/留言,03:更新,04:回复',
  `MSG_DESC` varchar(100) DEFAULT NULL COMMENT '消息描述',
  `MSG_TITLE_TEMPLATE` varchar(200) DEFAULT NULL,
  `MSG_CONTENT_TEMPLATE` blob NOT NULL,
  `MSG_LINK_TEMPLATE` varchar(200) DEFAULT NULL,
  `STATUS_USED` varchar(1) NOT NULL DEFAULT '1',
  `EMAIL_NOTIFY` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否邮件通知',
  `EMAIL_TPL_CODE` varchar(20) DEFAULT NULL COMMENT '邮件模板编号,EMAIL_NOTIFY为1时必填',
  `CREATE_TIME` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  `UPDATE_BY` varchar(50) NOT NULL,
  PRIMARY KEY (`BUSI_SCENE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息模板表';


-- ----------------------------
-- Table structure for TAGS
-- ----------------------------
DROP TABLE IF EXISTS `TAGS`;
CREATE TABLE `TAGS` (
  `OID` bigint NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `CONTENT` varchar(20) NOT NULL COMMENT '标签内容',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Table structure for TEMP_ARTICLE
-- ----------------------------
DROP TABLE IF EXISTS `TEMP_ARTICLE`;
CREATE TABLE `TEMP_ARTICLE` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `AUTHOR` varchar(500) DEFAULT NULL COMMENT '文章作者',
  `TITLE` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章标题',
  `CONTENT` longtext COMMENT '文章内容(html格式)',
  `CATEGORY_ID` int DEFAULT NULL COMMENT '文章类型',
  `IS_COMMENT` char(1) DEFAULT '1' COMMENT '是否可以被评论',
  `FLAG_ORIGINAL` varchar(1) DEFAULT '1' COMMENT '是否原创',
  `ORIGINAL_URL` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '转载源链接',
  `IMG_SRC` varchar(1024) DEFAULT NULL COMMENT '文章缩略图',
  `ACCESS_TYPE` varchar(10) DEFAULT '20' COMMENT '公开度[私密:10, 密码:11, 公开:20, 置顶:21]',
  `TOP_PLACE_STATUS` varchar(2) DEFAULT '00' COMMENT '置顶状态,00:未置顶,50:置顶',
  `PASSWORD` varchar(500) DEFAULT NULL COMMENT '当公开度为-1时设置',
  `OLD_ARTICLE_OID` bigint unsigned DEFAULT NULL COMMENT '当文章是已有文章时,文章的OID,当是新文章时为-1',
  `TAGS` varchar(2000) DEFAULT NULL COMMENT '文章标签的JSON数组',
  `PAGE_TYPE` varchar(10) NOT NULL DEFAULT '00' COMMENT '页面类型[10:发布页面 20:编辑页面]',
  `STATUS` varchar(10) NOT NULL DEFAULT '00' COMMENT '文章状态 [已经恢复:90, 临时缓存状态:00, 作废:91]',
  `RESTORE_ARTICLE_OID` bigint unsigned DEFAULT NULL COMMENT '恢复文章的OID',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=1230503938417312011 DEFAULT CHARSET=utf8 COMMENT='临时文章表';


-- ----------------------------
-- Table structure for USER_PROFILE
-- ----------------------------
DROP TABLE IF EXISTS `USER_PROFILE`;
CREATE TABLE `USER_PROFILE` (
  `OID` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
  `USER_ID` varchar(20) NOT NULL COMMENT '用户ID',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '密码',
  `USER_NICK` varchar(50) DEFAULT NULL,
  `USER_PHONE` varchar(20) DEFAULT NULL COMMENT '手机号',
  `USER_EMAIL` varchar(30) DEFAULT NULL COMMENT '电子邮件',
  `USER_SEX` varchar(10) DEFAULT NULL COMMENT '性别[10:男, 20:女]',
  `REGISTER_IP` varchar(20) DEFAULT NULL COMMENT '注册ip地址',
  `REGISTER_REGION` varchar(30) DEFAULT NULL COMMENT '用户地区',
  `LAST_LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '最后登录ip',
  `LAST_LOGIN_REGION` varchar(30) DEFAULT NULL COMMENT '最后登录地区',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  `USER_QQ` varchar(20) DEFAULT NULL COMMENT 'qq',
  `USER_WECHAT` varchar(50) DEFAULT NULL COMMENT '微信',
  `USER_WEIBO` varchar(50) DEFAULT NULL COMMENT '微博',
  `USER_TEL` varchar(20) DEFAULT NULL COMMENT '联系手机号',
  `AVATAR_SRC` varchar(200) DEFAULT NULL COMMENT '头像',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for WEBCONF
-- ----------------------------
DROP TABLE IF EXISTS `WEBCONF`;
CREATE TABLE `WEBCONF` (
  `KEY` varchar(50) NOT NULL,
  `DESC` varchar(200) DEFAULT NULL,
  `CFG_TYPE` varchar(10) DEFAULT 'SYS' COMMENT '配置类型',
  `VALUE_TYPE` varchar(10) DEFAULT NULL COMMENT 'S:STRING, N:NUMBER; D:DATE; B:BOOLEAN, default:S',
  `STRING_VALUE` varchar(2000) DEFAULT NULL,
  `INT_VALUE` int DEFAULT NULL COMMENT 'INT 类型',
  `FLOAT_VALUE` decimal(10,4) DEFAULT NULL COMMENT 'FLOAT 类型',
  `BOOLEAN_VALUE` char(1) DEFAULT NULL COMMENT 'BOOLEAN 类型',
  `DATE_VALUE` datetime DEFAULT NULL COMMENT 'DATE 类型',
  `VALID` char(1) DEFAULT '1',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`KEY`),
  UNIQUE KEY `WEB_CFG_INDEX` (`KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站配置表';

-- ----------------------------
-- Table structure for WEBCONF_FACADE
-- ----------------------------
DROP TABLE IF EXISTS `WEBCONF_FACADE`;
CREATE TABLE `WEBCONF_FACADE` (
  `CFG_KEY` varchar(50) NOT NULL COMMENT '配置参数字段名',
  `DESC` varchar(200) DEFAULT NULL,
  `TITLE_DSP` varchar(50) DEFAULT NULL,
  `TIPS_DSP` varchar(100) DEFAULT NULL,
  `FORM_TYPE` varchar(20) DEFAULT NULL COMMENT 'INPUT_TEXT, TEXTAREA; INPUT_FILE_IMAGE; INPUT_TEXT_NUMBER,INPUT_CHECKBOX:,SELECT,RADIO, default:INPUT_TEXT',
  `TAB_TYPE` varchar(20) DEFAULT NULL,
  `TAB_TYPE_DSP` varchar(50) DEFAULT NULL,
  `REQUIRED` char(1) DEFAULT NULL COMMENT '是否必填',
  `VALIDATE_TYPE` varchar(200) DEFAULT NULL COMMENT 'maxLength|number|minNum|maxNum|regex... ''|''分隔',
  `VALIDATE_VALUE` varchar(1000) DEFAULT NULL COMMENT 'JSON数据 eg: {\\"maxLength\\":{\\"value\\":50,\\"lay-verify\\":\\"lay-max=''50''\\"}}',
  `INDEX` int NOT NULL DEFAULT '1',
  `CREATE_TIME` datetime NOT NULL,
  `UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`CFG_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站配置表';

-- ----------------------------
-- Table structure for WORKER_NODE
-- ----------------------------
DROP TABLE IF EXISTS `WORKER_NODE`;
CREATE TABLE `WORKER_NODE` (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
  `HOST_NAME` varchar(64) NOT NULL COMMENT 'host name',
  `PORT` varchar(64) NOT NULL COMMENT 'port',
  `TYPE` int NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
  `LAUNCH_DATE` date NOT NULL COMMENT 'launch date',
  `MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modified time',
  `CREATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'created time',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3552 DEFAULT CHARSET=utf8 COMMENT='DB WorkerID Assigner for UID Generator';

SET FOREIGN_KEY_CHECKS = 1;



-- 1.1.0 版本脚本
ALTER table ARTICLE ADD INDEX INX_1(TITLE);
ALTER table ARTICLE ADD INDEX INX_2(PUSH_TIME);


ALTER TABLE `COMMENTS` ADD `FLAG_ADMIN`  varchar(1) DEFAULT '0'  COMMENT ' 评论是否是管理员发布' AFTER TOTAL_REPLY_COUNT;
ALTER TABLE `MESSAGE_BOARD` ADD `FLAG_ADMIN`  varchar(1) DEFAULT '0'  COMMENT ' 留言是否是管理员发布' AFTER TOTAL_REPLY_COUNT;
