package com.exp.griddemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 作者: gmh by Administrator on 2019/5/28.
 * 邮箱:gmh.com@qq.com
 */
public class GGridRecyclerAdapter extends RecyclerView.Adapter<GGridRecyclerAdapter.VHolder> {
    private List<GInfo> infoList;
    private Context context;
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public GGridRecyclerAdapter(List<GInfo> infoList, Context context) {
        this.infoList = infoList;
        this.context = context;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridview_item, viewGroup, false);
        return new VHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, @SuppressLint("RecyclerView") final int i) {
        if (infoList.get(i).getSex().equals("男")) {
            vHolder.all_layout.setBackgroundResource(R.color.manBgColor);
        } else {
            vHolder.all_layout.setBackgroundResource(R.color.womanBgColor);
        }
        vHolder.name.setText(infoList.get(i).getName());
        vHolder.sex.setText(infoList.get(i).getSex());
        vHolder.age.setText(infoList.get(i).getAge() + "");
        vHolder.all_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onClick(infoList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView sex;
        TextView age;
        RelativeLayout all_layout;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            all_layout = itemView.findViewById(R.id.all_layout);
            name = itemView.findViewById(R.id.name);
            sex = itemView.findViewById(R.id.sex);
            age = itemView.findViewById(R.id.age);
        }
    }
}
