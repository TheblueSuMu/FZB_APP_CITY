package com.xcy.fzbcity.all.adapter;

import android.content.Intent;
import android.util.Log;
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
import com.xcy.fzbcity.all.modle.SpellingMassTimeBean;
import com.xcy.fzbcity.all.view.SpellingMassActivity;

import java.util.List;

public class SpellingMassTimeAdaptewr extends RecyclerView.Adapter<SpellingMassTimeAdaptewr.SpellingMassTimeViewHolder> {

    private List<SpellingMassTimeBean.DataBean.RowsBean> rows;

    public void setRows(List<SpellingMassTimeBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public SpellingMassTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, null);
        return new SpellingMassTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpellingMassTimeViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(FinalContents.getImageUrl() + rows.get(position).getTravelWayImg()).into(holder.item_task_icon);

        Log.i("MyCL", "拼团测试：" + rows.get(position).getIslandTime() + "-" + rows.get(position).getEndTime());
        holder.item_task_islandTime.setText(rows.get(position).getIslandTime() + "-" + rows.get(position).getEndTime());
        holder.item_task_routeName.setText(rows.get(position).getRoute().getRouteName());
        holder.item_task_tv1.setText("截至：" + rows.get(position).getEndCloseTime() + "");
        holder.item_task_tv2.setText(rows.get(position).getExpenses());
        holder.item_task_tv3.setText("已报" + rows.get(position).getEnrollNumber() + "人");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setRouteTimeId(rows.get(position).getId());
                Intent intent = new Intent(holder.itemView.getContext(), SpellingMassActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class SpellingMassTimeViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item_task_back;
        ImageView item_task_icon;
        TextView item_task_routeName;
        TextView item_task_islandTime;
        TextView item_task_tv1;
        TextView item_task_tv2;
        TextView item_task_tv3;


        public SpellingMassTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            item_task_back = itemView.findViewById(R.id.item_task_back);
            item_task_icon = itemView.findViewById(R.id.item_task_icon);
            item_task_routeName = itemView.findViewById(R.id.item_task_routeName);
            item_task_islandTime = itemView.findViewById(R.id.item_task_islandTime);
            item_task_tv1 = itemView.findViewById(R.id.item_task_tv1);
            item_task_tv2 = itemView.findViewById(R.id.item_task_tv2);
            item_task_tv3 = itemView.findViewById(R.id.item_task_tv3);

        }
    }

}
