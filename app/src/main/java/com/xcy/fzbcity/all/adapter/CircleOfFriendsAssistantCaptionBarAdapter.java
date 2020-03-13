package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.FriendsAssistantBean;

import java.util.List;

public class CircleOfFriendsAssistantCaptionBarAdapter extends RecyclerView.Adapter<CircleOfFriendsAssistantCaptionBarAdapter.CircleOfFriendsAssistantCaptionBarViewHolder>{
    private List<FriendsAssistantBean.DataBean> list;
    private View view;
    private Context context;
    private int index = 0;
    private OnItemClickLisenter onItemClickLisenter;


    public interface OnItemClickLisenter {
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener) {
        this.onItemClickLisenter = onItemClickListener;
    }

    public CircleOfFriendsAssistantCaptionBarAdapter(List<FriendsAssistantBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CircleOfFriendsAssistantCaptionBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_circle_of_friends_assistant_caption_navigation_bar, parent,false);
        context = parent.getContext();
        return new CircleOfFriendsAssistantCaptionBarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleOfFriendsAssistantCaptionBarViewHolder holder, int position) {

        if (index == position) {
            holder.adapter_circle_of_friends_assistant_caption_navigation_bar_title.setTextColor(Color.parseColor("#334485"));
            holder.adapter_circle_of_friends_assistant_caption_navigation_bar_title.setTextSize(18);
            holder.adapter_circle_of_friends_assistant_caption_navigation_bar_view.setVisibility(View.VISIBLE);
        }else {
            holder.adapter_circle_of_friends_assistant_caption_navigation_bar_title.setTextColor(Color.parseColor("#666666"));
            holder.adapter_circle_of_friends_assistant_caption_navigation_bar_title.setTextSize(15);
            holder.adapter_circle_of_friends_assistant_caption_navigation_bar_view.setVisibility(View.INVISIBLE);
        }
        holder.adapter_circle_of_friends_assistant_caption_navigation_bar_title.setText(list.get(position).getClassName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null) {
                    onItemClickLisenter.onItemClick(position);
                }
                index = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CircleOfFriendsAssistantCaptionBarViewHolder extends RecyclerView.ViewHolder{
        private View adapter_circle_of_friends_assistant_caption_navigation_bar_view;
        private TextView adapter_circle_of_friends_assistant_caption_navigation_bar_title;


        public CircleOfFriendsAssistantCaptionBarViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_circle_of_friends_assistant_caption_navigation_bar_view = itemView.findViewById(R.id.adapter_circle_of_friends_assistant_caption_navigation_bar_view);
            adapter_circle_of_friends_assistant_caption_navigation_bar_title = itemView.findViewById(R.id.adapter_circle_of_friends_assistant_caption_navigation_bar_title);

        }
    }

}
