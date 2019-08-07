# 概述

项目示例

# plugin-git
利用插件git-commit-id-plugin来获取项目提交版本号\
1.在\<build>中添加如下内容
  ````
    <resources>
    	<resource>
    		<directory>src/main/resources</directory>
    		<filtering>true</filtering>
    	</resource>
    </resources>
  ````
 2.在<build><plugins>内添加git-commit-id-plugin插件，自动生成version.txt文件
 ````
   <plugin>
             <groupId>pl.project13.maven</groupId>
             <artifactId>git-commit-id-plugin</artifactId>
             <executions>
                 <execution>
                     <goals>
                         <goal>revision</goal>
                     </goals>
                 </execution>
             </executions>
             <configuration>
                 <!--,构建过程中,是否打印详细信息;默认值:false;-->
                 <verbose>false</verbose>
                 <!--日期格式;默认值:dd.MM.yyyy '@' HH:mm:ss z;，用于格式化"git.build.time"和"git.commit.time"-->
                 <dateFormat>yyyy-MM-dd'T'HH:mm:ssZ</dateFormat>
                 <!--git版本，默认7个字符-->
                 <abbrevLength>8</abbrevLength>
                 <generateGitPropertiesFile>false</generateGitPropertiesFile>
                 <generateGitPropertiesFilename>${project.build.outputDirectory}/version.txt</generateGitPropertiesFilename>
                 <format>txt</format>
                 <!--<injectAllReactorProjects>true</injectAllReactorProjects>-->
                 <includeOnlyProperties>git.commit.id.abbrev,git.build.version</includeOnlyProperties>
             </configuration>
         </plugin>

 ````
 3.在<build><plugins>内添加git-commit-id-plugin插件，手动创建version.txt配置方式
 1）在src/main/resources/目录下创建version.txt,内容如下：
   ````
   version = ${project.version}.${git.commit.id.abbrev}
  ````
 2）若项目使用了spring-boot-starter-parent做项目版本管理,且版本后1.3之后，
    需要替换resource.delimiter属性，需要在\<properties>中添加
     ```` 
      <resource.delimiter>${}</resource.delimiter>
    ````
    
 3）在\<build>中添加
   ````
     <resources>
     	<resource>
     		<directory>src/main/resources</directory>
     		<filtering>true</filtering>
     	</resource>
     </resources>
   ````
   用来过滤src/main/resources下的文件中的${}\
   4）在\<build>\<plugins>内添加git-commit-id-plugin插件配置
 ````
   <plugin>
             <groupId>pl.project13.maven</groupId>
             <artifactId>git-commit-id-plugin</artifactId>
             <executions>
                 <execution>
                     <goals>
                         <goal>revision</goal>
                     </goals>
                 </execution>
             </executions>
             <configuration>
                 <!--,构建过程中,是否打印详细信息;默认值:false;-->
                 <verbose>false</verbose>
                 <!--日期格式;默认值:dd.MM.yyyy '@' HH:mm:ss z;，用于格式化"git.build.time"和"git.commit.time"-->
                 <dateFormat>yyyy-MM-dd'T'HH:mm:ssZ</dateFormat>
                 <skipPoms></skipPoms>
                 <!--git版本，默认7个字符-->
                 <abbrevLength>8</abbrevLength>
                 <generateGitPropertiesFile>false</generateGitPropertiesFile>
 <!--                <generateGitPropertiesFilename>${project.build.outputDirectory}/version.txt</generateGitPropertiesFilename>-->
                 <format>txt</format>
                 <!--<injectAllReactorProjects>true</injectAllReactorProjects>-->
                 <includeOnlyProperties>git.commit.id.abbrev,git.build.version</includeOnlyProperties>
 <!--                <injectIntoSysProperties>true </injectIntoSysProperties>-->
             </configuration>
         </plugin>

 ````
