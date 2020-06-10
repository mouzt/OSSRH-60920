package com.meituan.mzt.domain;

/**
 * 用户信息
 */
public class User {
    private String misd;

    private String name;

    public User(String misd, String name) {
        this.misd = misd;
        this.name = name;
    }

    public String getMisId() {
        return misd;
    }

    public void setMisd(String misId) {
        this.misd = misId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
