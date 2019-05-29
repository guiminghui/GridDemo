package com.exp.griddemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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
public class GRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_0 = 0;
    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;

    private List<DataModel> infoList;
    private Context context;
    private LinearLayout curVisibleLayout;//当前可见的layout
    private int curPosition = -1;
    private GInfo cruGInfo;//当前点击的
    private int lastPosition = -1;//上次点击的 只有在同一行用得到


    public GRecyclerAdapter(List<DataModel> infoList, Context context) {
        this.infoList = infoList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return infoList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_0:
                View view0 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_0, viewGroup, false);
                return new TitleViewHolder(view0);
            case TYPE_1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_1, viewGroup, false);
                return new Title1ViewHolder(view1);
            case TYPE_2:
                View view2 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_2, viewGroup, false);
                return new GViewHolder(view2);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        if (viewHolder instanceof GViewHolder) {
            final GViewHolder gViewHolder = (GViewHolder) viewHolder;
            RecyclerView itemRecyclerView = gViewHolder.itemRecyclerView;
            itemRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
            itemRecyclerView.setLayoutManager(new GridLayoutManager(context, MainActivity.EachColumnNumber));
            GGridRecyclerAdapter adapter = new GGridRecyclerAdapter(((DatailData) (infoList.get(i))).getInfoList(), context);
            itemRecyclerView.setAdapter(adapter);
            gViewHolder.btn_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(context, TowActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cruGInfo", cruGInfo);
                    intent.putExtra("bundle", bundle);
                    context.startActivity(intent);
                }
            });
            adapter.setOnItemClick(new OnItemClick() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(GInfo gInfo, int position) {
                    cruGInfo = gInfo;//赋值备用
                    if (curPosition == i) { //是同一行的 直接更新数据
                        if (position == lastPosition) {
                            if (gViewHolder.detailsLayout.getVisibility() == View.VISIBLE) {
                                gViewHolder.detailsLayout.setVisibility(View.GONE);
                            } else {
                                gViewHolder.detailsLayout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            gViewHolder.detailsLayout.setVisibility(View.VISIBLE);
                        }
                        curVisibleLayout = gViewHolder.detailsLayout;
                        gViewHolder.datailsText.setText(gInfo.getName() + "---" + gInfo.getAge() + "---" + gInfo.getSex());
                        lastPosition = position;
                    } else {
                        if (curVisibleLayout != null && curVisibleLayout.getVisibility() == View.VISIBLE) {
                            curVisibleLayout.setVisibility(View.GONE);
                        }
                        gViewHolder.detailsLayout.setVisibility(View.VISIBLE);
                        curVisibleLayout = gViewHolder.detailsLayout;
                        gViewHolder.datailsText.setText(gInfo.getName() + "---" + gInfo.getAge() + "---" + gInfo.getSex());
                    }
                    curPosition = i;
                }
            });
        } else if (viewHolder instanceof TitleViewHolder) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) viewHolder;
            titleViewHolder.titleText.setText(((TitleData) infoList.get(i)).getTitle());
        } else if (viewHolder instanceof Title1ViewHolder) {
            Title1ViewHolder title1ViewHolder = (Title1ViewHolder) viewHolder;
            title1ViewHolder.titleText.setText(((TitleOneData) infoList.get(i)).getDetailsTitle());
        }
    }


    @Override
    public int getItemCount() {
        return infoList.size();
//        int size = infoList.size();
//        return size % MainActivity.EachColumnNumber == 0 ? size / MainActivity.EachColumnNumber : size / MainActivity.EachColumnNumber + 1;
    }

    class GViewHolder extends RecyclerView.ViewHolder {
        RecyclerView itemRecyclerView;
        LinearLayout detailsLayout;
        TextView datailsText;
        Button btn_item;

        GViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRecyclerView = itemView.findViewById(R.id.item_recycler);
            detailsLayout = itemView.findViewById(R.id.details_layout);
            datailsText = itemView.findViewById(R.id.datails_text);
            btn_item = itemView.findViewById(R.id.btn_item);
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;

        TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title_text);
        }
    }

    class Title1ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;

        Title1ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title1_text);
        }
    }
}
