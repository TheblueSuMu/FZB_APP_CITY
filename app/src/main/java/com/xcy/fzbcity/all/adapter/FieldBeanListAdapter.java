package com.xcy.fzbcity.all.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.database.FieldBean;

import java.util.List;

public class FieldBeanListAdapter extends RecyclerView.Adapter<FieldBeanListAdapter.FieldViewHolder>{

    private List<FieldBean> list;

    private OnItemClickLisenter onItemClickLisenter;

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    public FieldBeanListAdapter(List<FieldBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_field, parent, false);

        return new FieldViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final FieldViewHolder holder, final int position) {
        holder.item_field_title.setText(list.get(position).getFullName()+"  "+list.get(position).getGender()+"  "+list.get(position).getRelation());
        holder.item_field_amend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(position);
                }
                Log.i("来下","下标："+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FieldViewHolder extends RecyclerView.ViewHolder{
        TextView item_field_title;
        ImageView item_field_amend;

        public FieldViewHolder(@NonNull View itemView) {
            super(itemView);
            item_field_title = itemView.findViewById(R.id.item_field_title);
            item_field_amend = itemView.findViewById(R.id.item_field_amend);
        }

    }
}