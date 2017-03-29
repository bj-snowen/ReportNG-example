package com.vdrips.test.reportng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITest;
import org.testng.annotations.Test;

/**
 * Created by baixf on 2017/3/29.
 */
@ContextConfiguration(locations = {"classpath:test.xml"})
public class ConfigTest extends AbstractTestNGSpringContextTests implements ITest  {

    @Autowired
    MyUser myUser;

    @Test(testName = "测试用户信息",description = "测试注入")
    public void userInfo(){
        myUser.setUuid("testid");
        System.out.println(""+myUser.toString());
    }

    public String getTestName() {
        return "注入";
    }
}
