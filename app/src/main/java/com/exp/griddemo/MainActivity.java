package com.exp.griddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: gmh by Administrator on 2019/5/28.
 * 邮箱:gmh.com@qq.com
 */
public class MainActivity extends AppCompatActivity {
    public static final int EachColumnNumber = 3;
    private List<GInfo> infoList;
    @BindView(R.id.recycler_view_main)
    RecyclerView mRecyclerView;
    GRecyclerAdapter mGRecyclerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initdata();
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mGRecyclerAdapter = new GRecyclerAdapter(infoList, this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGRecyclerAdapter);
    }

    private void initdata() {
        infoList = new ArrayList<>();
        GInfo info = new GInfo("张三", "男", 22);
        GInfo info1 = new GInfo("张四", "女", 24);
        GInfo info2 = new GInfo("张五", "男", 25);
        GInfo info3 = new GInfo("张六", "女", 21);
        GInfo info4 = new GInfo("张七", "男", 23);
        GInfo info5 = new GInfo("张八", "女", 22);
        GInfo info6 = new GInfo("张九", "男", 20);
        GInfo info7 = new GInfo("张十", "女", 22);
        GInfo info8 = new GInfo("张11", "男", 21);
        GInfo info9 = new GInfo("张11", "女", 21);
        GInfo info10 = new GInfo("张11", "男", 21);
        infoList.add(info);
        infoList.add(info1);
        infoList.add(info2);
        infoList.add(info3);
        infoList.add(info4);
        infoList.add(info5);
        infoList.add(info6);
        infoList.add(info7);
        infoList.add(info8);
        infoList.add(info9);
        infoList.add(info10);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
