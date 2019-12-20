package com.xcy.fzbcity.all.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseTypeFragment extends Fragment implements View.OnClickListener {
    private CheckBox house0;
    private CheckBox house1;
    private CheckBox house2;
    private CheckBox house3;
    private CheckBox house4;
    private CheckBox house5;
    private CheckBox house6;
    private ImageView ensure;
    private String url = FinalContents.getImageUrl() +  "/fangfang/app/v1/commonSelect/projectList?"+ "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID()+ "&apartment=";
    private String eventUrl = FinalContents.getBaseUrl() + "/fangfang/app/v1/commonSelect/projectList?"+ "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID()+ "&apartment=";
    private String string = "";
    private Map<Integer,String> map = new HashMap<>();
    private ImageView house_reset;

    public HouseTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house_type, container, false);
        house0 = view.findViewById(R.id.house_type_0);
        house1 = view.findViewById(R.id.house_type_1);
        house2 = view.findViewById(R.id.house_type_2);
        house3 = view.findViewById(R.id.house_type_3);
        house4 = view.findViewById(R.id.house_type_4);
        house5 = view.findViewById(R.id.house_type_5);
        house6 = view.findViewById(R.id.house_type_6);
        ensure = view.findViewById(R.id.house_ensure);
        house_reset = view.findViewById(R.id.house_reset);
        house_reset.setOnClickListener(this);
        initData();
        init();
        return view;
    }

    private void init(){
        house0.setOnClickListener(this);
        house1.setOnClickListener(this);
        house2.setOnClickListener(this);
        house3.setOnClickListener(this);
        house4.setOnClickListener(this);
        house5.setOnClickListener(this);
        house6.setOnClickListener(this);
        ensure.setOnClickListener(this);

    }

    private void initData(){
        map.put(0,"");
        map.put(1,"");
        map.put(2,"");
        map.put(3,"");
        map.put(4,"");
        map.put(5,"");
        map.put(6,"");

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.house_type_0:
                if (house0.isChecked()) {
                    map.put(0,",0");
                }else {
                    map.put(0,"");
                }
                break;
            case R.id.house_type_1:
                if (house1.isChecked()) {
                    map.put(1,",1");
                }else {
                    map.put(1,"");
                }
                break;
            case R.id.house_type_2:
                if (house2.isChecked()) {
                    map.put(2,",2");
                }else {
                    map.put(2,"");
                }
                break;
            case R.id.house_type_3:
                if (house3.isChecked()) {
                    map.put(3,",3");
                }else {
                    map.put(3,"");
                }
                break;
            case R.id.house_type_4:
                if (house4.isChecked()) {
                    map.put(4,",4");
                }else {
                    map.put(4,"");
                }
                break;
            case R.id.house_type_5:
                if (house5.isChecked()) {
                    map.put(5,",5");
                }else {
                    map.put(5,"");
                }
                break;
            case R.id.house_type_6:
                if (house6.isChecked()) {
                    map.put(6,",6");
                }else {
                    map.put(6,"");
                }
                break;
            case R.id.house_ensure:
                for (int i = 0;i < map.size();i++){
                    string = string + map.get(i);
                }
                eventUrl = url + string;
                FinalContents.setApartment(string);
                EventBus.getDefault().post(eventUrl);
                string = "";
                break;
            case R.id.house_reset:
                house0.setChecked(false);
                house1.setChecked(false);
                house2.setChecked(false);
                house3.setChecked(false);
                house4.setChecked(false);
                house5.setChecked(false);
                house6.setChecked(false);

                initData();
                break;
        }
    }
}
