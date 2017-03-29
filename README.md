# mavn 使用reportng

[Better looking HTML test reports for TestNG with ReportNG – Maven guide](https://solidsoft.wordpress.com/2011/01/23/better-looking-html-test-reports-for-testng-with-reportng-maven-guide/)
First, the additional dependency has to be added to pom.xml:
```
<dependencies>
    <dependency>
        <groupId>org.uncommons</groupId>
        <artifactId>reportng</artifactId>
        <version>1.1.2</version>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.testng</groupId>
                <artifactId>testng</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    (...)
</dependencies>
```
Usually in our project a newer TestNG version is used, so that ReportNG dependency should be excluded.  

Next, Surefire plugin has to be configured:
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.5</version>
            <configuration>
                <properties>
                    <property>
                        <name>usedefaultlisteners</name>
                        <value>false</value>
                    </property>
                    <property>
                        <name>listener</name>
                        <value>org.uncommons.reportng.HTMLReporter, org.uncommons.reportng.JUnitXMLReporter</value>
                    </property>
                </properties>
                <workingDirectory>target/</workingDirectory>
            </configuration>
        </plugin>
        (...)
    </plugins>
</build>
```

ReportNG uses two reporters pluggable into TestNG. JUnitXMLReporter generates XML summarize of running tests. It’s used for tools (like CI server). HTMLReporter creates human readable HTML report. Default TestNG listeners should be disabled.  


After a test run I added also a workingDirectory property which causes that velocity.log (file created by Velocity engine used internally by ReportNG) is placed in a target instead of main project directory (and therefore it is deleted by the “mvn clean” command).  

One more thing. Unfortunately ReportNG jar isn’t available in Maven Central Repository, so it could be required to add java.net repository in your settings.xml.
```
<repositories>
    <repository>
        <id>java-net</id>
        <url>http://download.java.net/maven/2</url>
    </repository>
    (...)
</repositories>
```
That’s all. Now “mvn clean test” should generate a nice looking HTML report for lots of tests covering our project :).  


Update 2012-08-23. This post was written with TestNG 5.x in mind. With TestNG 6.0+ you can meet a problem with “ClassNotFoundException: com.google.inject.Module” exception. In that case Guice dependency needs to be added. Thanks to Alexander Schikora for pointing it out.
```
<dependency>
    <groupId>com.google.inject</groupId>
    <artifactId>guice</artifactId>
    <version>3.0</version>
    <scope>test</scope>
</dependency>
```

# tech
TestNG_Example uses a number of open source projects to work properly:
	* [Selenium WebDriver] - web browser driver
	* [TestNG] - testing framework (project contains testng.xml file config)
	* [Junit] - testing framework
	* [Apache Maven] - build automation tool
	* [ReportNG] - report plugin for the TestNG
	
	
### First type in console:
mvn clean test
Than:
mvn test
# 注意事项
Spring's @ContextConfiguration test configuration in a testng class is creating beans only once per test class, not per test
```
@ContextConfiguration(locations = {"classpath:test.xml"}) public class ConfigTest extends AbstractTestNGSpringContextTests implements ITest  {     @Autowired     MyUser myUser;      @Test(testName = "测试用户信息",description = "测试注入")     public void userInfo(){         myUser.setUuid("testid");         System.out.println(""+myUser.toString());     }      public String getTestName() {         return "注入";     } }

```
extends AbstractTestNGSpringContextTests' solved my problem


locations 可以导入多个xml

