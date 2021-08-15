ALTER TABLE COMMENTS
    ADD COLUMN AREA VARCHAR(100) AFTER IP
ALTER TABLE VISIT
    ADD COLUMN AREA VARCHAR(100) AFTER IP


CREATE TABLE WORKER_NODE
(
    ID          BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
    HOST_NAME   VARCHAR(64) NOT NULL COMMENT 'host name',
    PORT        VARCHAR(64) NOT NULL COMMENT 'port',
    TYPE        INT         NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
    LAUNCH_DATE DATE        NOT NULL COMMENT 'launch date',
    MODIFIED    TIMESTAMP   NOT NULL COMMENT 'modified time',
    CREATED     TIMESTAMP   NOT NULL COMMENT 'created time',
    PRIMARY KEY (ID)
)
    COMMENT ='DB WorkerID Assigner for UID Generator', ENGINE = INNODB;



INSERT
INTO
    WEBCONF
VALUES
    (13, 'IS_LOGIN_NEED_CAPTCHA', NULL, NULL, 1, '2020-11-20 10:39:54', '2021-01-26 10:02:07');


ALTER TABLE ADMIN
    ADD COLUMN NICK VARCHAR(20) AFTER PASSWORD;
ALTER TABLE MENU
    ADD COLUMN VALID CHAR(1) DEFAULT '1' AFTER URL;
ALTER TABLE MENU
    MODIFY COLUMN ICON VARCHAR(50);


-- update menu
UPDATE `menu`
SET
    `OID`='8',
    `NAME`='index',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`='layui-icon-home',
    `TEXT`='首页',
    `URL`=NULL,
    `VALID`='1',
    `PARENT_OID`='0'
WHERE
    (`OID` = '8');
UPDATE `menu`
SET
    `OID`='9',
    `NAME`='index',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='主页',
    `URL`='index',
    `VALID`='1',
    `PARENT_OID`='8'
WHERE
    (`OID` = '9');
UPDATE `menu`
SET
    `OID`='10',
    `NAME`='dynamic',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='动态',
    `URL`='/dynamic',
    `VALID`='1',
    `PARENT_OID`='8'
WHERE
    (`OID` = '10');
UPDATE `menu`
SET
    `OID`='11',
    `NAME`='articleMangement',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`='layui-icon-template-1',
    `TEXT`='文章管理',
    `URL`=NULL,
    `VALID`='1',
    `PARENT_OID`='0'
WHERE
    (`OID` = '11');
UPDATE `menu`
SET
    `OID`='12',
    `NAME`='articlePublished',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='文章发布',
    `URL`='/article/published',
    `VALID`='1',
    `PARENT_OID`='11'
WHERE
    (`OID` = '12');
UPDATE `menu`
SET
    `OID`='13',
    `NAME`='myArticle',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='我的文章',
    `URL`='/article/list',
    `VALID`='1',
    `PARENT_OID`='11'
WHERE
    (`OID` = '13');
UPDATE `menu`
SET
    `OID`='14',
    `NAME`='commentManagement',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='评论管理',
    `URL`='/comment/list',
    `VALID`='1',
    `PARENT_OID`='11'
WHERE
    (`OID` = '14');
UPDATE `menu`
SET
    `OID`='15',
    `NAME`='articleTags',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='文章标签',
    `URL`='/article/tags',
    `VALID`='1',
    `PARENT_OID`='11'
WHERE
    (`OID` = '15');
UPDATE `menu`
SET
    `OID`='16',
    `NAME`='recycleBin',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='文章回收站',
    `URL`='/article/recycleBin',
    `VALID`='1',
    `PARENT_OID`='11'
WHERE
    (`OID` = '16');
UPDATE `menu`
SET
    `OID`='17',
    `NAME`='menuManagement',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`='layui-icon-menu-fill',
    `TEXT`='菜单管理',
    `URL`=NULL,
    `VALID`='1',
    `PARENT_OID`='0'
WHERE
    (`OID` = '17');
UPDATE `menu`
SET
    `OID`='18',
    `NAME`='menuMg',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='菜单管理',
    `URL`='/menu/view',
    `VALID`='1',
    `PARENT_OID`='17'
WHERE
    (`OID` = '18');
UPDATE `menu`
SET
    `OID`='19',
    `NAME`='userManagement',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='用户管理',
    `URL`=NULL,
    `VALID`='0',
    `PARENT_OID`='0'
WHERE
    (`OID` = '19');
UPDATE `menu`
SET
    `OID`='20',
    `NAME`='regUser',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='注册用户',
    `URL`=NULL,
    `VALID`='0',
    `PARENT_OID`='19'
WHERE
    (`OID` = '20');
UPDATE `menu`
SET
    `OID`='21',
    `NAME`='systemManagement',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`='layui-icon-set-fill',
    `TEXT`='系统管理',
    `URL`=NULL,
    `VALID`='1',
    `PARENT_OID`='0'
