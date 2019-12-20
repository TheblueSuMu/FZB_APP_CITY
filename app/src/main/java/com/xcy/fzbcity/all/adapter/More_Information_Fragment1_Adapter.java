package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.MoreBean;
import com.xcy.fzbcity.all.view.MapActivity;

import java.util.List;

public class More_Information_Fragment1_Adapter extends RecyclerView.Adapter<More_Information_Fragment1_Adapter.ViewHolder>{
    private List<MoreBean.DataBean.ValueBeanX.ValueBean> list;
    private View view;
    private Context context;

    public More_Information_Fragment1_Adapter(List<MoreBean.DataBean.ValueBeanX.ValueBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_information_fragment1, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_more_information_fragment1_title.setText(list.get(position).getKey());
        holder.item_more_information_fragment1_content.setText(list.get(position).getValue());
        if (list.get(position).getKey().equals("项目地址")) {
            holder.item_more_information_fragment1_icon.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MapActivity.class);
                    intent.putExtra("office","1");
                    context.startActivity(intent);
                }
            });
        }else if ( list.get(position).getKey().equals("售楼处地址")){
            holder.item_more_information_fragment1_icon.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MapActivity.class);
                    intent.putExtra("office","0");
                    context.startActivity(intent);
                }
            });
        }else {
            holder.item_more_information_fragment1_icon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_more_information_fragment1_title;
        TextView item_more_information_fragment1_content;
        ImageView item_more_information_fragment1_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_more_information_fragment1_title = itemView.findViewById(R.id.item_more_information_fragment1_title);
            item_more_information_fragment1_content = itemView.findViewById(R.id.item_more_information_fragment1_content);
            item_more_information_fragment1_icon = itemView.findViewById(R.id.item_more_information_fragment1_icon);

        }
    }
}
