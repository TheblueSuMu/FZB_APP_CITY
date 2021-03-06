package com.xcy.fzbcity.all.myim.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.HotBean;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.all.view.ProjectDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

public class NewRecyclerAdapter extends RecyclerView.Adapter<NewRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<HotBean.DataBean.RowsBean> beanList;
    private String project = "0";
    private OnItemClickLisenter onItemClickLisenter;

    public void setProject(String project) {
        this.project = project;
    }

    public interface OnItemClickLisenter {
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener) {
        this.onItemClickLisenter = onItemClickListener;
    }


    public NewRecyclerAdapter(List<HotBean.DataBean.RowsBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modulebroker_fragment_recycler_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(FinalContents.getImageUrl() + beanList.get(position).getListPageCover()).into(holder.imageAvatar);
        holder.nameText.setText("[" + beanList.get(position).getArea() + "]" + beanList.get(position).getProjectName());

        String ids = beanList.get(position).getProductFeature();//从pd里取出字符串
        List tags = Arrays.asList(ids.split(","));//根据逗号分隔转化为list
        List tag = new ArrayList();
        if (tags.size() > 4) {
            for (int i = 0; i < 4; i++) {
                tag.add(tags.get(i));
            }
        } else {
            for (int i = 0; i < tags.size(); i++) {
                tag.add(tags.get(i));
            }
        }

        Log.i("2222", "标签" + beanList.get(position).getProductFeature());

        if (beanList.get(position).getProductFeature().equals("")) {
            holder.tagView.setVisibility(View.GONE);
        } else {
            holder.tagView.setVisibility(View.VISIBLE);
            holder.tagView.setTheme(ColorFactory.NONE);
            holder.tagView.setTags(tag);
        }

        holder.group_booking.setVisibility(View.GONE);

        holder.chick.setText(Html.fromHtml("报备(" + "<font color='#A52A2A'>" + beanList.get(position).getReportAmount() + "</font>" + ")"));
        holder.attention.setText(Html.fromHtml("关注(" + "<font color='#A52A2A'>" + beanList.get(position).getBrowseNum() + "</font>" + ")"));
        holder.collect.setText(Html.fromHtml("收藏(" + "<font color='#A52A2A'>" + beanList.get(position).getCollectionNum() + "</font>" + ")"));
        holder.transmit.setText(Html.fromHtml("转发(" + "<font color='#A52A2A'>" + beanList.get(position).getForwardingAmount() + "</font>" + ")"));


        holder.price.setText(beanList.get(position).getProductUnitPrice());
        holder.price_money.setText(beanList.get(position).getMonetaryUnit());
        Log.i("列表", "城市数据1" + beanList.get(position).getProductUnitPrice());
        Log.i("列表", "城市数据2" + beanList.get(position).getMonetaryUnit());
        if (beanList.get(position).getProductUnitPrice().equals("") || beanList.get(position).getProductUnitPrice().equals("0")) {
            holder.price.setVisibility(View.GONE);
            holder.price_money.setVisibility(View.GONE);
            holder.item_view.setVisibility(View.GONE);
        } else {
            holder.price.setVisibility(View.VISIBLE);
            holder.price_money.setVisibility(View.VISIBLE);
            holder.item_view.setVisibility(View.VISIBLE);

        }


        holder.square.setText(beanList.get(position).getAreaInterval());
        holder.commission.setText("佣金：" + beanList.get(position).getCommission());
        holder.second.setText("秒结：" + beanList.get(position).getSecondPay());
        FinalContents.setProjectID(beanList.get(position).getProjectId());
        Log.i("MyCL", "项目id：" + beanList.get(position).getProjectId());


        if (FinalContents.getIdentity().equals("63")) {
            holder.modulebroke_ll.setVisibility(View.GONE);
        } else {
            if (SharItOff.getShar().equals("显")) {
                holder.modulebroke_ll.setVisibility(View.VISIBLE);
            } else if (SharItOff.getShar().equals("隐")) {
                holder.modulebroke_ll.setVisibility(View.GONE);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickLisenter.onItemClick(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return beanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageAvatar;
        TextView nameText;
        TagContainerLayout tagView;
        TextView chick;
        TextView attention;
        TextView collect;
        TextView transmit;
        TextView price;
        TextView price_money;
        TextView square;
        TextView commission;
        TextView second;
        LinearLayout modulebroke_ll;
        TextView group_booking;
        View item_view;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = itemView.findViewById(R.id.ImageView_S);
            nameText = (TextView) itemView.findViewById(R.id.TextViewName);
            tagView = itemView.findViewById(R.id.tagView);
            chick = (TextView) itemView.findViewById(R.id.chick);
            attention = (TextView) itemView.findViewById(R.id.attention);
            collect = (TextView) itemView.findViewById(R.id.collect);
            transmit = (TextView) itemView.findViewById(R.id.transmit);
            price_money = (TextView) itemView.findViewById(R.id.price_money);
            price = (TextView) itemView.findViewById(R.id.price);
            square = (TextView) itemView.findViewById(R.id.square);
            commission = (TextView) itemView.findViewById(R.id.commission);
            second = (TextView) itemView.findViewById(R.id.second);
            modulebroke_ll = (LinearLayout) itemView.findViewById(R.id.modulebroke_ll);
            group_booking = itemView.findViewById(R.id.group_booking_item);
            item_view = itemView.findViewById(R.id.item_view);
        }
    }
}
