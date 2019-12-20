package com.xcy.fzbcity.all.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.persente.StatusBar;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprehensiveFragment extends Fragment {

    RadioGroup comprehensive_rg;
    RadioButton comprehensive_rb_1;
    RadioButton comprehensive_rb_2;
    RadioButton comprehensive_rb_3;
    RadioButton comprehensive_rb_4;
    private Button ensure;
    private boolean fff = true;

    private String eventUrl = "";


    public ComprehensiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StatusBar.makeStatusBarTransparent(getActivity());

        return inflater.inflate(R.layout.fragment_comprehensive, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comprehensive_rg = getActivity().findViewById(R.id.comprehensive_rg);
        comprehensive_rb_1 = getActivity().findViewById(R.id.comprehensive_rb_1);
        comprehensive_rb_2 = getActivity().findViewById(R.id.comprehensive_rb_2);
        comprehensive_rb_3 = getActivity().findViewById(R.id.comprehensive_rb_3);
        comprehensive_rb_4 = getActivity().findViewById(R.id.comprehensive_rb_4);
        ensure = getActivity().findViewById(R.id.ensure);

        comprehensive_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(comprehensive_rb_1.isChecked() == true){
                    Log.i("MyCL","默认排序");
                    FinalContents.setComprehensiveSorting("10");
                }else if(comprehensive_rb_2.isChecked() == true){
                    FinalContents.setComprehensiveSorting("20");
                    Log.i("MyCL","价格降序");
                }else if(comprehensive_rb_3.isChecked() == true){
                    FinalContents.setComprehensiveSorting("30");
                    Log.i("MyCL","价格升序");
                }else if(comprehensive_rb_4.isChecked() == true){
                    FinalContents.setComprehensiveSorting("40");
                    Log.i("MyCL","成交排序");
                }
            }
        });

        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(eventUrl);
            }
        });
    }

}
