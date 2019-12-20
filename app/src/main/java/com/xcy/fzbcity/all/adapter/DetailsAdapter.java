package com.xcy.fzbcity.all.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.utils.DetailsData;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

    List<DetailsData> list = new ArrayList<>();

    private DetailsItem detailsItem;

    public void setDetailsItem(DetailsItem detailsItem) {
        this.detailsItem = detailsItem;
    }

    public void setList(List<DetailsData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details, parent, false);
        return new DetailsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailsViewHolder holder, final int position) {
        Log.i("MyCL", "list1:" + list.size());
        if(list.size() == 1){
            holder.item_details_ifnum.setVisibility(View.GONE);
            holder.item_details_buildingName.setText(list.get(position).getBuildingName() + "");
            if(list.get(position).getElementNumber().equals("")){
                holder.item_details_elementNumber.setText(list.get(position).getElementNumber());
            }else {
                holder.item_details_elementNumber.setText(list.get(position).getElementNumber() + "个单元");
            }
            if(list.get(position).getHouseholds().equals("")){
                holder.item_details_households.setText("");
            }else {
                holder.item_details_households.setText(list.get(position).getHouseholds() + "户");
            }
        }else if(list.size() == 2){
            if(list.get(position).getIfnum().equals("1")){
                holder.item_details_ifnum.setVisibility(View.VISIBLE);
            }else if(list.get(position).getIfnum().equals("2")){
                holder.item_details_ifnum.setVisibility(View.GONE);
            }
            holder.item_details_buildingName.setText(list.get(position).getBuildingName() + "");
            if(list.get(position).getElementNumber().equals("")){
                holder.item_details_elementNumber.setText(list.get(position).getElementNumber());
            }else {
                holder.item_details_elementNumber.setText(list.get(position).getElementNumber() + "个单元");
            }
            if(list.get(position).getHouseholds().equals("")){
                holder.item_details_households.setText("");
            }else {
                holder.item_details_households.setText(list.get(position).getHouseholds() + "户");
            }
            Log.i("MyCL", "list1:" + list.get(position).getBuildingName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsItem.MyItems(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder{

        TextView item_details_buildingName;
        TextView item_details_elementNumber;
        TextView item_details_households;
        LinearLayout item_details_ifnum;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            item_details_buildingName = itemView.findViewById(R.id.item_details_buildingName);
            item_details_elementNumber = itemView.findViewById(R.id.item_details_elementNumber);
            item_details_households = itemView.findViewById(R.id.item_details_households);
            item_details_ifnum = itemView.findViewById(R.id.item_details_ifnum);

        }
    }

    public interface DetailsItem{

        void MyItems(int position);

    }

}
