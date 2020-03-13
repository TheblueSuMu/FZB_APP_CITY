package com.xcy.fzbcity.project_side.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.SideHomeBean;
import com.xcy.fzbcity.all.persente.SharItOff;
import com.xcy.fzbcity.project_side.view.DetailsTheProjectEndActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<SideHomeBean.DataBean.RowsBean> beanList;
    private String project = "";
    private OnItemClickLisenter onItemClickLisenter;
    private View view;

    public void setProject(String project) {
        this.project = project;
    }

    public interface OnItemClickLisenter {
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener) {
        this.onItemClickLisenter = onItemClickListener;
    }


    public HomeRecyclerAdapter(List<SideHomeBean.DataBean.RowsBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.side_fragment_recycler_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + beanList.get(position).getListPageCover()).into(holder.imageAvatar);
        holder.nameText.setText("[" + beanList.get(position).getArea() + "]" + beanList.get(position).getProjectName());
        holder.item_project_location.setText("项目地址：" + beanList.get(position).getDetailAddress());

        if (beanList.get(position).getOnlineState().equals("0")) {
            holder.item_OnlineState.setVisibility(View.VISIBLE);
        } else if (beanList.get(position).getOnlineState().equals("1")) {
            holder.item_OnlineState.setVisibility(View.GONE);
        }

        if (beanList.get(position).getIsgroup().equals("1")) {
            holder.group_booking.setVisibility(View.VISIBLE);
            holder.group_booking.setText(beanList.get(position).getGroupNum() + "个团火热报名中...");
        } else {
            holder.group_booking.setVisibility(View.GONE);
        }

        holder.chick.setText(Html.fromHtml("报备(" + "<font color='#A52A2A'>" + beanList.get(position).getReportAmount() + "</font>" + ")"));
        holder.attention.setText(Html.fromHtml("关注(" + "<font color='#A52A2A'>" + beanList.get(position).getBrowseNum() + "</font>" + ")"));
        holder.collect.setText(Html.fromHtml("收藏(" + "<font color='#A52A2A'>" + beanList.get(position).getCollectionNum() + "</font>" + ")"));
        holder.transmit.setText(Html.fromHtml("转发(" + "<font color='#A52A2A'>" + beanList.get(position).getForwardingAmount() + "</font>" + ")"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CityContents.getOneKey().equals("一键成交")) {
                    if (onItemClickLisenter != null) {
                        onItemClickLisenter.onItemClick(position);
                    }
                } else {
                    if (FinalContents.getMessageIssueNum().equals("1")) {
                        if (onItemClickLisenter != null) {
                            onItemClickLisenter.onItemClick(position);
                        }
                    } else {
                        if (project.equals("1")) {
                            if (onItemClickLisenter != null) {
                                onItemClickLisenter.onItemClick(position);
                            }
                        } else {
                            FinalContents.setProjectID(beanList.get(position).getProjectId());
                            String ids = beanList.get(position).getLocation();//从pd里取出字符串
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
                            double d = Double.parseDouble(tags.get(0).toString());
                            double o = Double.parseDouble(tags.get(1).toString());
                            FinalContents.setD(d);
                            FinalContents.setO(o);
                            Intent intent = new Intent(context, DetailsTheProjectEndActivity.class);
                            context.startActivity(intent);
                        }
                    }
                }
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
        TextView chick;
        TextView attention;
        TextView collect;
        TextView transmit;
        TextView group_booking;
        ImageView item_OnlineState;
        TextView item_project_location;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            item_project_location = itemView.findViewById(R.id.item_project_location);
            imageAvatar = itemView.findViewById(R.id.ImageView);
            nameText = (TextView) itemView.findViewById(R.id.TextViewName);
            chick = (TextView) itemView.findViewById(R.id.chick);
            attention = (TextView) itemView.findViewById(R.id.attention);
            collect = (TextView) itemView.findViewById(R.id.collect);
            transmit = (TextView) itemView.findViewById(R.id.transmit);
            group_booking = itemView.findViewById(R.id.group_booking_item);
            item_OnlineState = itemView.findViewById(R.id.item_OnlineState);
        }
    }
}
