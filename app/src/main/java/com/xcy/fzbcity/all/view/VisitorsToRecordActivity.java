package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xcy.fzbcity.R;

public class VisitorsToRecordActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout visitors_to_record_return;
    private EditText visitors_to_record_search;
    private ImageView visitors_to_record_search_img;
    private TextView visitors_to_record_all_money_get;
    private TextView visitors_to_record_all_people;
    private TextView visitors_to_record_all_money_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors_to_record);
        initfvb();
    }

    private void initfvb(){
        visitors_to_record_return = findViewById(R.id.visitors_to_record_return);
        visitors_to_record_search = findViewById(R.id.visitors_to_record_search);
        visitors_to_record_search_img = findViewById(R.id.visitors_to_record_search_img);
        visitors_to_record_all_money_get = findViewById(R.id.visitors_to_record_all_money_get);
        visitors_to_record_all_people = findViewById(R.id.visitors_to_record_all_people);
        visitors_to_record_all_money_send = findViewById(R.id.visitors_to_record_all_money_send);

        visitors_to_record_return.setOnClickListener(this);
        visitors_to_record_search_img.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.visitors_to_record_return :
                //  TODO    返回
                finish();
                break;
            case R.id.visitors_to_record_search_img :
                //  TODO    搜索

                break;
        }
    }
}
