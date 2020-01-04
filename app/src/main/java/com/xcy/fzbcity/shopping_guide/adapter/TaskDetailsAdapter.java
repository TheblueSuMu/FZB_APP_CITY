package com.xcy.fzbcity.shopping_guide.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.TaskDetailsBean;

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
        holder.item_task_details_adapter_name.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        holder.item_task_details_adapter_name.getPaint().setAntiAlias(true);//抗锯齿
        holder.item_task_details_adapter_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = list.get(position).getSpecialPhone();
                if (phone.equals("")) {
                    ToastUtil.showToast(context, "暂无电话信息，无法拨打");
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
        TextView item_task_details_adapter_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_task_details_adapter_name = itemView.findViewById(R.id.item_task_details_adapter_name);

        }
    }
}