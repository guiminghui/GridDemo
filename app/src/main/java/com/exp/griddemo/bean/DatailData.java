package com.exp.griddemo.bean;

import com.exp.griddemo.GInfo;

import java.util.List;

/**
 * 作者: gmh by Administrator on 2019/5/29.
 * 邮箱:gmh.com@qq.com
 */
public class DatailData extends DataModel {
    private List<GInfo> infoList;

    public DatailData(List<GInfo> infoList) {
        this.infoList = infoList;
    }

    public List<GInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<GInfo> infoList) {
        this.infoList = infoList;
    }
}
