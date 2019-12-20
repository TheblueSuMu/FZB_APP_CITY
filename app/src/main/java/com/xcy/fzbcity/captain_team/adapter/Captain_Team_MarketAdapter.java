package com.xcy.fzbcity.captain_team.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;

public class Captain_Team_MarketAdapter extends RecyclerView.Adapter<Captain_Team_MarketAdapter.MarketViewHolder> {


    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.captain_team_item_market, parent, false);
        return new MarketViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MarketViewHolder extends RecyclerView.ViewHolder{

        ImageView item_market_img1;
        ImageView item_market_img2;

        TextView item_market_tv1;
        TextView item_market_tv2;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);

            item_market_img1 = itemView.findViewById(R.id.item_market_img1);
            item_market_img2 = itemView.findViewById(R.id.item_market_img2);
            item_market_tv1 = itemView.findViewById(R.id.item_market_tv1);
            item_market_tv2 = itemView.findViewById(R.id.item_market_tv2);

        }
    }

}
