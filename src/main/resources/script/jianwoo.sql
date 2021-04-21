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
   `AUTHOR` varchar(10) NOT NULL COMMENT '文章作者',
   `TITLE` text NULL COMMENT '文章标题',
   `CONTENT` longtext NULL COMMENT '文章内容(html格式)',
   `TYPE_ID` int(11) DEFAULT '0' COMMENT '文章类型',
   `IS_COMMENT` int(11) DEFAULT NULL COMMENT '是否可以被评论',
   `IMG_SRC` varchar(1024) DEFAULT NULL COMMENT '文章缩略图',
   `VISIT_TYPE` int(11) DEFAULT '1' COMMENT '公开度 -1：密码  , 1：公开 ,私密:0 ,置顶:2',
   `PASSWORD` varchar(50) DEFAULT NULL COMMENT '当公开度为-1时设置',
   `OLD_OID` bigint(20) unsigned DEFAULT NULL COMMENT '当文章是已有文章时,文章的OID,当是新文章时为-1',
   `TAGS` varchar(1000) DEFAULT NULL COMMENT '文章标签的JSON数组',
   `PAGE` int(2) NOT NULL COMMENT '1:发布页面 2编辑页面',
   `STATUS` int(11) NOT NULL COMMENT '临时文章状态 1已经恢复  0临时缓存状态  -1作废',
   `RESTORE_OID` bigint(20) unsigned DEFAULT NULL COMMENT '恢复文章的OID',
   `CREATE_DATE` datetime NOT NULL,
   `UPDATE_DATE` datetime NOT NULL,
   PRIMARY KEY (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='临时文章表';





