package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.GetTheRecordParticularsAdapter;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.RedbagReceiveRecordBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.service.MyService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetTheRecordParticularsActivity extends AllActivity implements View.OnClickListener {

    private RecyclerView adapter_get_the_record_particulars_recyclerview;
    private LinearLayout activity_get_the_record_particulars_return;
    private ImageView adapter_get_the_record_particulars_image;
    private TextView adapter_get_the_record_particulars_title;
    private TextView adapter_get_the_record_particulars_price;
    private TextView adapter_get_the_record_particulars_time;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_the_record_particulars);
        initfvb();
    }

    private void initfvb(){
        activity_get_the_record_particulars_return = findViewById(R.id.activity_get_the_record_particulars_return);
        adapter_get_the_record_particulars_image = findViewById(R.id.adapter_get_the_record_particulars_image);
        adapter_get_the_record_particulars_title = findViewById(R.id.adapter_get_the_record_particulars_title);
        adapter_get_the_record_particulars_price = findViewById(R.id.adapter_get_the_record_particulars_price);
        adapter_get_the_record_particulars_time = findViewById(R.id.adapter_get_the_record_particulars_time);
        adapter_get_the_record_particulars_recyclerview = findViewById(R.id.adapter_get_the_record_particulars_recyclerview);
        Log.i("","");
        activity_get_the_record_particulars_return.setOnClickListener(this);

        initData();
    }


    //      TODO    领取详情数据接口
    private void initData(){
        index = RedEnvelopesAllTalk.getIndex();
        List<RedbagReceiveRecordBean.DataBean.RowsBean> list = RedEnvelopesAllTalk.getRedbagReceiveRecordList();
        Glide.with(GetTheRecordParticularsActivity.this).load(FinalContents.getImageUrl() + list.get(index).getCustomerImg()).into(adapter_get_the_record_particulars_image);
        adapter_get_the_record_particulars_title.setText(list.get(index).getCustomerName());
        adapter_get_the_record_particulars_price.setText(list.get(index).getDenomination());
        adapter_get_the_record_particulars_time.setText(list.get(index).getClaimDate());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GetTheRecordParticularsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter_get_the_record_particulars_recyclerview.setLayoutManager(linearLayoutManager);
        GetTheRecordParticularsAdapter getTheRecordParticularsAdapter = new GetTheRecordParticularsAdapter(list.get(index).getAssistList());
        adapter_get_the_record_particulars_recyclerview.setAdapter(getTheRecordParticularsAdapter);
        getTheRecordParticularsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_get_the_record_particulars_return :
                //  TODO    返回
                finish();
                break;

        }
    }
}
