package com.exp.griddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 作者: gmh by Administrator on 2019/5/29.
 * 邮箱:gmh.com@qq.com
 */
public class TowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);
        GInfo info = (GInfo) getIntent().getBundleExtra("bundle").getSerializable("cruGInfo");
        ((TextView) findViewById(R.id.context_text)).setText(info.toString());
    }
}