备注：git-commit-id-plugin 其他配置信息
```
<plugin>	
    <groupId>pl.project13.maven</groupId>	
    <artifactId>git-commit-id-plugin</artifactId>	<
    version>2.1.5</version>	
    <executions>		
        <execution>			
            <goals>				
                <goal>revision</goal>			
            </goals>		
        </execution>	
    </executions>	
    <configuration>		
        <!--日期格式;默认值:dd.MM.yyyy '@' HH:mm:ss z;-->		
        <dateFormat>yyyyMMddHHmmss</dateFormat>		
        
        <!--,构建过程中,是否打印详细信息;默认值:false;-->		
        <verbose>true</verbose>		
        
        <!-- ".git"文件路径;默认值:${project.basedir}/.git; -->		
        <dotGitDirectory>${project.basedir}/.git</dotGitDirectory>		
       
        <!--若项目打包类型为pom,是否取消构建;默认值:true;-->		
        <skipPoms>false</skipPoms>		
        
        <!--是否生成"git.properties"文件;默认值:false;-->		
        <generateGitPropertiesFile>true</generateGitPropertiesFile>		
       
        <!--指定"git.properties"文件的存放路径(相对于${project.basedir}的一个路径);-->		
       
        <generateGitPropertiesFilename>git.properties</generateGitPropertiesFilename>		
        <!--".git"文件夹未找到时,构建是否失败;若设置true,则构建失败;若设置false,则跳过执行该目标;默认值:true;-->		
       
        <failOnNoGitDirectory>true</failOnNoGitDirectory> 		
        
        <!--git描述配置,可选;由JGit提供实现;-->		
        <gitDescribe>			
        
        <!--是否生成描述属性-->			
        <skip>false</skip>			
       
        <!--提交操作未发现tag时,仅打印提交操作ID,-->			
        <always>false</always>			
       
        <!--提交操作ID显式字符长度,最大值为:40;默认值:7;0代表特殊意义;-->			
        <abbrev>7</abbrev>			
      
        <!--构建触发时,代码有修改时(即"dirty state"),添加指定后缀;默认值:"";-->			
        <dirty>-dirty</dirty>			
       
        <!--always print using the "tag-commits_from_tag-g_commit_id-maybe_dirty" format, even if "on" a tag.The distance will always be 0 if you're "on" the tag.			-->			
        <forceLongFormat>false</forceLongFormat>		
        </gitDescribe>	</configuration>
    </plugin>
```


# maven-project

利用插件svn-revision-number-maven-plugin获取项目svn提交代码版本
## 打jar包获取svn提交代码版本号
1.在src/main/resources/目录下创建version.txt,内容如下：
````
version = ${project.version}.${svn.committedRevision}
````
2.若项目使用了spring-boot-starter-parent做项目版本管理,且版本后1.3之后，
需要替换resource.delimiter属性，需要在\<properties>中添加
 ```` 
  <resource.delimiter>${}</resource.delimiter>
````
3.在\<build>中添加
````
  <resources>
  	<resource>
  		<directory>src/main/resources</directory>
  		<filtering>true</filtering>
  	</resource>
  </resources>
````
4.在\<build>\<plugins>内添加相关插件
````
<plugin>
	<groupId>com.google.code.maven-svn-revision-number-plugin</groupId>
	<artifactId>svn-revision-number-maven-plugin</artifactId>
	<version>1.13</version>
	<configuration>
		<verbose>true</verbose>
		<entries>
			<entry>
				<prefix>svn</prefix>
				<depth>infinite</depth>
			</entry>
		</entries>
	</configuration>
	<executions>
		<execution>
			<id>revision</id>
			<phase>validate</phase>
			<goals>
				<goal>revision</goal>
			</goals>
		</execution>
	</executions>
	<dependencies>
		<dependency>
			<groupId>org.tmatesoft.svnkit</groupId>
			<artifactId>svnkit</artifactId>
			<version>1.8.14</version>
		</dependency>
	</dependencies>
</plugin>
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<version>2.6</version>
	<configuration>
		<excludes>
			<exclude>*.xml</exclude>
			<exclude>*.properties</exclude>
			<exclude>conf/*.*</exclude>
		</excludes>
		<archive>
			<manifestEntries>
				<Specification-Vendor>${vendor}</Specification-Vendor>
				<Implementation-Vendor>${vendor}</Implementation-Vendor>
				<Implementation-Title>${project.artifactId}</Implementation-Title>
				<Implementation-Version>${project.version}</Implementation-Version>
																<svn-revision>${svn.revision} </svn-revision>
				<jar-buildTime>${current.time}</jar-buildTime>
							<svn-committedRevision>${svn.committedRevision} </svn-committedRevision>
			</manifestEntries>
	
		</archive>
	</configuration>
	<executions>
		<execution>
			<goals>
				<goal>test-jar</goal>
			</goals>
		</execution>
	</executions>
</plugin>
````
5.打包后，查看version.txt文件

## 打war包获取svn提交代码版本号
1.方式和maven打jar包配置一样，唯一不同的是插件maven-jar-plugin改成maven-war-plugin，配置内容如下：
````
<plugin>
     <groupId>org.apache.maven.plugins</groupId>
     <artifactId>maven-war-plugin</artifactId>
     <version>3.0.0</version>
     <configuration>
         <webResources>
             <resource>
                 <directory>src/main/resources</directory>
                 <filtering>true</filtering>
                 <excludes>
                     <exclude>**/application-*.yml</exclude>
                 </excludes>
                 <targetPath>WEB-INF/classes</targetPath>
             </resource>
         </webResources>
         <archive>
             <manifestEntries>
                 <Specification-Vendor>${vendor}</Specification-Vendor>
                 <Implementation-Vendor>${vendor}</Implementation-Vendor>
                 <Implementation-Title>${project.artifactId}</Implementation-Title>
                 <Implementation-Version>${project.version}</Implementation-Version>
                 <svn-revision>${svn.revision} </svn-revision>
             </manifestEntries>
         </archive>
     </configuration>
 </plugin>
````