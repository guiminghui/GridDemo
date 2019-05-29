package com.exp.griddemo;

import java.io.Serializable;

/**
 * 作者: gmh by Administrator on 2019/5/28.
 * 邮箱:gmh.com@qq.com
 */
public class GInfo implements Serializable {
    private String name;
    private String sex;
    private int age;

    public GInfo(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"sex\":\"")
                .append(sex).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append('}');
        return sb.toString();
    }
}
