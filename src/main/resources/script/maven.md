## 1

mvn install:install-file -Dfile=${path}/uid-generate-spring-boot-starter-1.0.0.jar -DgroupId=com.baidu.fsd
-DartifactId=uid-generate-spring-boot-starter -Dversion=1.0.0 -Dpackaging=jar

## 2

mvn install:install-file -Dfile=${path}/sauronsoftware-1.0.2.jar -DgroupId=it.sauronsoftware -DartifactId=sauronsoftware
-Dversion=1.0.2 -Dpackaging=jar

## 3

mvn install:install-file -Dfile=${path}/mybatis-generate-code-1.0-SNAPSHOT.jar -DgroupId=cn.jianwoo.dev
-DartifactId=mybatis-generate-code -Dversion=1.0-SNAPSHOT -Dpackaging=jar
