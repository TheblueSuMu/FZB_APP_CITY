package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.database.LinkmanBean;

import java.util.List;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class LinkmanAdapter extends RecyclerView.Adapter<LinkmanAdapter.ViewHolder> {
    protected Context mContext;
    protected List<LinkmanBean> mDatas;
    protected LayoutInflater mInflater;
    ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void itemClick(int position);
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public LinkmanAdapter(Context mContext, List<LinkmanBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<LinkmanBean> getDatas() {
        return mDatas;
    }

    public LinkmanAdapter setDatas(List<LinkmanBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public LinkmanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_linkman, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.item_linkman_name.setText(mDatas.get(position).getCity());
        holder.item_linkman_phone.setText(mDatas.get(position).getClientPhone());
//        Log.i("数据对比","客户名"+mDatas.get(position).getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClick.itemClick(position);
                Log.i("数据对比","客户名"+mDatas.get(position).getCity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_linkman_name;
        TextView item_linkman_phone;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            item_linkman_name =  itemView.findViewById(R.id.item_linkman_name);
            item_linkman_phone =  itemView.findViewById(R.id.item_linkman_phone);
            content = itemView.findViewById(R.id.content);
        }
    }
}
