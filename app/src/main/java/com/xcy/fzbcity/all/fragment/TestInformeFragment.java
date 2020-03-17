package com.xcy.fzbcity.all.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CodeBean;
import com.xcy.fzbcity.all.modle.InformTheDetailsBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.DatabaseHelper;
import com.xcy.fzbcity.all.utils.InformeDataBase;
import com.xcy.fzbcity.all.utils.InformeDataBaseUtil;
import com.xcy.fzbcity.all.utils.InformeDatabaseHelper;
import com.xcy.fzbcity.all.view.InformTheDetailsActivity;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestInformeFragment extends Fragment implements View.OnClickListener {

    TextView infor_time_1;
    TextView infor_time_2;
    TextView infor_time_3;
    TextView infor_message_1;
    TextView infor_message_2;
    TextView infor_message_3;
    TextView infor_num_1;
    TextView infor_num_2;
    TextView infor_num_3;
    RelativeLayout infor_rl_1;
    RelativeLayout infor_rl_2;
    RelativeLayout infor_rl_3;
    private Intent intent;

    String tzTime = "";
    String tzMessage = "";

    String hbTime = "";
    String hbMessage = "";

    String zjTime = "";
    String zjMessage = "";

    int tzNum = 0;
    int hbNum = 0;
    int zjNum = 0;

    public TestInformeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_informe, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        infor_time_1 = getActivity().findViewById(R.id.infor_time_1);
        infor_time_2 = getActivity().findViewById(R.id.infor_time_2);
        infor_time_3 = getActivity().findViewById(R.id.infor_time_3);
        infor_message_1 = getActivity().findViewById(R.id.infor_message_1);
        infor_message_2 = getActivity().findViewById(R.id.infor_message_2);
        infor_message_3 = getActivity().findViewById(R.id.infor_message_3);
        infor_num_1 = getActivity().findViewById(R.id.infor_num_1);
        infor_num_2 = getActivity().findViewById(R.id.infor_num_2);
        infor_num_3 = getActivity().findViewById(R.id.infor_num_3);
        infor_rl_1 = getActivity().findViewById(R.id.infor_rl_1);
        infor_rl_2 = getActivity().findViewById(R.id.infor_rl_2);
        infor_rl_3 = getActivity().findViewById(R.id.infor_rl_3);

        infor_rl_1.setOnClickListener(this);
        infor_rl_2.setOnClickListener(this);
        infor_rl_3.setOnClickListener(this);




    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {

        tzNum = 0;
        hbNum = 0;
        zjNum = 0;

        tzTime = "";
        hbTime = "";
        zjTime = "";

        tzMessage = "";
        hbMessage = "";
        zjMessage = "";

        List<InformeDataBase> informeDataBases = InformeDataBaseUtil.initSelect(getActivity(), "");
//        Log.i("通知数据库","informeDataBases.size():" + informeDataBases.size());
        for (int i = 0; i < informeDataBases.size(); ++i) {

            if (informeDataBases.get(i).getType().equals("1")) {
                if(informeDataBases.get(i).getUserId().equals(FinalContents.getUserID())){
                    tzMessage = informeDataBases.get(i).getContent();
                    tzTime = informeDataBases.get(i).getDate();
                    if (informeDataBases.get(i).getIsRead().equals("1")) {
                        tzNum++;
                    }
                }
            } else if (informeDataBases.get(i).getType().equals("2")) {
                if(informeDataBases.get(i).getUserId().equals(FinalContents.getUserID())){
                    hbMessage = informeDataBases.get(i).getContent();
                    hbTime = informeDataBases.get(i).getDate();
                    if (informeDataBases.get(i).getIsRead().equals("1")) {
                        hbNum++;
                    }
                }

            } else if (informeDataBases.get(i).getType().equals("3")) {
                if(informeDataBases.get(i).getUserId().equals(FinalContents.getUserID())){
                    zjMessage = informeDataBases.get(i).getContent();
                    zjTime = informeDataBases.get(i).getDate();
                    if (informeDataBases.get(i).getIsRead().equals("1")) {
                        zjNum++;
                    }
                }

            }
//            Log.i("通知数据库","i:" + i);
            if (i == (informeDataBases.size() - 1)) {
                initInformeData();
            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String timestamp2Date(String str_num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(toLong(str_num)));
        return date;

    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initInformeData() {

        //时间戳转换
        if(tzTime.equals("")){
            infor_time_1.setText("");
        }else {
            infor_time_1.setText(timestamp2Date(tzTime) + "");
        }

        if(hbTime.equals("")){
            infor_time_2.setText("");
        }else {
            infor_time_2.setText(timestamp2Date(hbTime) + "");
        }

        if(zjTime.equals("")){
            infor_time_3.setText("");
        }else {
            infor_time_3.setText(timestamp2Date(zjTime) + "");
        }


        Log.i("通知数据库","tzMessage:" + tzMessage);

        infor_message_1.setText(tzMessage);
        infor_message_2.setText(hbMessage);
        infor_message_3.setText(zjMessage);

        if (tzNum == 0) {
            infor_num_1.setVisibility(View.GONE);
        } else {
            infor_num_1.setVisibility(View.VISIBLE);
            infor_num_1.setText(tzNum + "");
        }

        if (hbNum == 0) {
            infor_num_2.setVisibility(View.GONE);
        } else {
            infor_num_2.setVisibility(View.VISIBLE);
            infor_num_2.setText(hbNum + "");
        }

        if (zjNum == 0) {
            infor_num_3.setVisibility(View.GONE);
        } else {
            infor_num_3.setVisibility(View.VISIBLE);
            infor_num_3.setText(zjNum + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.infor_rl_1:
                intent = new Intent(getActivity(), InformTheDetailsActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.infor_rl_2:
                intent = new Intent(getActivity(), InformTheDetailsActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.infor_rl_3:
                intent = new Intent(getActivity(), InformTheDetailsActivity.class);
                intent.putExtra("type", "3");
                startActivity(intent);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();

        initData();

    }
}
