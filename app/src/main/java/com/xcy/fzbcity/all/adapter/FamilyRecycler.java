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
import com.xcy.fzbcity.all.modle.ProjectDetailsBean;
import com.xcy.fzbcity.all.view.AnalysisActivity;

import java.util.List;

public class FamilyRecycler extends RecyclerView.Adapter<FamilyRecycler.ViewHolder>{

    private List<ProjectDetailsBean.DataBean.FamilyInfomationsBean.ValueBean> list;
    private String sales;
    private String price;
    final StringBuffer s = new StringBuffer();

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private Context context;

    public FamilyRecycler(List<ProjectDetailsBean.DataBean.FamilyInfomationsBean.ValueBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_item,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(FinalContents.getImageUrl()+list.get(position).getFloorPlan()).into(holder.imageAvatar);

        if(position == 0){
            s.append(list.get(position).getFloorPlan());
        }else {
            s.append("|" + list.get(position).getFloorPlan());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityContents.setFamilyId(list.get(position).getId());
                Intent intent = new Intent(context, AnalysisActivity.class);
                context.startActivity(intent);
//                Intent intent = new Intent(context,BigPhotoActivity.class);
//                intent.putExtra("index",position);
//                intent.putExtra("bigPhotoimg",s.toString());
//                context.startActivity(intent);
            }
        });

        if (list.get(position).getHall() != null) {
            if (list.get(position).getHall().equals("")) {
                if (list.get(position).getHall().equals("") && list.get(position).getToilet().equals("")) {
                    holder.room.setText(list.get(position).getRoom()+"室");
                }else {
                    holder.room.setText(list.get(position).getRoom()+"室"+list.get(position).getToilet()+"卫");
                }
            } else if (list.get(position).getToilet().equals("")) {
                if (list.get(position).getHall().equals("") && list.get(position).getToilet().equals("")) {
                    holder.room.setText(list.get(position).getRoom()+"室");
                }else {
                    holder.room.setText(list.get(position).getRoom()+"室"+list.get(position).getHall()+"厅");
                }
            }else {
                holder.room.setText(list.get(position).getRoom()+"室"+list.get(position).getHall()+"厅"+list.get(position).getToilet()+"卫");
            }
            holder.sales.setText(sales);
            holder.area.setText("面积约："+list.get(position).getFamilyArea());
            holder.orientation.setText("朝向："+list.get(position).getFamilyOrientation());

            switch (list.get(position).getProductType()){
                case "1":
                    holder.type.setText("住宅");
                    break;
                case "2":
                    holder.type.setText("公寓");
                    break;
                case "3":
                    holder.type.setText("写字楼");
                    break;
                case "4":
                    holder.type.setText("商铺");
                    break;
                case "5":
                    holder.type.setText("别墅");
                    break;
            }
            holder.price.setText(price+"万元/套");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageAvatar;
        TextView room;
        TextView sales;
        TextView type;
        TextView area;
        TextView orientation;
        TextView price;



        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = (ImageView)itemView.findViewById(R.id.family_item_pic);
            room =(TextView) itemView.findViewById(R.id.family_item_room);
            sales =(TextView) itemView.findViewById(R.id.family_item_sales);
            type =(TextView) itemView.findViewById(R.id.family_item_type);
            area =(TextView) itemView.findViewById(R.id.family_item_area);
            orientation =(TextView) itemView.findViewById(R.id.family_item_orientation);
            price =(TextView) itemView.findViewById(R.id.family_item_price);
        }
    }
}
