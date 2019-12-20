package com.xcy.fzbcity.captain_team.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.TeamMemberBean;

import java.util.List;

public class Captain_Team_SalesDetailsAdapter extends RecyclerView.Adapter<Captain_Team_SalesDetailsAdapter.SalesDetailsViewHolder> {

    private List<TeamMemberBean.DataBean.RowsBean> list;
    private String identy = "";

    public void setIdenty(String identy) {
        this.identy = identy;
    }

    public Captain_Team_SalesDetailsAdapter(List<TeamMemberBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    @NonNull
    @Override
    public SalesDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.captain_team_item_sales_details, parent, false);
        return new SalesDetailsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final SalesDetailsViewHolder holder, final int position) {

        Glide.with(holder.itemView.getContext()).load(FinalContents.getImageUrl() + list.get(position).getPhoto()).into(holder.item_sales_details_img);
        if (identy.equals("顾问")) {
            holder.item_sales_details_tv1.setText(list.get(position).getName()+"("+list.get(position).getRatioName()+")");
            holder.item_sales_details_tv3.setText("团队长："+list.get(position).getLeaderName() +"   销售："+list.get(position).getSaleName());
        } else if (identy.equals("销售")) {
            holder.item_sales_details_tv1.setText(list.get(position).getName()+"("+list.get(position).getRatioName()+")");
            holder.item_sales_details_tv3.setText("团队长："+list.get(position).getLeaderName());
        }
        holder.item_sales_details_tv2.setText(list.get(position).getPhone());

        holder.item_sales_details_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getPhone()));//跳转到拨号界面，同时传递电话号码
                holder.itemView.getContext().startActivity(dialIntent);
            }
        });

        if(list.get(position).getLoginDate().equals("")){
            holder.item_sales_details_tv4.setVisibility(View.GONE);
        }else {
            holder.item_sales_details_tv4.setText("最后上线时间："+list.get(position).getLoginDate());
        }

        if (list.get(position).getLoginFlag().equals("0")) {
            holder.member_name_rls.setVisibility(View.VISIBLE);
        } else {
            holder.member_name_rls.setVisibility(View.GONE);
        }

        holder.item_sales_details_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SalesDetailsViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout item_sales_details_rl;
        RelativeLayout member_name_rls;
        ImageView item_sales_details_img;
        TextView item_sales_details_tv1;
        TextView item_sales_details_tv2;
        TextView item_sales_details_tv3;
        TextView item_sales_details_tv4;
        public SalesDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            member_name_rls = itemView.findViewById(R.id.member_name_rls);
            item_sales_details_rl = itemView.findViewById(R.id.item_sales_details_rl);
            item_sales_details_img = itemView.findViewById(R.id.item_sales_details_img);
            item_sales_details_tv1 = itemView.findViewById(R.id.item_sales_details_tv1);
            item_sales_details_tv2 = itemView.findViewById(R.id.item_sales_details_tv2);
            item_sales_details_tv3 = itemView.findViewById(R.id.item_sales_details_tv3);
            item_sales_details_tv4 = itemView.findViewById(R.id.item_sales_details_tv4);
        }
    }

}
