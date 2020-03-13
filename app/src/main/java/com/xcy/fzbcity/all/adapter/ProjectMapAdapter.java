package com.xcy.fzbcity.all.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;

import java.math.BigDecimal;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectMapAdapter extends RecyclerView.Adapter<ProjectMapAdapter.ProjectMapViewHolder> {

    private ArrayList<PoiItem> pois;

    public void setPois(ArrayList<PoiItem> pois) {
        Log.i("小地图", "进入onPoiSearched2");
        this.pois = pois;
    }

    @NonNull
    @Override
    public ProjectMapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("小地图", "进入onPoiSearched3");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_map_adapter, parent, false);
        return new ProjectMapViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectMapViewHolder holder, int position) {
        Log.i("小地图", "进入onPoiSearched4");
        holder.project_map_tv1.setText(pois.get(position).getTitle());
        float distance = AMapUtils.calculateLineDistance(new LatLng(pois.get(position).getLatLonPoint().getLatitude(), pois.get(position).getLatLonPoint().getLongitude()),
                new LatLng(FinalContents.getO(), FinalContents.getD()));
        if(distance < 1000){
            int scale = 0;//设置尾数
            int roundingMode = 4;//表示四舍五入，可以选择其他的舍值方式，例如去位等等
            BigDecimal b = new BigDecimal(distance);
            b = b.setScale(scale, roundingMode);
            holder.project_map_tv2.setText(b + "m");
        }else {
            float v = distance / 1000;
            int scale = 2;//设置尾数
            int roundingMode = 4;//表示四舍五入，可以选择其他的舍值方式，例如去位等等
            BigDecimal b = new BigDecimal(v);
            b = b.setScale(scale, roundingMode);
            holder.project_map_tv2.setText(b + "km");
        }

    }

    @Override
    public int getItemCount() {
        Log.i("小地图", "进入onPoiSearched5");
        return pois.size();
    }

    class ProjectMapViewHolder extends RecyclerView.ViewHolder {

        TextView project_map_tv1;
        TextView project_map_tv2;

        public ProjectMapViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.i("小地图", "进入onPoiSearched6");
            project_map_tv1 = itemView.findViewById(R.id.project_map_tv1);
            project_map_tv2 = itemView.findViewById(R.id.project_map_tv2);

        }
    }


}
