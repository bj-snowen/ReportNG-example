package com.vdrips.test.reportng.normal;

import org.testng.annotations.Test;

/**
 * Created by baixf on 2017/3/29.
 */
@Test
public class NormalTest {

    @Test(testName = "测试setUp",description = "setUp")
    public void setUp(){
        System.out.println("set up");
    }

    @Test(testName = "测试tearDown",description = "tearDown")
    public void tearDown(){
        System.out.println("tear down");
    }
}
