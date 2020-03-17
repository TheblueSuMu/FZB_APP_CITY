package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.LinkmanBean;
import com.xcy.fzbcity.all.database.LinkmanNewBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class LinkmanNewAdapter extends RecyclerView.Adapter<LinkmanNewAdapter.ViewHolder> {
    protected Context mContext;
    protected List<LinkmanNewBean> mDatas;
    protected LayoutInflater mInflater;
    ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void itemClick(int position);
    }

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public LinkmanNewAdapter(Context mContext, List<LinkmanNewBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<LinkmanNewBean> getDatas() {
        return mDatas;
    }

    public LinkmanNewAdapter setDatas(List<LinkmanNewBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public LinkmanNewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_linkman_new, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.item_linkman_name.setText(mDatas.get(position).getCity());
        holder.item_linkman_phone.setText(mDatas.get(position).getClientPhone());

        if (mDatas.get(position).getWay().equals("2")) {
            holder.item_linkman_new_tk.setVisibility(View.VISIBLE);
        }else {
            holder.item_linkman_new_tk.setVisibility(View.GONE);
        }

        if (mDatas.get(position).getGender().equals("男")) {
            holder.item_linkman_new_sex1.setVisibility(View.VISIBLE);
        } else if (mDatas.get(position).getGender().equals("女")) {
            holder.item_linkman_new_sex2.setVisibility(View.VISIBLE);
        }else {
            holder.item_linkman_new_sex1.setVisibility(View.GONE);
            holder.item_linkman_new_sex2.setVisibility(View.GONE);
        }
        Glide.with(holder.itemView.getContext()).load(FinalContents.getImageUrl() + mDatas.get(position).getCustomerimg()).into(holder.item_linkman_new_img);

//        Log.i("数据对比","客户名"+mDatas.get(position).getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClick.itemClick(position);
                Log.i("数据对比", "客户名" + mDatas.get(position).getCity());
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
        RoundedImageView item_linkman_new_img;
        ImageView item_linkman_new_sex1;
        ImageView item_linkman_new_sex2;
        ImageView item_linkman_new_tk;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            item_linkman_name = itemView.findViewById(R.id.item_linkman_new_name);
            item_linkman_phone = itemView.findViewById(R.id.item_linkman_new_phone);
            item_linkman_new_img = itemView.findViewById(R.id.item_linkman_new_img);
            item_linkman_new_sex1 = itemView.findViewById(R.id.item_linkman_new_sex1);
            item_linkman_new_sex2 = itemView.findViewById(R.id.item_linkman_new_sex2);
            item_linkman_new_tk = itemView.findViewById(R.id.item_linkman_new_tk);
            content = itemView.findViewById(R.id.content);
        }
    }
}
