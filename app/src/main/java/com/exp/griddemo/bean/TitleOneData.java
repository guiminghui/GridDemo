package com.exp.griddemo.bean;

/**
 * 作者: gmh by Administrator on 2019/5/29.
 * 邮箱:gmh.com@qq.com
 */
public class TitleOneData extends DataModel {
    private String detailsTitle;

    public TitleOneData(String detailsTitle) {
        this.detailsTitle = detailsTitle;
    }

    public String getDetailsTitle() {
        return detailsTitle;
    }

    public void setDetailsTitle(String detailsTitle) {
        this.detailsTitle = detailsTitle;
    }
}
