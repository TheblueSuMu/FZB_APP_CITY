package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.xcy.fzbcity.all.modle.SellingPointsBean;
import com.xcy.fzbcity.all.view.WebActivity;

import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.ViewHolder> {

    private List<SellingPointsBean.DataBean.RowsBean> rows;
    private Context context;

    public void setRows(List<SellingPointsBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_points_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load(FinalContents.getImageUrl() + rows.get(position).getImg()).into(holder.project_points_img);
        holder.project_points_title.setText(rows.get(position).getTitle());
        holder.project_points_message.setText(rows.get(position).getContent());
        Log.i("xxcl", "我从这里走过" + rows.get(position).getTitle());
        holder.project_points_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.xcy.fzb.all.utils.Item_Share.initDaown(context, rows.get(position).getTitle(), FinalContents.getAdminUrl() + "/sellingPoint?" + "&userId=" + FinalContents.getUserID() + "&talkToolId=" +
                        rows.get(position).getId(), rows.get(position).getContent(), FinalContents.getImageUrl() + rows.get(position).getImg(), FinalContents.getAdminUrl() +
                        "/sellingPoint?" + "&userId=" + FinalContents.getUserID() + "&talkToolId=" + rows.get(position).getId());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("webUrl", FinalContents.getAdminUrl() + "/sellingPoint?" + "&userId=" + FinalContents.getUserID() + "&talkToolId=" + rows.get(position).getId());
                intent.putExtra("title", "海报详情");
                FinalContents.setTalkToolId(rows.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView project_points_img;
        TextView project_points_title;
        TextView project_points_message;
        ImageView project_points_share;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            project_points_img = itemView.findViewById(R.id.project_points_img);
            project_points_title = itemView.findViewById(R.id.project_points_title);
            project_points_message = itemView.findViewById(R.id.project_points_message);
            project_points_share = itemView.findViewById(R.id.project_points_share);

        }
    }

}
