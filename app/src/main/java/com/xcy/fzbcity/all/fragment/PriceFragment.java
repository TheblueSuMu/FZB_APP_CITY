package com.xcy.fzbcity.all.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class PriceFragment extends Fragment {
    private EditText down;
    private EditText up;
    private ImageView ensure;
    private String url = FinalContents.getImageUrl() + "/fangfang/app/v1/commonSelect/projectList?" + "&userId=" + FinalContents.getUserID() + "&city=" + FinalContents.getCityID();
    private String eventUrl = "";
    private ImageView price_reset;

    public PriceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_price, container, false);

        down = view.findViewById(R.id.price_down_count);
        up = view.findViewById(R.id.price_up_count);
        ensure = view.findViewById(R.id.price_ensure);
        price_reset = view.findViewById(R.id.price_reset);
        price_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                down.setText("");
                up.setText("");
            }
        });
        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventUrl = url + "&projectPriceStart=" + down.getText() + "&projectPriceEnd=" + up.getText();
                FinalContents.setProjectPriceStart(down.getText().toString());
                FinalContents.setProjectPriceEnd(up.getText().toString());
                EventBus.getDefault().post(eventUrl);
            }
        });

        return view;
    }

}
