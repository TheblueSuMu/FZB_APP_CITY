package com.xcy.fzbcity.all.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.BuildingBean;
import com.xcy.fzbcity.all.view.AnalysisActivity;

import java.util.List;

public class BuildingInformationAdapter extends RecyclerView.Adapter<BuildingInformationAdapter.ViewHolder>{
    private List<BuildingBean.DataBean.HouseInfoListBean.ValueBean> list;
    private Context context;
    private String imageUrl = "";
    StringBuffer s = new StringBuffer();

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BuildingInformationAdapter(List<BuildingBean.DataBean.HouseInfoListBean.ValueBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.building_item,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load(FinalContents.getImageUrl() +list.get(position).getFloorPlan()).into(holder.imageAvatar);

        if(position == 0){
        s.append(list.get(position).getFloorPlan());
        }else {
        s.append("|" + list.get(position).getFloorPlan());
        }

        holder.room.setText(list.get(position).getModel());
        holder.area.setText("面积约："+list.get(position).getArea());
        holder.orientation.setText("朝向："+list.get(position).getOrientation());

        holder.imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityContents.setFamilyId(list.get(position).getId());
                Intent intent = new Intent(context, AnalysisActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;
        TextView room;
        TextView area;
        TextView orientation;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = (ImageView)itemView.findViewById(R.id.building_item_pic);
            room =(TextView) itemView.findViewById(R.id.building_item_room);
            area =(TextView) itemView.findViewById(R.id.building_item_area);
            orientation =(TextView) itemView.findViewById(R.id.building_item_orientation);
        }
    }
}