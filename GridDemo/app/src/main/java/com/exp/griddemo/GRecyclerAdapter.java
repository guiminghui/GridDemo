package com.exp.griddemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: gmh by Administrator on 2019/5/28.
 * 邮箱:gmh.com@qq.com
 */
public class GRecyclerAdapter extends RecyclerView.Adapter<GRecyclerAdapter.GViewHolder> {
    private List<GInfo> infoList;
    private Context context;
    private LinearLayout curVisibleLayout;//当前可见的layout
    private int curPosition = -1;


    public GRecyclerAdapter(List<GInfo> infoList, Context context) {
        this.infoList = infoList;
        this.context = context;
    }

    @NonNull
    @Override
    public GViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new GViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GViewHolder gViewHolder, final int i) {
        RecyclerView itemRecyclerView = gViewHolder.itemRecyclerView;
        itemRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
        itemRecyclerView.setLayoutManager(new GridLayoutManager(context, MainActivity.EachColumnNumber));
        GGridRecyclerAdapter adapter = new GGridRecyclerAdapter(fixedGrouping2(infoList, MainActivity.EachColumnNumber).get(i), context);
        itemRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new OnItemClick() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(GInfo gInfo) {
                if (curPosition == i) { //是同一行的 直接更新数据
                    gViewHolder.detailsLayout.setVisibility(View.VISIBLE);
                    curVisibleLayout = gViewHolder.detailsLayout;
                    gViewHolder.datailsText.setText(gInfo.getName() + "---" + gInfo.getAge() + "---" + gInfo.getSex());
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
    public int getItemCount() {
        int size = infoList.size();
        return size % MainActivity.EachColumnNumber == 0 ? size / MainActivity.EachColumnNumber : size / MainActivity.EachColumnNumber + 1;
    }

    class GViewHolder extends RecyclerView.ViewHolder {
        RecyclerView itemRecyclerView;
        LinearLayout detailsLayout;
        TextView datailsText;

        GViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRecyclerView = itemView.findViewById(R.id.item_recycler);
            detailsLayout = itemView.findViewById(R.id.details_layout);
            datailsText = itemView.findViewById(R.id.datails_text);
        }
    }
}
