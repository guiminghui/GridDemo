package com.exp.griddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.exp.griddemo.bean.DataModel;
import com.exp.griddemo.bean.DatailData;
import com.exp.griddemo.bean.TitleData;
import com.exp.griddemo.bean.TitleOneData;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: gmh by Administrator on 2019/5/28.
 * 邮箱:gmh.com@qq.com
 */
public class MainActivity extends AppCompatActivity {
    public static final int EachColumnNumber = 3;
    private List<DataModel> infoList;
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
        TitleData titleData = new TitleData("title1");
        titleData.setType(0);
        TitleOneData titleOneData = new TitleOneData("title2");
        titleOneData.setType(1);
        GInfo info = new GInfo("张三", "男", 22);GInfo info1 = new GInfo("张四", "女", 24);GInfo info2 = new GInfo("张五", "男", 25);GInfo info3 = new GInfo("张六", "女", 21);GInfo info4 = new GInfo("张七", "男", 23);
        GInfo info5 = new GInfo("张八", "女", 22);GInfo info6 = new GInfo("张九", "男", 20);GInfo info7 = new GInfo("张十", "女", 22);GInfo info8 = new GInfo("张11", "男", 21);
        GInfo info9 = new GInfo("张11", "女", 21);GInfo info10 = new GInfo("张11", "男", 21);
        List<GInfo> gInfoList = new ArrayList<>();
        gInfoList.add(info);gInfoList.add(info1);gInfoList.add(info2);gInfoList.add(info3);gInfoList.add(info4);gInfoList.add(info5);gInfoList.add(info6);gInfoList.add(info7);
        gInfoList.add(info8);gInfoList.add(info9);gInfoList.add(info10);
        infoList.add(titleData);
        infoList.add(titleOneData);
        List<List<GInfo>> grouping = fixedGrouping2(gInfoList, EachColumnNumber);
        for (int i=0;i<grouping.size();i++){
            DatailData datailData=new DatailData(grouping.get(i));
            datailData.setType(2);
            infoList.add(datailData);
        }
        //多填充了一个数据
        infoList.add(titleData);
        infoList.add(titleOneData);
        List<List<GInfo>> grouping1 = fixedGrouping2(gInfoList, EachColumnNumber);
        for (int i=0;i<grouping1.size();i++){
            DatailData datailData=new DatailData(grouping1.get(i));
            datailData.setType(2);
            infoList.add(datailData);
        }
    }
    /**
     * 将一组数据固定分组，每组n个元素
     *
     * @param source 要分组的数据源
     * @param n      每组n个元素
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> fixedGrouping2(List<T> source, int n) {
        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset = null;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset = null;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
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