WHERE
    (`OID` = '21');
UPDATE `menu`
SET
    `OID`='22',
    `NAME`='webConfig',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='网站配置',
    `URL`='/web/config',
    `VALID`='1',
    `PARENT_OID`='21'
WHERE
    (`OID` = '22');
UPDATE `menu`
SET
    `OID`='23',
    `NAME`='pptManagement',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='首页幻灯片',
    `URL`='/web/ppt',
    `VALID`='0',
    `PARENT_OID`='21'
WHERE
    (`OID` = '23');
UPDATE `menu`
SET
    `OID`='24',
    `NAME`='clearCache',
    `INDEX`='0',
    `CREATE_DATE`='2020-08-15 22:37:17',
    `UPDATE_DATE`='2020-08-15 22:37:17',
    `TYPE`='2',
    `ICON`=NULL,
    `TEXT`='清除缓存',
    `URL`='/web/clear',
    `VALID`='1',
    `PARENT_OID`='21'
WHERE
    (`OID` = '24');



ALTER TABLE FILE_UPLOAD ADD COLUMN MEDIA_INFO VARCHAR(1000) AFTER TYPE;
ALTER TABLE FILE_UPLOAD DROP COLUMN MEDIA_TIME ;


ALTER TABLE WEBCONF ADD COLUMN VALID CHAR(1) AFTER BOOLEAN_VALUE DEFAULT '1';
ALTER TABLE WEBCONF ADD COLUMN `DESC` VARCHAR(200) AFTER `KEY`;
ALTER TABLE WEBCONF ADD COLUMN TITLE VARCHAR(50) AFTER `DESC`;
ALTER TABLE WEBCONF ADD COLUMN VALUE_TYPE VARCHAR(10) AFTER TITLE COMMENT 'S:STRING, N:NUMBER; D:DATE; B:BOOLEAN, default:S';
ALTER TABLE WEBCONF ADD COLUMN FORM_TYPE VARCHAR(20) AFTER TYPE COMMENT 'INPUT_TEXT, TEXTAREA; INPUT_FILE_IMAGE; INPUT_TEXT_NUMBER,INPUT_CHECKBOX:,SELECT,RADIO, default:INPUT_TEXT';
ALTER TABLE WEBCONF ADD COLUMN DATE_VALUE datetime AFTER BOOLEAN_VALUE;
ALTER TABLE WEBCONF ADD COLUMN MANDATORY CHAR(1) AFTER DATE_VALUE COMMENT '是否必填';
ALTER TABLE WEBCONF ADD COLUMN VALIDATE_TYPE VARCHAR(200) AFTER MANDATORY COMMENT "maxLength|number|minNum|maxNum|regex... '|'分隔";
ALTER TABLE WEBCONF ADD COLUMN VALIDATE_VALUE VARCHAR(1000) AFTER VALIDATE_TYPE COMMENT "JSON数据 eg: {\"maxLength\":{\"value\":50,\"lay-verify\":\"lay-max=''50''\"}}";
ALTER TABLE WEBCONF ADD COLUMN `INDEX` int(6) AFTER VALIDATE_VALUE  DEFAULT 1;
ALTER TABLE WEBCONF MODIFY COLUMN `NUM_VALUE` DECIMAL(10,4)  ;
ALTER TABLE WEBCONF MODIFY COLUMN `STRING_VALUE` VARCHAR(2000)  ;
ALTER TABLE ARTICLE MODIFY COLUMN `STATUS` int(11) NOT NULL COMMENT '文章状态 1已发布  0草稿    -1回收站  -2 临时文章';


