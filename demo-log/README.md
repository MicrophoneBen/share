# 概述

日志框架示例

# demo-log4j



# demo-log4j2



# demo-logback

#               （一）logback  java使用

## 一、 logback介绍

Logback是由log4j创始人设计的又一个开源日志组件。logback当前分成三个模块：logback-core,logback- classic和logback-access。logback-core是其它两个模块的基础模块。logback-classic是log4j的一个 改良版本。此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging。logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能（来源于百度百科）。

## 二、maven依赖

1.普通 maven web 项目：

```xml

<!-- logback+slf4j -->  
<dependency>  
    <groupId>org.slf4j</groupId>  
    <artifactId>slf4j-api</artifactId>  
    <version>1.6.0</version>  
    <type>jar</type>  
    <scope>compile</scope>  
</dependency>  
<dependency>  
    <groupId>ch.qos.logback</groupId>  
    <artifactId>logback-core</artifactId>  
    <version>0.9.28</version>  
    <type>jar</type>  
</dependency>  
<dependency>  
    <groupId>ch.qos.logback</groupId>  
    <artifactId>logback-classic</artifactId>  
    <version>0.9.28</version>  
    <type>jar</type>  
</dependency> 
```

2.springboot 项目

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

springboot 项目默认日志框架就是 logback



## 三、日志使用

1.集成 lombok 方式：

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
```

```java
@Sl4j
public class TestController {  
    public void hello(){  
        log.debug("DEBUG TEST 这个地方输出DEBUG级别的日志");  
        log.info("INFO test 这个地方输出INFO级别的日志");  
        log.error("ERROR test 这个地方输出ERROR级别的日志");  
    }  
}
```

2.未集成 lombok 方式：

```java
public class TestController  {  
    protected final Logger log = LoggerFactory.getLogger(TestController.class);  
  
    public void hello(){  
        log.debug("DEBUG TEST 这个地方输出DEBUG级别的日志");  
        log.info("INFO test 这个地方输出INFO级别的日志");  
        log.error("ERROR test 这个地方输出ERROR级别的日志");  
    }  
} 

