package com.xcy.fzbcity.captain_team.adapter;

import android.content.Context;
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
import com.xcy.fzbcity.all.database.DailyTurnoverBean;

import java.util.List;

public class DailyTurnoverAdapter extends RecyclerView.Adapter<DailyTurnoverAdapter.MyTeamViewHolder> {
    private List<DailyTurnoverBean.DataBean> list;
    private Context context;

    public DailyTurnoverAdapter(List<DailyTurnoverBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.captain_team_item_market, parent, false);
        context = parent.getContext();
        return new MyTeamViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTeamViewHolder holder, int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getPhoto()).into(holder.item_market_img2);
        holder.item_market_tv1.setText(list.get(position).getName());
        holder.item_market_tv2.setText(list.get(position).getNum()+"");

        if (position == 0) {
            holder.item_market_img1.setBackgroundResource(R.mipmap.qtop01);
        } else if (position == 1) {
            holder.item_market_img1.setBackgroundResource(R.mipmap.qtop02);
        } else if (position == 2) {
            holder.item_market_img1.setBackgroundResource(R.mipmap.qtop03);
        } else if (position == 3) {
            holder.item_market_img1.setBackgroundResource(R.mipmap.qtop04);
        } else if (position == 4) {
            holder.item_market_img1.setBackgroundResource(R.mipmap.qtop05);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyTeamViewHolder extends RecyclerView.ViewHolder{

        ImageView item_market_img1;
        ImageView item_market_img2;

        TextView item_market_tv1;
        TextView item_market_tv2;

        public MyTeamViewHolder(@NonNull View itemView) {
            super(itemView);

            item_market_img1 = itemView.findViewById(R.id.item_market_img1);
            item_market_img2 = itemView.findViewById(R.id.item_market_img2);
            item_market_tv1 = itemView.findViewById(R.id.item_market_tv1);
            item_market_tv2 = itemView.findViewById(R.id.item_market_tv2);

        }
    }

}
