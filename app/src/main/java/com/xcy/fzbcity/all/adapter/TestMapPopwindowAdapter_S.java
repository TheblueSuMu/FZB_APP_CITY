package com.xcy.fzbcity.all.adapter;

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
import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestMapPopwindowAdapter_S extends RecyclerView.Adapter<TestMapPopwindowAdapter_S.TestMapPopwindowViewHolder> {

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
        float v = AMapUtils.calculateLineDistance(FinalContents.getMylatLng(), new LatLng(allPoi.get(position).getLatLonPoint().getLatitude(), allPoi.get(position).getLatLonPoint().getLongitude()));
//        int scale = 1;//设置尾数
//        int roundingMode = 4;//表示四舍五入，可以选择其他的舍值方式，例如去位等等
//        BigDecimal b = new BigDecimal(v);
//        b = b.setScale(scale, roundingMode);

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        if(v > 1000){
            holder.textView3.setText(nf.format(v / 1000) + "km");
        }else {
            holder.textView3.setText(nf.format(v) + "m");
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTestMap.TestMapS(position);
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
        TextView textView3;

        public TestMapPopwindowViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.test_map_tv1);
            textView2 = itemView.findViewById(R.id.test_map_tv2);
            textView3 = itemView.findViewById(R.id.test_map_tv3);
        }
    }

    public interface OnTestMap {
        void TestMapS(int position);
    }

}