```

# 				（二）logback  配置详解
## 一、logback 配置文件示例
由于logback 的使用大多数结合 springboot 项目使用，接下来以logback-spirng.xml 作为配置文件讲解模板

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- configuration节点:
			scan: 当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
			scanPeriod: 设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
			debug: 当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
-->
<configuration scan="true" scanPeriod="60 seconds" debug="false">
  	<!--   springProperty节点: 从 springboot 配置文件读取配置,从 source映射到 name 
						定义的变量能够在通过${} 在logger 中使用：
						例: ${logback.logPath}/${logback.appname}_info.%d{yyyy-MM-dd}-%i.log
		-->
    <springProperty name="logback.logPath" source="logback.logPath"/>
    <springProperty name="logback.appname" source="logback.appname"/>
    <!-- appender:
     1.name: appender组件的名称，后面给logger指定appender使用
     2.class: appender的具体实现类。常用的有 ConsoleAppender、FileAppender、RollingFileAppender
     	(1).ConsoleAppender：向控制台输出日志内容的组件，只要定义好encoder节点就可以使用。
     	(2).RollingFileAppender：向文件输出日志内容的组件，同时可以配置日志文件滚动策略，在日志达到一定条件后生成一个新的日志文件
    	(3).FileAppender：向文件输出日志内容的组件，用法也很简单，不过由于没有日志滚动策略，一般很少使用
		-->
    <appender name="consoleLog" class="ch.qos.logback.core.ConsoleAppender">
        <!--展示格式 layout-->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %highlight(%-5level) %cyan(%logger{50} %L -)
                %highlight(%msg%n)
            </pattern>
        </layout>
    </appender>
    </springProfile>
    <!-- prod，default环境.springProfile 不同配置文件可以不同的日志输出方式 -->
    <springProfile name="prod,default">
<!-- 1.RollingFileAppender：向文件输出日志内容的组件，同时可以配置日志文件滚动策略，在日志达到一定条件后生成一个新的日志文件
 		 2.encoder:对记录事件进行格式化
		 3.rollingPolicy:当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名。
		 4.triggeringPolicy:告知 RollingFileAppender 合适激活滚动
		 5.当为true时，不支持FixedWindowRollingPolicy。支持TimeBasedRollingPolicy，但是有两个限制，1不支持也不允许文件压缩，2不能设置file属性，必须留空
-->
        <appender name="fileInfoLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <!--filter节点:
						 如果只是想要 Info 级别的日志，只是过滤 info 还是会输出 Error 日志，因为 Error 的级别高，
            所以我们使用下面的策略，可以避免输出 Error 的日志-->
            <filter class="ch.qos.logback.classic.filter.LevelFilter">
                <!--过滤 Error-->
                <level>ERROR</level>
                <!--匹配到就禁止-->
                <onMatch>DENY</onMatch>
                <!--没有匹配到就允许-->
                <onMismatch>ACCEPT</onMismatch>
            </filter>
            <!--日志名称，如果没有File 属性，那么只会使用FileNamePattern的文件路径规则
            如果同时有<File>和<FileNamePattern>，那么当天日志是<File>，明天会自动把今天
            的日志改名为今天的日期。即，<File> 的日志都是当天的。
            -->
            <File>${logback.logPath}/${logback.appname}_info.log</File>
<!--rollingPolicy:
TimeBasedRollingPolicy: 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责触发滚动。有以下子节点：

1.fileNamePattern：必要节点，包含文件名及“%d”转换符， “%d”可以包含一个		  java.text.SimpleDateFormat指定的时间格式，如：%d{yyyy-MM}。如果直接使用 %d，默认格式是 yyyy-MM-dd。 RollingFileAppender 的file字节点可有可无，通过设置file，可以为活动文件和归档文件指定不同位置，当前日志总是记录到file指定的文件（活动文件），活动文件的名字不会改变；如果没设置file，活动文件的名字会根据fileNamePattern 的值，每隔一段时间改变一次。“/”或者“\”会被当做目录分隔符。

2. maxHistory:可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件。假设设置每个月滚动，且 <maxHistory>是6，则只保存最近6个月的文件，删除之前的旧文件。注意，删除旧文件是，那些为了归档而创建的目录也会被删除。

3.FixedWindowRollingPolicy： 根据固定窗口算法重命名文件的滚动策略。有以下子节点：
	(1)minIndex:窗口索引最小值
	(2)maxIndex:窗口索引最大值，当用户指定的窗口过大时，会自动将窗口设置为12。
	(3)fileNamePattern :必须包含“%i”例如，假设最小值和最大值分别为1和2，命名模式为 mylog%i.log,会产生归档文件mylog1.log和mylog2.log。还可以指定文件压缩选项，例如，mylog%i.log.gz 或者 没有log%i.log.zip

4.triggeringPolicy:
	(1)SizeBasedTriggeringPolicy： 查看当前活动文件的大小，如果超过指定大小会告知 RollingFileAppender 触发当前活动文件滚动。只有一个节点:
	(2)maxFileSize:这是活动文件的大小，默认值是10MB。
 -->
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <!--文件路径,定义了日志的切分方式——把每一天的日志归档到一个文件中,以防止日志填满整个磁盘空间.
                滚动时产生的文件的存放位置及文件名称 %d{yyyy-MM-dd}：按天进行日志滚动%i：
                当文件大小超过maxFileSize时，按照i进行文件滚动-->
                <FileNamePattern>${logback.logPath}/${logback.appname}_info.%d{yyyy-MM-dd}-%i.log</FileNamePattern>
                <!--只保留最近3天的日志-->
                <maxHistory>3</maxHistory>
                <!--用来指定日志文件的上限大小，那么到了这个值，就会删除旧的日志-->
                <totalSizeCap>1GB</totalSizeCap>
                <!--单个日志文件大小到达 50mb 时触发滚动-->
                <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                    <maxFileSize>50MB</maxFileSize>
                </timeBasedFileNamingAndTriggeringPolicy>
            </rollingPolicy>
            <!--日志输出编码格式化-->
            <encoder>
              	<!-- 使用 UTF-8 -->
                <charset>UTF-8</charset>
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ${logback.appname} [%thread] %-5level %logger{50} %L - %msg%n
                </pattern>
            </encoder>
        </appender>
        <appender name="fileErrorLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <!--如果只是想要 Error 级别的日志，那么需要过滤一下，默认是 info 级别的，ThresholdFilter-->
            <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
                <level>ERROR</level>
            </filter>
            <!--日志名称，如果没有File 属性，那么只会使用FileNamePattern的文件路径规则
            如果同时有<File>和<FileNamePattern>，那么当天日志是<File>，明天会自动把今天
            的日志改名为今天的日期。即，<File> 的日志都是当天的。
            -->
            <File>${logback.logPath}/${logback.appname}_error.log</File>
            <!--滚动策略，按照时间滚动 TimeBasedRollingPolicy-->
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <!--文件路径,定义了日志的切分方式——把每一天的日志归档到一个文件中,以防止日志填满整个磁盘空间-->
                <FileNamePattern>${logback.log${logback.appname}graphy_error.%d{yyyy-MM-dd}-%i.log</FileNamePattern>
                <!--只保留最近3天的日志-->
                <maxHistory>3</maxHistory>
                <!--用来指定日志文件的上限大小，那么到了这个值，就会删除旧的日志-->
                <totalSizeCap>1GB</totalSizeCap>
                <!--日志文件每到达 50mb 就会触发切割 -->
                <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                    <maxFileSize>50MB</maxFileSize>
                </timeBasedFileNamingAndTriggeringPolicy>
            </rollingPolicy>
            <!--日志输出编码格式化-->
            <encoder>
                <charset>UTF-8</charset>
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} %L - %msg%n</pattern>
            </encoder>
        </appender>
<!-- logger有三个属性:name,level,addtivity
1.name: 全限定类名或包的相对路径,如果是包,则包下的所有类都会应用同一套设定,如果是类名,则该类会应用配置
2.level: 用来设置打印级别:
TRACE,DEBUG,INFO,WARN,ERROR,ALL,OFF还有一个特殊值INHERITED代表强制执行上级的级别。 
日志级别从左往右是从高到低
如果未设置此属性，那么当前logger将会继承上级的级别。
3.additivity:是否向上级logger传递打印信息。默认是true。
	(1)当 additivity 为 false 且 logger 中配置了 appender-ref 时,logger 将会在方法运行时打印日志
	(2)当 additivity 为 true 且 logger 中配置 appender-ref 时,logger 会在方法运行时打印一次日志,并在上级logger打印一次日志
	(3)当 additivity 为 true 且 logger 中没关联 appender-ref 时,logger 会在把日志传递到上级logger,并在上级打印一次日志
4.root节点和logger节点其实都是表示Logger组件。个人觉的可以把他们之间的关系可以理解为父子关系，root是最顶层的logger，正常情况getLogger("name/class")没有找到对应logger的情况下，都是使用root节点配置的logger。-->
        <root level="WARN">
            <appender-ref ref="consoleLog"/>
            <appender-ref ref="fileInfoLog"/>
            <appender-ref ref="fileErrorLog"/>
        </root>
        <logger name="org.springframework" level="INFO" additivity="false">
            <!-- appender-ref 关联的 appender-->
            <appender-ref ref="consoleLog"/>
            <appender-ref ref="fileInfoLog"/>
            <appender-ref ref="fileErrorLog"/>
        </logger>
        <logger name="com.suntek.pciopen.choreography" level="DEBUG" additivity="false">
            <appender-ref ref="consoleLog"/>
            <appender-ref ref="fileInfoLog"/>
            <appender-ref ref="fileErrorLog"/>
        </logger>
    </springProfile>
    <!-- test，dev环境.不持久化日志文件，只输出到控制台 -->
    <springProfile name="test,dev">
     			  <root level="WARN">
                <appender-ref ref="consoleLog"/>
                <!--<appender-ref ref="fileInfoLog"/>-->
                <!--<appender-ref ref="fileErrorLog"/>-->
            </root>
            <logger name="org.springframework" level="INFO" additivity="false">
                <appender-ref ref="consoleLog"/>
            </logger>
            <logger name="com.suntek.pciopen.choreography" level="DEBUG" additivity="false">
                <appender-ref ref="consoleLog"/>
            </logger>
    </configuration>
```

