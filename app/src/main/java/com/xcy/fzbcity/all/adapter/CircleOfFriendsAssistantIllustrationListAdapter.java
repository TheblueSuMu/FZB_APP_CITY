package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.tools.ScreenUtils;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.FriendsAssistantBean;
import com.xcy.fzbcity.all.view.CirclOfFriendsAssistantAppletActivity;

import java.util.List;

public class CircleOfFriendsAssistantIllustrationListAdapter extends RecyclerView.Adapter<CircleOfFriendsAssistantIllustrationListAdapter.CircleOfFriendsAssistantIllustrationListViewHolder>{
    private List<FriendsAssistantBean.DataBean.ListDateBean> list;
    private View view;
    private Context context;

    public CircleOfFriendsAssistantIllustrationListAdapter(List<FriendsAssistantBean.DataBean.ListDateBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CircleOfFriendsAssistantIllustrationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_circle_of_friends_assistant_illustration_list, parent, false);
        context = parent.getContext();
        return new CircleOfFriendsAssistantIllustrationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleOfFriendsAssistantIllustrationListViewHolder holder, int position) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.adapter_circle_of_friends_assistant_illustration_list_image.getLayoutParams();
//        float itemwidth = (ScreenUtils.getScreenWidth(holder.adapter_circle_of_friends_assistant_illustration_list_image.getContext()) - 8*3) / 2;
//        layoutParams.width = (int) itemwidth;
//        float scale = (itemwidth + 0f) / list.get(position).getHeight();
        layoutParams.height = Integer.parseInt(list.get(position).getHeight());
        holder.adapter_circle_of_friends_assistant_illustration_list_image.setLayoutParams(layoutParams);

        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position).getImgPath()).into(holder.adapter_circle_of_friends_assistant_illustration_list_image);
        Log.i("图片","地址"+list.get(position).getImgPath());


        holder.adapter_circle_of_friends_assistant_illustration_list_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedEnvelopesAllTalk.setAppletImage(list.get(position).getImgPath());
                Intent intent = new Intent(context, CirclOfFriendsAssistantAppletActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CircleOfFriendsAssistantIllustrationListViewHolder extends RecyclerView.ViewHolder{
        ImageView adapter_circle_of_friends_assistant_illustration_list_image;

        public CircleOfFriendsAssistantIllustrationListViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_circle_of_friends_assistant_illustration_list_image = itemView.findViewById(R.id.adapter_circle_of_friends_assistant_illustration_list_image);
        }
    }
}
