package com.xcy.fzbcity.shopping_guide.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.xcy.fzbcity.all.modle.TaskListBean;
import com.xcy.fzbcity.shopping_guide.view.TaskDetailsActivity;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder>{

    private List<TaskListBean.DataBean.RowsBean> list;
    private Context context;

    public TaskListAdapter(List<TaskListBean.DataBean.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_guide_item_task_list, null);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getTravelWayImg()).into(holder.item_task_icon);
        if (list.get(position).getTravelWay().equals("0")) {
            holder.item_task_back.setBackgroundResource(R.drawable.item_task_list_back1);
            holder.item_task_layout.setBackgroundResource(R.mipmap.task1);
        } else if (list.get(position).getTravelWay().equals("1")) {
            holder.item_task_back.setBackgroundResource(R.drawable.item_task_list_back3);
            holder.item_task_layout.setBackgroundResource(R.mipmap.task3);
        }else if (list.get(position).getTravelWay().equals("2")) {
            holder.item_task_back.setBackgroundResource(R.drawable.item_task_list_back2);
            holder.item_task_layout.setBackgroundResource(R.mipmap.task2);
        }
        holder.item_task_islandTime.setText(list.get(position).getIslandTime()+"-"+list.get(position).getEndTime());
        holder.item_task_routeName.setText(list.get(position).getRoute().getRouteName());
        holder.item_task_StatusName.setText(list.get(position).getTaskStatusName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getTaskStatus().equals("60")) {
                    FinalContents.setDaoGou("60");
                } else {
                    FinalContents.setDaoGou("1");
                }
                FinalContents.setRouteTimeId(list.get(position).getId());
                Intent intent = new Intent(context, TaskDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout item_task_back;
        RelativeLayout item_task_layout;
        ImageView item_task_icon;
        TextView item_task_routeName;
        TextView item_task_islandTime;
        TextView item_task_StatusName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_task_back = itemView.findViewById(R.id.item_task_back);
            item_task_layout = itemView.findViewById(R.id.item_task_layout);
            item_task_icon = itemView.findViewById(R.id.item_task_icon);
            item_task_routeName = itemView.findViewById(R.id.item_task_routeName);
            item_task_islandTime = itemView.findViewById(R.id.item_task_islandTime);
            item_task_StatusName = itemView.findViewById(R.id.item_task_StatusName);
        }
    }
}
