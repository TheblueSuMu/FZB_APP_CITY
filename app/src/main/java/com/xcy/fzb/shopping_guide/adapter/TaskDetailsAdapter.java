package com.xcy.fzb.shopping_guide.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzb.R;
import com.xcy.fzb.all.modle.TaskDetailsBean;

import java.util.List;

public class TaskDetailsAdapter extends RecyclerView.Adapter<TaskDetailsAdapter.ViewHolder>{
    private List<TaskDetailsBean.DataBean.ProjectSpecialInfoBean.BeanBean> list;
    private Context context;

    public TaskDetailsAdapter(List<TaskDetailsBean.DataBean.ProjectSpecialInfoBean.BeanBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_guide_item_task_details_adapter, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.item_task_details_adapter_name.setText("专案【"+list.get(position).getSpecialName()+"】");
        holder.item_task_details_adapter_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = list.get(position).getSpecialPhone();
                if (phone.equals("")) {
                    Toast.makeText(context, "暂无电话信息，无法拨打", Toast.LENGTH_SHORT).show();
                } else {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));//跳转到拨号界面，同时传递电话号码
                    context.startActivity(dialIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_task_details_adapter_icon;
        TextView item_task_details_adapter_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_task_details_adapter_name = itemView.findViewById(R.id.item_task_details_adapter_name);
            item_task_details_adapter_icon = itemView.findViewById(R.id.item_task_details_adapter_icon);

        }
    }
}
