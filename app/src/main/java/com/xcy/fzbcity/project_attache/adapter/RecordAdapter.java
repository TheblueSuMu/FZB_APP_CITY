package com.xcy.fzbcity.project_attache.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.RecordBean;
import com.xcy.fzbcity.all.view.BigPhotoActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    List<RecordBean.DataBean.RowsBean> rows;

    public void setRows(List<RecordBean.DataBean.RowsBean> rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_clock_stores, viewGroup, false);
        return new RecordViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecordViewHolder recordViewHolder, final int i) {

        if (rows.get(i).getType().equals("1")) {
            recordViewHolder.item_clock_stores_tv1.setText("到店打卡：");
            recordViewHolder.item_clock_stores_tv2.setText("打卡时间：" + rows.get(i).getCreateDate());
        } else if (rows.get(i).getType().equals("2")) {
            recordViewHolder.item_clock_stores_tv1.setText("出店打卡：");
            if (rows.get(i).getEffective().equals("0")) {//无效打卡
                recordViewHolder.item_clock_stores_tv2.setText("打卡时间：" + rows.get(i).getCreateDate() + "(无效打卡)");
            } else if (rows.get(i).getEffective().equals("1")) {//有效打卡
                recordViewHolder.item_clock_stores_tv2.setText("打卡时间：" + rows.get(i).getCreateDate() + "(有效打卡)");
            }
        }

        recordViewHolder.item_clock_stores_tv3.setText(rows.get(i).getAddress());

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append = stringBuffer.append(rows.get(i).getImg());
        int num = 0;
        int num1 = 0;
        int num2 = 0;
        for (int j = 0; j < append.length(); ++j) {
            if (stringBuffer.substring(j, j + 1).equals("|")) {
                num++;
                if (num == 1) {
                    num1 = j;
                } else if (num == 2) {
                    num2 = j;
                }
            }
        }
        if (num == 0) {
            recordViewHolder.item_clock_stores_img1.setVisibility(View.VISIBLE);
            Glide.with(recordViewHolder.itemView.getContext()).load(FinalContents.getImageUrl() + rows.get(i).getImg()).into(recordViewHolder.item_clock_stores_img1);
        } else if (num == 1) {
            recordViewHolder.item_clock_stores_img1.setVisibility(View.VISIBLE);
            recordViewHolder.item_clock_stores_img2.setVisibility(View.VISIBLE);
            Glide.with(recordViewHolder.itemView.getContext()).load(FinalContents.getImageUrl() + append.substring(0, num1)).into(recordViewHolder.item_clock_stores_img1);
            Glide.with(recordViewHolder.itemView.getContext()).load(FinalContents.getImageUrl() + append.substring(num1 + 1)).into(recordViewHolder.item_clock_stores_img2);
        } else if (num == 2) {
            recordViewHolder.item_clock_stores_img1.setVisibility(View.VISIBLE);
            recordViewHolder.item_clock_stores_img2.setVisibility(View.VISIBLE);
            recordViewHolder.item_clock_stores_img3.setVisibility(View.VISIBLE);
            Glide.with(recordViewHolder.itemView.getContext()).load(FinalContents.getImageUrl() + append.substring(0, num1)).into(recordViewHolder.item_clock_stores_img1);
            Glide.with(recordViewHolder.itemView.getContext()).load(FinalContents.getImageUrl() + append.substring(num1 + 1,num2)).into(recordViewHolder.item_clock_stores_img2);
            Glide.with(recordViewHolder.itemView.getContext()).load(FinalContents.getImageUrl() + append.substring(num2 + 1)).into(recordViewHolder.item_clock_stores_img3);
        }

        //图片点击事件 图片放大效果
        recordViewHolder.item_clock_stores_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recordViewHolder.itemView.getContext(), BigPhotoActivity.class);
                intent.putExtra("index", 0);
                intent.putExtra("bigPhotoimg", rows.get(i).getImg());
                recordViewHolder.itemView.getContext().startActivity(intent);
            }
        });
        recordViewHolder.item_clock_stores_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recordViewHolder.itemView.getContext(), BigPhotoActivity.class);
                intent.putExtra("index", 1);
                intent.putExtra("bigPhotoimg", rows.get(i).getImg());
                recordViewHolder.itemView.getContext().startActivity(intent);
            }
        });
        recordViewHolder.item_clock_stores_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recordViewHolder.itemView.getContext(), BigPhotoActivity.class);
                intent.putExtra("index", 2);
                intent.putExtra("bigPhotoimg", rows.get(i).getImg());
                recordViewHolder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder {

        TextView item_clock_stores_tv1;
        TextView item_clock_stores_tv2;
        TextView item_clock_stores_tv3;

        ImageView item_clock_stores_img1;
        ImageView item_clock_stores_img2;
        ImageView item_clock_stores_img3;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);

            item_clock_stores_tv1 = itemView.findViewById(R.id.item_clock_stores_tv1);
            item_clock_stores_tv2 = itemView.findViewById(R.id.item_clock_stores_tv2);
            item_clock_stores_tv3 = itemView.findViewById(R.id.item_clock_stores_tv3);

            item_clock_stores_img1 = itemView.findViewById(R.id.item_clock_stores_img1);
            item_clock_stores_img2 = itemView.findViewById(R.id.item_clock_stores_img2);
            item_clock_stores_img3 = itemView.findViewById(R.id.item_clock_stores_img3);

        }
    }



}
