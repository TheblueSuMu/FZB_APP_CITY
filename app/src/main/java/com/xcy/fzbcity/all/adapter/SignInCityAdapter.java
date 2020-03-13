package com.xcy.fzbcity.all.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.modle.SignInCityBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SignInCityAdapter extends RecyclerView.Adapter<SignInCityAdapter.SignInCityViewHolder> {

    private List<SignInCityBean.DataBean> data;
    OnCityClick onCityClick;
    String cityName;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setOnCityClick(OnCityClick onCityClick) {
        this.onCityClick = onCityClick;
    }

    public void setListCity(List<SignInCityBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SignInCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_in_item, parent, false);
        return new SignInCityViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SignInCityViewHolder holder, int position) {
        if(data.get(position).getName().equals(cityName)){
            holder.sign_in_img.setVisibility(View.VISIBLE);
            holder.sign_in_tv.setTextColor(Color.parseColor("#334485"));
        }else {
            holder.sign_in_img.setVisibility(View.GONE);
            holder.sign_in_tv.setTextColor(Color.parseColor("#111111"));
        }
//        if(position == 0){
//            holder.sign_in_img.setVisibility(View.GONE);
//            holder.sign_in_tv.setTextColor(Color.parseColor("#999999"));
//        }else {
//            holder.sign_in_img.setVisibility(View.GONE);
//            holder.sign_in_tv.setTextColor(Color.parseColor("#999999"));
//        }
        holder.sign_in_tv.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCityClick.CityClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SignInCityViewHolder extends RecyclerView.ViewHolder {

        ImageView sign_in_img;
        TextView sign_in_tv;

        public SignInCityViewHolder(@NonNull View itemView) {
            super(itemView);

            sign_in_img = itemView.findViewById(R.id.sign_in_img);
            sign_in_tv = itemView.findViewById(R.id.sign_in_tv);

        }
    }

    public interface OnCityClick{
        void CityClick(int cityPosition);
    }

}