## 二、 partten 转换符



| **c** {*length* }  **lo** {*length* }  **logger**{*length* } | 输出日志的logger名，可有一个整形参数，功能是缩短logger名，设置为0表示只输入logger最右边点符号之后的字符串。 Conversion specifier Logger name Result%loggermainPackage.sub.sample.BarmainPackage.sub.sample.Bar%logger{0}mainPackage.sub.sample.BarBar%logger{5}mainPackage.sub.sample.Barm.s.s.Bar%logger{10}mainPackage.sub.sample.Barm.s.s.Bar%logger{15}mainPackage.sub.sample.Barm.s.sample.Bar%logger{16}mainPackage.sub.sample.Barm.sub.sample.Bar%logger{26}mainPackage.sub.sample.BarmainPackage.sub.sample.Bar |
| ------------------------------------------------------------ | :----------------------------------------------------------- |
| **C** {*length* }  **class** {*length*}                      | 输出执行记录请求的调用者的全限定名。参数与上面的一样。尽量避免使用，除非执行速度不造成任何问题。 |
| **contextName** **cn**                                       | 输出上下文名称。                                             |
| **d** {*pattern* }  **date** {*pattern*}                     | 输出日志的打印日志，模式语法与`java.text.SimpleDateFormat` 兼容。 Conversion Pattern Result%d2006-10-20 14:06:49,812%date2006-10-20 14:06:49,812%date{ISO8601}2006-10-20 14:06:49,812%date{HH:mm:ss.SSS}14:06:49.812%date{dd MMM yyyy ;HH:mm:ss.SSS}20 oct. 2006;14:06:49.812 |
| **F / file**                                                 | 输出执行记录请求的java源文件名。尽量避免使用，除非执行速度不造成任何问题。 |
| **caller{depth}****caller{depth, evaluator-1, ... evaluator-n}** | 输出生成日志的调用者的位置信息，整数选项表示输出信息深度。例如， **%caller{2}**   输出为：`0    [main] DEBUG - logging statement  Caller+0   at mainPackage.sub.sample.Bar.sampleMethodName(Bar.java:22) Caller+1   at mainPackage.sub.sample.Bar.createLoggingRequest(Bar.java:17)`例如， **%caller{3}**   输出为：`16   [main] DEBUG - logging statement  Caller+0   at mainPackage.sub.sample.Bar.sampleMethodName(Bar.java:22) Caller+1   at mainPackage.sub.sample.Bar.createLoggingRequest(Bar.java:17) Caller+2   at mainPackage.ConfigTester.main(ConfigTester.java:38)` |
| **L / line**                                                 | 输出执行日志请求的行号。尽量避免使用，除非执行速度不造成任何问题。 |
| **m / msg / message**                                        | 输出应用程序提供的信息。                                     |
| **M / method**                                               | 输出执行日志请求的方法名。尽量避免使用，除非执行速度不造成任何问题。 |
| **n**                                                        | 输出平台先关的分行符“\n”或者“\r\n”。                         |
| **p / le / level**                                           | 输出日志级别。                                               |
| **r / relative**                                             | 输出从程序启动到创建日志记录的时间，单位是毫秒               |
| **t / thread**                                               | 输出产生日志的线程名。                                       |
| **replace(p ){r, t}**                                        | **p** 为日志内容，**r** 是正则表达式，将**p** 中符合**r** 的内容替换为**t** 。例如， "%replace(%msg){'\s', ''}" |