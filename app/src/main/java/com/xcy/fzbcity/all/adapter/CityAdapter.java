package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.NationBean;
import com.xcy.fzbcity.all.view.RecyclerViewActivity;

import java.util.List;
import java.util.Random;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{
    private List<NationBean.DataBean> list;
    private Context context;
    private int num;

    public CityAdapter(List<NationBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i("拼音数据","拼音"+list.get(position).getPinyin());
        Glide.with(context).load(FinalContents.getImageUrl()+list.get(position).getNationImg()).into(holder.item_city_adapter_name);
//        holder.item_city_adapter_pingying.setText(list.get(position).getPinyin());
//        num = getNum(4);
//        if (num == 0) {
//            holder.item_city_adapter_linear.setBackgroundResource(R.drawable.city_back1);
//        } else if (num == 1) {
//            holder.item_city_adapter_linear.setBackgroundResource(R.drawable.city_back2);
//        } else if (num == 2) {
//            holder.item_city_adapter_linear.setBackgroundResource(R.drawable.city_back3);
//        } else if (num == 3) {
//            holder.item_city_adapter_linear.setBackgroundResource(R.drawable.city_back4);
//        } else if (num == 4) {
//            Log.i("云","有四");
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecyclerViewActivity.class);
                intent.putExtra("nation", list.get(position).getNationName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 生成一个0 到 count 之间的随机数
     * @param endNum
     * @return
     */
    public static int getNum(int endNum){
        if(endNum > 0){
            Random random = new Random();
            return random.nextInt(endNum);
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_city_adapter_name;
        TextView item_city_adapter_pingying;
        LinearLayout item_city_adapter_linear;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_city_adapter_linear = itemView.findViewById(R.id.item_city_adapter_linear);
            item_city_adapter_pingying = itemView.findViewById(R.id.item_city_adapter_pingying);
            item_city_adapter_name = itemView.findViewById(R.id.item_city_adapter_name);
        }

    }
}
