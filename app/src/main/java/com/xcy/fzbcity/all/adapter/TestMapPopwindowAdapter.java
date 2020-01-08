package com.xcy.fzbcity.all.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.xcy.fzbcity.R;

import java.util.List;

public class TestMapPopwindowAdapter extends RecyclerView.Adapter<TestMapPopwindowAdapter.TestMapPopwindowViewHolder> {

    private List<PoiInfo> allPoi;

    public void setAllPoi(List<PoiInfo> allPoi) {
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

        if (allPoi.get(position).getName().equals("")) {
            holder.textView1.setText(allPoi.get(position).getAddress());
        } else {
            if (allPoi.get(position).getArea().equals("")) {
                holder.textView1.setText(allPoi.get(position).getName());
            } else {
                holder.textView1.setText(allPoi.get(position).getName() + "(" + allPoi.get(position).getArea() + ")");
            }
        }
        if (allPoi.get(position).getAddress().equals("")) {
            if (allPoi.get(position).getName().equals("")) {
                holder.textView1.setText(allPoi.get(position).getAddress());
            } else {
                if (allPoi.get(position).getArea().equals("")) {
                    holder.textView1.setText(allPoi.get(position).getName());
                } else {
                    holder.textView2.setText(allPoi.get(position).getName() + "(" + allPoi.get(position).getArea() + ")");
                }
            }
        } else {
            holder.textView2.setText(allPoi.get(position).getAddress());
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

        public TestMapPopwindowViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.test_map_tv1);
            textView2 = itemView.findViewById(R.id.test_map_tv2);
        }
    }

    public interface OnTestMap {
        void TestMap(int position);
    }

}
