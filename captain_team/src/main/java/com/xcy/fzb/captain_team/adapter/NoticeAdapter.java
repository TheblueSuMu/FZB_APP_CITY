package com.xcy.fzb.captain_team.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzb.captain_team.R;
import com.xcy.fzb.captain_team.database.MessageBean;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    List<MessageBean.DataBean.RowsBean> rows;
    Click click;
    FZClick fzClick;

    public void setFzClick(FZClick fzClick) {
        this.fzClick = fzClick;
    }

    public void setClick(Click click) {
        this.click = click;
    }

    public void setRows(List<MessageBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guest_room, null);

        return new NoticeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, final int position) {

        if (rows.get(position).getType2().equals("5")) {
            Glide.with(holder.itemView.getContext()).load(R.mipmap.z20).into(holder.room_title_img);
        } else if (rows.get(position).getType2().equals("6")) {
            Glide.with(holder.itemView.getContext()).load(R.mipmap.z23).into(holder.room_title_img);
        } else if (rows.get(position).getType2().equals("7")) {
            Glide.with(holder.itemView.getContext()).load(R.mipmap.z24).into(holder.room_title_img);
        } else if (rows.get(position).getType2().equals("8")) {
            Glide.with(holder.itemView.getContext()).load(R.mipmap.z25).into(holder.room_title_img);
        }
        if (rows.get(position).getImgPath().equals("")) {
            holder.room_img.setVisibility(View.GONE);
        } else {
            holder.room_img.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView.getContext()).load("http://39.98.173.250:8080" + rows.get(position).getImgPath()).into(holder.room_img);
        }

        if(rows.get(position).getPhone().equals("")){
            holder.room_phone.setText("暂无");
        }else {
            holder.room_phone.setText(rows.get(position).getPhone());
        }

        holder.room_title.setText(rows.get(position).getTitle());
        holder.room_time.setText(rows.get(position).getCreateDate());
        holder.room_message.setText(rows.get(position).getContent());
        holder.room_name.setText(rows.get(position).getUserName());


//        TODO 拨打电话
        holder.room_ll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.ItemOnClick(position);
            }
        });
        holder.room_ll_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fzClick.ItemFZOnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {

        ImageView room_title_img;
        ImageView room_img;

        TextView room_title;
        TextView room_time;
        TextView room_message;
        TextView room_name;
        TextView room_phone;

        LinearLayout room_ll_1;
        LinearLayout room_ll_2;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);


            room_title_img = itemView.findViewById(R.id.room_title_img);
            room_img = itemView.findViewById(R.id.room_img);
            room_title = itemView.findViewById(R.id.room_title);
            room_time = itemView.findViewById(R.id.room_time);
            room_message = itemView.findViewById(R.id.room_message);
            room_name = itemView.findViewById(R.id.room_name);
            room_phone = itemView.findViewById(R.id.room_phone);
            room_ll_1 = itemView.findViewById(R.id.room_ll_1);
            room_ll_2 = itemView.findViewById(R.id.room_ll_2);

        }

    }

    public interface Click {
        void ItemOnClick(int position);
    }

    public interface FZClick {
        void ItemFZOnClick(int position);
    }

}
