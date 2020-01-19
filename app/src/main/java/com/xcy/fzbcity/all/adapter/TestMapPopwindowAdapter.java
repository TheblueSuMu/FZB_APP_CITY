package com.xcy.fzbcity.all.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.baidu.mapapi.search.core.PoiInfo;
import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;

import java.util.List;

public class TestMapPopwindowAdapter extends RecyclerView.Adapter<TestMapPopwindowAdapter.TestMapPopwindowViewHolder> {

    private List<PoiItem> allPoi;

    public void setAllPoi(List<PoiItem> allPoi) {
        this.allPoi = allPoi;
    }

    OnTestMap onTestMap;

    public void setOnTestMap(OnTestMap onTestMap) {
        this.onTestMap = onTestMap;
    }

    @NonNull
    @Override
    public TestMapPopwindowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_map_pop_item, parent, false);

        return new TestMapPopwindowViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TestMapPopwindowViewHolder holder, final int position) {

        holder.test_map_img.setVisibility(View.VISIBLE);

        if(position == 0){
            Log.i("高德地图","0下标：" + position);
            holder.textView1.setTextColor(Color.parseColor("#AC1E26"));
            Glide.with(holder.itemView.getContext()).load(R.mipmap.yuan2).into(holder.test_map_img);
        }else {
            Log.i("高德地图","下标：" + position);
            holder.textView1.setTextColor(Color.parseColor("#ff111111"));
            Glide.with(holder.itemView.getContext()).load(R.mipmap.yuan1).into(holder.test_map_img);
        }

        if (allPoi.get(position).getTitle().equals("")) {
            holder.textView1.setText(allPoi.get(position).getSnippet());
        }else {
            holder.textView1.setText(allPoi.get(position).getTitle());
        }
        if(allPoi.get(position).getSnippet().equals("")){
            holder.textView2.setText(allPoi.get(position).getTitle());
        }else {
            holder.textView2.setText(allPoi.get(position).getSnippet());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTestMap.TestMap(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPoi.size();
    }

    class TestMapPopwindowViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        ImageView test_map_img;

        public TestMapPopwindowViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.test_map_tv1);
            textView2 = itemView.findViewById(R.id.test_map_tv2);
            test_map_img = itemView.findViewById(R.id.test_map_img);
        }
    }

    public interface OnTestMap {
        void TestMap(int position);
    }

}
