package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ReportProcessBean;
import com.xcy.fzbcity.all.view.ReviewTheSuccessActivity;

import java.util.List;

public class ReportProcessAdapter extends RecyclerView.Adapter<ReportProcessAdapter.ClientFragmentViewHolder> {

    List<ReportProcessBean.DataBean.RowsBean> rows;
    private Context context;


    public void setRows(List<ReportProcessBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public ClientFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        context = parent.getContext();
        return new ClientFragmentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientFragmentViewHolder holder, final int position) {

        Glide.with(holder.itemView.getContext()).load(FinalContents.getImageUrl() + rows.get(position).getCustomerImg()).into(holder.client_item_img);
        FinalContents.setPreparationId(rows.get(position).getPreparationId());
        holder.client_item_name.setText(rows.get(position).getCustomerName());
        holder.client_item_photo.setText("(" + rows.get(position).getCustomerPhone() + ")");
        holder.client_item_time.setText(rows.get(position).getDate());
        holder.client_item_project_name.setText(rows.get(position).getProjectName());
        Log.i("折行","数据"+rows.get(position).getRelatedData());

        if (rows.get(position).getIsRead().equals("0")){
            holder.client_item_unread.setVisibility(View.VISIBLE);
        }else if(rows.get(position).getIsRead().equals("1")){
            holder.client_item_unread.setVisibility(View.GONE);
        }

        if (!rows.get(position).getRelatedData().equals("")) {
            if (rows.get(position).getRelatedData().contains("保")) {
                String[] split = rows.get(position).getRelatedData().split("保");
                holder.client_item_cg.setText("保"+split[1]);
                if (split[0].equals("报备成功\n") || split[0].equals("到访成功\n") || split[0].equals("登岛(审核成功)\n")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#43987C'>" + split[0] + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn2);
                } else if (split[0].equals("失效\n") || split[0].equals("待审核\n") || split[0].equals("登岛(审核失败)\n")|| split[0].equals("到访申请\n")
                        || split[0].equals("调单审核\n")|| split[0].equals("拒绝调单\n")|| split[0].equals("退单审核\n")|| split[0].equals("拒绝退单\n")
                        || split[0].equals("退筹审核\n")|| split[0].equals("拒绝退筹\n")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#ac1e26'>" + split[0] + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn1);
                } else if (split[0].equals("申请登岛\n") || split[0].equals("登岛中\n") || split[0].equals("已认筹\n") || split[0].equals("成交\n") || split[0].equals("已调单\n") || split[0].equals("已退单\n")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#334485'>" + split[0] + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn3);
                } else if (split[0].equals("登岛结束\n")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#999999'>" + split[0] + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn4);
                }
                holder.client_item_cg.setTextColor(Color.parseColor("#ac1e26"));
            }else {
//                holder.client_item_title.setText(rows.get(position).getRelatedData());
                if (rows.get(position).getRelatedData().equals("报备成功") || rows.get(position).getRelatedData().equals("到访成功") || rows.get(position).getRelatedData().equals("登岛(审核成功)")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#43987C'>" + rows.get(position).getRelatedData() + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn2);
                } else if (rows.get(position).getRelatedData().equals("失效") || rows.get(position).getRelatedData().equals("待审核") || rows.get(position).getRelatedData().equals("登岛(审核失败)") || rows.get(position).getRelatedData().equals("到访申请")
                        || rows.get(position).getRelatedData().equals("调单审核")|| rows.get(position).getRelatedData().equals("拒绝调单")|| rows.get(position).getRelatedData().equals("退单审核")|| rows.get(position).getRelatedData().equals("拒绝退单")
                        || rows.get(position).getRelatedData().equals("退筹审核")|| rows.get(position).getRelatedData().equals("拒绝退筹")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#ac1e26'>" + rows.get(position).getRelatedData() + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn1);
                } else if (rows.get(position).getRelatedData().equals("申请登岛") || rows.get(position).getRelatedData().equals("登岛中") || rows.get(position).getRelatedData().equals("已认筹") || rows.get(position).getRelatedData().equals("成交") || rows.get(position).getRelatedData().equals("已调单") || rows.get(position).getRelatedData().equals("已退单")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#334485'>" + rows.get(position).getRelatedData() + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn3);
                } else if (rows.get(position).getRelatedData().equals("登岛结束")) {
                    holder.client_item_title.setText(Html.fromHtml("<font color='#999999'>" + rows.get(position).getRelatedData() + "</font>"));
//                holder.client_item_title.setTextColor(R.color.colornn4);
                }
                holder.client_item_cg.setVisibility(View.GONE);
            }
        }else {
            holder.client_item_title.setVisibility(View.GONE);
            holder.client_item_cg.setVisibility(View.GONE);
        }

        holder.client_item_project_add.setText(rows.get(position).getAgentName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewTheSuccessActivity.class);
                FinalContents.setPreparationId(rows.get(position).getPreparationId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class ClientFragmentViewHolder extends RecyclerView.ViewHolder {

        ImageView client_item_img;
        TextView client_item_name;
        TextView client_item_photo;
        TextView client_item_project_name;
        TextView client_item_time;
        TextView client_item_cg;
        TextView client_item_title;
        TextView client_item_project_add;
        ImageView client_item_unread;

        public ClientFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            client_item_img = itemView.findViewById(R.id.client_item_img);
            client_item_name = itemView.findViewById(R.id.client_item_name);
            client_item_photo = itemView.findViewById(R.id.client_item_photo);
            client_item_project_name = itemView.findViewById(R.id.client_item_project_name);
            client_item_time = itemView.findViewById(R.id.client_item_time);
            client_item_cg = itemView.findViewById(R.id.client_item_cg);
            client_item_title = itemView.findViewById(R.id.client_item_title);
            client_item_project_add = itemView.findViewById(R.id.client_item_project_add);
            client_item_unread = itemView.findViewById(R.id.client_item_unread);

        }
    }

}
