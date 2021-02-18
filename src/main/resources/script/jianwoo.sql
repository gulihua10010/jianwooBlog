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
    `URL`='/web/clear',
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
    `URL`=NULL,
    `VALID`='1',
    `PARENT_OID`='21'
WHERE
    (`OID` = '24');

