package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.FriendsAssistantBean;

import java.util.List;

public class CircleOfFriendsAssistantCaptionListAdapter extends RecyclerView.Adapter<CircleOfFriendsAssistantCaptionListAdapter.CircleOfFriendsAssistantCaptionListViewHolder>{
    private List<FriendsAssistantBean.DataBean.ListDateBean> list;
    private View view;
    private Context context;

    public CircleOfFriendsAssistantCaptionListAdapter(List<FriendsAssistantBean.DataBean.ListDateBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CircleOfFriendsAssistantCaptionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_circle_of_friends_assistant_caption_navigation_list, parent, false);
        context = parent.getContext();
        return new CircleOfFriendsAssistantCaptionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleOfFriendsAssistantCaptionListViewHolder holder, int position) {
        holder.adapter_circle_of_friends_assistant_caption_navigation_list_content.setText(list.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CircleOfFriendsAssistantCaptionListViewHolder extends RecyclerView.ViewHolder{
        TextView adapter_circle_of_friends_assistant_caption_navigation_list_content;
        RelativeLayout adapter_circle_of_friends_assistant_caption_navigation_list_copy;

        public CircleOfFriendsAssistantCaptionListViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_circle_of_friends_assistant_caption_navigation_list_content = itemView.findViewById(R.id.adapter_circle_of_friends_assistant_caption_navigation_list_content);
            adapter_circle_of_friends_assistant_caption_navigation_list_copy = itemView.findViewById(R.id.adapter_circle_of_friends_assistant_caption_navigation_list_copy);

        }
    }
}
