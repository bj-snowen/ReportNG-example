package com.vdrips.test.reportng.config;

/**
 * Created by baixf on 2017/3/29.
 */
public class MyUser {
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
