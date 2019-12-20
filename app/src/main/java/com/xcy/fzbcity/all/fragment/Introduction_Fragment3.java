package com.xcy.fzbcity.all.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xcy.fzbcity.Login.LoginActivity;
import com.xcy.fzbcity.R;

public class Introduction_Fragment3 extends AllFragment {

    private View view;
    private ImageView introduction_button;
    private  final int SPLASH_DISPLAY_LENGHT = 3000;//两秒后进入系统，时间可自行调整
    private boolean login = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.introduction_fragment3, container, false);
        init();
        return view;
    }

    private void init(){
        introduction_button = view.findViewById(R.id.introduction_button);
        introduction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                login = false;
            }
        });

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser) {
//            if (true) {
//                new android.os.Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent mainIntent = new Intent(getActivity(),LoginActivity.class);
//                        getActivity().startActivity(mainIntent);
//                        getActivity().finish();
//                    }
//                },SPLASH_DISPLAY_LENGHT);
//            }
//            //相当于OnResume(),可以做相关逻辑
//        }else {
//            //相当于OnPause()
//        }
//    }

}