CREATE TABLE `TEMP_ARTICLE` (
                                `OID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
                                `AUTHOR` varchar(500) DEFAULT NULL COMMENT '文章作者',
                                `TITLE` text NULL COMMENT '文章标题',
                                `CONTENT` longtext NULL COMMENT '文章内容(html格式)',
                                `TYPE_ID` int(11) DEFAULT '0' COMMENT '文章类型',
                                `IS_COMMENT` int(11) DEFAULT NULL COMMENT '是否可以被评论',
                                `IMG_SRC` varchar(1024) DEFAULT NULL COMMENT '文章缩略图',
                                `VISIT_TYPE` int(11) DEFAULT '1' COMMENT '公开度 -1：密码  , 1：公开 ,私密:0 ,置顶:2',
                                `PASSWORD` varchar(500) DEFAULT NULL COMMENT '当公开度为-1时设置',
                                `OLD_OID` bigint(20) unsigned DEFAULT NULL COMMENT '当文章是已有文章时,文章的OID,当是新文章时为-1',
                                `TAGS` varchar(2000) DEFAULT NULL COMMENT '文章标签的JSON数组',
                                `PAGE` int(2) NOT NULL COMMENT '1:发布页面 2编辑页面',
                                `STATUS` int(11) NOT NULL COMMENT '临时文章状态 1已经恢复  0临时缓存状态  -1作废',
                                `RESTORE_OID` bigint(20) unsigned DEFAULT NULL COMMENT '恢复文章的OID',
                                `CREATE_DATE` datetime NOT NULL,
                                `UPDATE_DATE` datetime NOT NULL,
                                PRIMARY KEY (`OID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='临时文章表';


ALTER TABLE MENU
    MODIFY COLUMN `CREATE_DATE` datetime NOT NULL AFTER PARENT_OID;
ALTER TABLE MENU
    MODIFY COLUMN `UPDATE_DATE` datetime NOT NULL AFTER CREATE_DATE;



ALTER TABLE WEBCONF
    ADD COLUMN `TAB_TYPE` VARCHAR(20) AFTER `FORM_TYPE`;
ALTER TABLE WEBCONF
    ADD COLUMN `TAB_TYPE_DESC` VARCHAR(50) AFTER `TAB_TYPE`;


UPDATE `webconf`
SET
    `TAB_TYPE`='SYS',
    `TAB_TYPE_DESC`='系统配置'
WHERE
    (`OID` IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));



INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('15', 'EMAIL_HOST', '邮件服务器的SMTP地址', '邮件SMTP地址',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('16', 'EMAIL_PORT', '邮件服务器的SMTP端口', '邮件SMTP端口',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('17', 'EMAIL_USER', '发件人（必须正确，否则发送失败）', '用户',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('18', 'EMAIL_SENDER', '发件人（必须正确，否则发送失败）', '发件人',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('19', 'EMAIL_USER', '用户名（注意：如果使用foxmail邮箱，此处user为qq号）', '用户',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('20', 'EMAIL_PWD', '密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）', '密码',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('21', 'EMAIL_STARTTLS', '使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。', '开启STARTTLS',
     'B', 'INPUT_CHECKBOX', 'EMAIL', '邮件配置',
     NULL, NULL, '1', NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('22', 'EMAIL_SSL', '使用SSL安全连接', '开启SSL安全连接',
     'B', 'INPUT_CHECKBOX', 'EMAIL', '邮件配置',
     NULL, NULL, '1', NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('23', 'EMAIL_SOCKET_FACTORY_CLASS', '指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字', 'socketFactoryClass',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('24', 'EMAIL_SOCKET_FACTORY_FALLBACK', '如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true',
     'socketFactoryFallback ',
     'B', 'INPUT_CHECKBOX', 'EMAIL', '邮件配置',
     NULL, NULL, '1', NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('25', 'EMAIL_SOCKET_FACTORY_PORT', '指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口456', 'socketFactoryPort ',
     'S', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     '456', NULL, NULL, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('26', 'EMAIL_STMT_TIMEOUT', 'SMTP超时时长，单位毫秒，缺省值不超时', 'STMT超时时间 ',
     'N', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     NULL, NULL, 0, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');
INSERT
INTO
    `webconf`
    (`OID`, `KEY`, `DESC`, `TITLE`, `VALUE_TYPE`, `FORM_TYPE`, `TAB_TYPE`, `TAB_TYPE_DESC`, `STRING_VALUE`, `NUM_VALUE`,
     `BOOLEAN_VALUE`, `DATE_VALUE`, `MANDATORY`, `VALIDATE_TYPE`, `VALIDATE_VALUE`, `INDEX`, `VALID`, `CREATE_DATE`,
     `UPDATE_DATE`)
VALUES
    ('27', 'EMAIL_SOCKET_TIMEOUT', 'Socket连接超时值，单位毫秒，缺省值不超时', ' Socket超时时间 ',
     'N', 'INPUT_TEXT', 'EMAIL', '邮件配置',
     NULL, NULL, 0, NULL, NULL, NULL, NULL, '13', '1', '2020-11-20 10:39:54', '2021-07-01 15:57:11');


CREATE TABLE `WEBCONF_FACADE`
(
    `OID`            bigint(11) NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
    `CONF_OID`       bigint(11) NOT NULL COMMENT 'WEBCONF.OID',
    `DESC`           varchar(200)  DEFAULT NULL,
    `TITLE_DSP`      varchar(50)   DEFAULT NULL,
    `TIPS_DSP`       varchar(50)   DEFAULT NULL,
    `FORM_TYPE`      varchar(20)   DEFAULT NULL COMMENT 'INPUT_TEXT, TEXTAREA; INPUT_FILE_IMAGE; INPUT_TEXT_NUMBER,INPUT_CHECKBOX:,SELECT,RADIO, default:INPUT_TEXT',
    `TAB_TYPE`       varchar(20)   DEFAULT NULL,
    `TAB_TYPE_DSP`   varchar(50)   DEFAULT NULL,
    `MANDATORY`      char(1)       DEFAULT NULL COMMENT '是否必填',
    `VALIDATE_TYPE`  varchar(200)  DEFAULT NULL COMMENT 'maxLength|number|minNum|maxNum|regex... ''|''分隔',
    `VALIDATE_VALUE` varchar(1000) DEFAULT NULL COMMENT 'JSON数据 eg: {\\"maxLength\\":{\\"value\\":50,\\"lay-verify\\":\\"lay-max=''50''\\"}}',
    `INDEX`          int(6)        DEFAULT '1',
    `CREATE_DATE`    datetime   NOT NULL,
    `UPDATE_DATE`    datetime   NOT NULL,
    PRIMARY KEY (`OID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  DEFAULT CHARSET = utf8 COMMENT ='网站配置表';

INSERT
INTO
    WEBCONF_FACADE(OID, CONF_OID, `DESC`, TITLE_DSP, FORM_TYPE, TAB_TYPE, TAB_TYPE_DSP, MANDATORY, VALIDATE_TYPE,
                   VALIDATE_VALUE, `INDEX`, CREATE_DATE, UPDATE_DATE)
SELECT
    OID, OID, `DESC`, TITLE, FORM_TYPE, TAB_TYPE, TAB_TYPE_DESC, MANDATORY, VALIDATE_TYPE,
    VALIDATE_VALUE, `INDEX`, CREATE_DATE, UPDATE_DATE
FROM
    WEBCONF


ALTER TABLE `webconf`
    DROP COLUMN TITLE;
ALTER TABLE `webconf`
    DROP COLUMN FORM_TYPE;
ALTER TABLE `webconf`
    DROP COLUMN TAB_TYPE;
ALTER TABLE `webconf`
    DROP COLUMN TAB_TYPE_DESC;
ALTER TABLE `webconf`
    DROP COLUMN MANDATORY;
ALTER TABLE `webconf`
    DROP COLUMN VALIDATE_TYPE;
ALTER TABLE `webconf`
    DROP COLUMN VALIDATE_VALUE;
ALTER TABLE `webconf`
    DROP COLUMN `INDEX`;



CREATE UNIQUE INDEX WEB_CFG_INDEX
    ON `webconf` (`KEY`);



CREATE TABLE `EMAIL`
(
    `OID`            bigint(11)   NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
    `TO_EMAIL`       varchar(200) NOT NULL COMMENT '收件人, JSON数组',
    `EMAIL_TPL_CODE` varchar(20)  NOT NULL COMMENT '邮件模板编号',
    `SUBJECT`        varchar(500) NOT NULL COMMENT '邮件主题',
    `CONTENT`        TEXT          DEFAULT NULL COMMENT '邮件内容',
    `JSON_DATA`      varchar(2000) DEFAULT NULL COMMENT '组装的JSON数据',
    `FILES`          varchar(200)  DEFAULT NULL COMMENT '邮件附件',
    `PROC_STATUS`    varchar(10)   DEFAULT '00',
    `PROC_DESC`      varchar(2000) DEFAULT NULL,
    `PROC_TIME`      datetime     NOT NULL,
    `CREATE_DATE`    datetime     NOT NULL,
    `UPDATE_DATE`    datetime     NOT NULL,
    PRIMARY KEY (`OID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  DEFAULT CHARSET = utf8 COMMENT ='邮箱表';


CREATE UNIQUE INDEX EMAIL_TPL_INDEX
    ON `EMAIL_TEMPLATE` (`EMAIL_TPL_CODE`);

CREATE TABLE `EMAIL_TEMPLATE`
(
    `OID`            bigint(11)   NOT NULL AUTO_INCREMENT COMMENT '表自增唯一id',
    `EMAIL_TPL_CODE` varchar(20)  NOT NULL COMMENT '邮件模板编号',
    `DESC`           varchar(100) NOT NULL COMMENT '描述',
    `SUBJECT`        varchar(500) NOT NULL COMMENT '邮件模板主题',
    `CONTENT`        TEXT         NOT NULL COMMENT '邮件模板内容,HTML格式',
    `TEST_JSON_DATA` varchar(2000) DEFAULT NULL COMMENT '测试的JSON数据',
    `CREATE_DATE`    datetime     NOT NULL,
    `UPDATE_DATE`    datetime     NOT NULL,
    PRIMARY KEY (`OID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='邮件模板';




