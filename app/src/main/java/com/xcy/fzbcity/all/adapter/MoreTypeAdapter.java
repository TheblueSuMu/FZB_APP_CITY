package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.MoreBean;

import java.util.List;

public class MoreTypeAdapter extends RecyclerView.Adapter<MoreTypeAdapter.ViewHolder>{
    private List<MoreBean.DataBean.ValueBeanX> list;
    private int more = 0;
    private int[] img = {R.drawable.more_type_1, R.drawable.more_type_2};
    private Context context;

    public MoreTypeAdapter(List<MoreBean.DataBean.ValueBeanX> list) {
        this.list = list;
        Log.i("长度","list"+list.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_type_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nameText.setText(list.get(position).getKey());
        holder.more_img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (more == 0) {
                    more = 1;
                    holder.more_type_Rv.setVisibility(View.VISIBLE);
                    holder.more_img_1.setImageResource(img[0]);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    holder.more_type_Rv.setLayoutManager(linearLayoutManager);
                    More_Information_Fragment1_Adapter more_information_fragment1_adapter = new More_Information_Fragment1_Adapter(list.get(position).getValue());
                    holder.more_type_Rv.setAdapter(more_information_fragment1_adapter);
                    more_information_fragment1_adapter.notifyDataSetChanged();
                } else if (more == 1) {
                    more = 0;
                    holder.more_type_Rv.setVisibility(View.GONE);
                    holder.more_img_1.setImageResource(img[1]);
                }
            }
        });
        if (list.get(position).getIsshow().equals("0")) {
            more = 0;
            holder.more_img_1.setImageResource(img[1]);
            holder.more_type_Rv.setVisibility(View.GONE);
        } else if (list.get(position).getIsshow().equals("1")) {
            more = 1;
            holder.more_img_1.setImageResource(img[0]);
            holder.more_type_Rv.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.more_type_Rv.setLayoutManager(linearLayoutManager);
            More_Information_Fragment1_Adapter more_information_fragment1_adapter = new More_Information_Fragment1_Adapter(list.get(position).getValue());
            holder.more_type_Rv.setAdapter(more_information_fragment1_adapter);
            more_information_fragment1_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        ImageView more_img_1;
        RecyclerView more_type_Rv;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.more_type_name);
            more_img_1 = itemView.findViewById(R.id.more_img_1);
            more_type_Rv = itemView.findViewById(R.id.more_type_Rv);
        }
    }
}
