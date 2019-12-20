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
import com.xcy.fzbcity.all.modle.DynamicDetailsBean;

import java.util.List;

public class MessageCommentAdapter extends RecyclerView.Adapter<MessageCommentAdapter.MessageCommentViewHolder> {

    List<DynamicDetailsBean.DataBean.CommentListBean> commentList;

    public void setCommentList(List<DynamicDetailsBean.DataBean.CommentListBean> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public MessageCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_economic, parent, false);

        return new MessageCommentViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageCommentViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).load(commentList.get(position).getUser().getPhoto()).into(holder.item_economic_img);
        holder.item_economic_name.setText(commentList.get(position).getCreateDate() + "");
        holder.item_economic_time.setText(commentList.get(position).getUser().getName() + "");
        holder.item_economic_message.setText(commentList.get(position).getComment() + "");


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class MessageCommentViewHolder extends RecyclerView.ViewHolder {

        ImageView item_economic_img;
        TextView item_economic_name;
        TextView item_economic_time;
        TextView item_economic_message;

        public MessageCommentViewHolder(@NonNull View itemView) {
            super(itemView);

            item_economic_img = itemView.findViewById(R.id.item_economic_img);
            item_economic_name = itemView.findViewById(R.id.item_economic_name);
            item_economic_time = itemView.findViewById(R.id.item_economic_time);
            item_economic_message = itemView.findViewById(R.id.item_economic_message);

        }
    }

}