package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.GoodNewsBean;

import java.util.List;

public class GoodNewsAdapter extends RecyclerView.Adapter<GoodNewsAdapter.GoodNewsViewHolder> {

    List<GoodNewsBean.DataBean.RowsBean> rows;

    public void setRows(List<GoodNewsBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public GoodNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good_news, parent,false);
        return new GoodNewsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodNewsViewHolder holder, int position) {

        if(rows.get(position).getProjectType().equals("2")){//海外
            Glide.with(holder.itemView.getContext()).load(R.mipmap.hwimg).into(holder.news_img);
        }else if(rows.get(position).getProjectType().equals("3")){//旅居
            Glide.with(holder.itemView.getContext()).load(R.mipmap.ljimg).into(holder.news_img);
        }else if(rows.get(position).getProjectType().equals("1")){//城市
            Glide.with(holder.itemView.getContext()).load(R.mipmap.city_id).into(holder.news_img);
        }

        holder.news_title.setText(rows.get(position).getTitle());

        String content = rows.get(position).getContent();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(content);

        for (int i = 0; i < stringBuffer.length(); ++i) {
            if (stringBuffer.substring(i, i + 1).equals("\\")) {
                stringBuffer.replace(i, i + 2, "\n");
            }
        }
        holder.news_message.setText(stringBuffer.toString());


    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class GoodNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView news_img;
        TextView news_title;
        TextView news_message;

        public GoodNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            news_img = itemView.findViewById(R.id.news_img);
            news_title = itemView.findViewById(R.id.news_title);
            news_message = itemView.findViewById(R.id.news_message);

        }
    }

}
