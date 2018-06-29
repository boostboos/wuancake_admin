# wuancake_admin
默认读取/etc/application.yml配置文件，可在启动类AdminBackApplication中修改配置文件位置，配置如下：
	
	spring:
	  datasource:
	    url: jdbc:mysql://数据库地址:3306/数据库名?useSSL=false    
	    password: 数据库账号					
	    username: 数据库密码					
	    driver-class-name: com.mysql.jdbc.Driver
	    type: com.alibaba.druid.pool.DruidDataSource
	    initialSize: 5
	    minIdle: 5
	    maxActive: 20
	    maxWait: 60000
	    timeBetweenEvictionRunsMillis: 60000
	    minEvictableIdleTimeMillis: 300000
	    validationQuery: SELECT 1 FROM DUAL
	    testWhileIdle: true
	    testOnBorrow: false
	    testOnReturn: false
	    poolPreparedStatements: true
	    maxPoolPreparedStatementPerConnectionSize: 20
	    spring.datasource.filters: stat,wall,log4j
	    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
	  mvc:
	    view:
	      prefix:
	      suffix: .jsp
	  cache:
	    ehcache:
	      config: ehcache.xml
	mybatis:
	  configuration:
	    map-underscore-to-camel-case: true
	customConfig:
	  pageSize: 8					
	server:
	  port: 项目端口					
