package com.xcy.fzbcity.project_side.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.DynamicFragment;
import com.xcy.fzbcity.all.fragment.GoodNewsFragment;
import com.xcy.fzbcity.all.fragment.GuestRoomFragment;
import com.xcy.fzbcity.all.fragment.NoticeFragment;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.project_side.view.MessageIssueActivity;

//TODO 消息界面
public class MessageFragment extends Fragment implements View.OnClickListener {
    private View view;
    LinearLayout information_ll_1;
    LinearLayout information_ll_2;
    LinearLayout information_ll_3;
    LinearLayout information_ll_4;
    LinearLayout information_ll_5;
    LinearLayout information_ll_6;
    LinearLayout information_ll_7;
    LinearLayout information_ll_8;

    FrameLayout information_fl;

    FragmentManager manager;
    FragmentTransaction transaction;
    DynamicFragment dynamicFragment = new DynamicFragment();
    com.xcy.fzbcity.all.fragment.NoticeFragment noticeFragment = new NoticeFragment();
    GuestRoomFragment guestRoomFragment = new GuestRoomFragment();
    GoodNewsFragment goodNewsFragment = new GoodNewsFragment();
    ImageView message_fb;

    private String type = "";

    public void setType(String type) {
        this.type = type;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());

        view = inflater.inflate(R.layout.project_side_modulebroker_fragment_message, null);
        FinalContents.setHidden(true);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if (FinalContents.isLuo()) {
            transaction.add(R.id.information_fl, dynamicFragment);
            transaction.add(R.id.information_fl, noticeFragment);
            transaction.add(R.id.information_fl, guestRoomFragment);
            transaction.add(R.id.information_fl, goodNewsFragment);
            transaction.hide(noticeFragment);
            transaction.hide(guestRoomFragment);
            transaction.hide(goodNewsFragment);
            FinalContents.setLuo(false);
        }
        if (type.equals("1")) {
            information_ll_5.setVisibility(View.VISIBLE);
            information_ll_6.setVisibility(View.INVISIBLE);
            information_ll_7.setVisibility(View.INVISIBLE);
            information_ll_8.setVisibility(View.INVISIBLE);
            transaction.show(dynamicFragment);
            transaction.hide(noticeFragment);
            transaction.hide(guestRoomFragment);
            transaction.hide(goodNewsFragment);
        } else if (type.equals("2")) {
            information_ll_5.setVisibility(View.INVISIBLE);
            information_ll_6.setVisibility(View.VISIBLE);
            information_ll_7.setVisibility(View.INVISIBLE);
            information_ll_8.setVisibility(View.INVISIBLE);
            transaction.show(noticeFragment);
            transaction.hide(dynamicFragment);
            transaction.hide(guestRoomFragment);
            transaction.hide(goodNewsFragment);
        } else if (type.equals("3")) {
            information_ll_5.setVisibility(View.INVISIBLE);
            information_ll_6.setVisibility(View.INVISIBLE);
            information_ll_7.setVisibility(View.VISIBLE);
            information_ll_8.setVisibility(View.INVISIBLE);
            transaction.show(guestRoomFragment);
            transaction.hide(dynamicFragment);
            transaction.hide(noticeFragment);
            transaction.hide(goodNewsFragment);
        } else if (type.equals("4")) {
            information_ll_5.setVisibility(View.INVISIBLE);
            information_ll_6.setVisibility(View.INVISIBLE);
            information_ll_7.setVisibility(View.INVISIBLE);
            information_ll_8.setVisibility(View.VISIBLE);
            transaction.show(goodNewsFragment);
            transaction.hide(dynamicFragment);
            transaction.hide(guestRoomFragment);
            transaction.hide(noticeFragment);
        }
        transaction.commit();
    }

    private void initView() {

        information_fl = view.findViewById(R.id.information_fl);

        information_ll_1 = view.findViewById(R.id.information_ll_1);
        information_ll_2 = view.findViewById(R.id.information_ll_2);
        information_ll_3 = view.findViewById(R.id.information_ll_3);
        information_ll_4 = view.findViewById(R.id.information_ll_4);
        information_ll_5 = view.findViewById(R.id.information_ll_5);
        information_ll_6 = view.findViewById(R.id.information_ll_6);
        information_ll_7 = view.findViewById(R.id.information_ll_7);
        information_ll_8 = view.findViewById(R.id.information_ll_8);

        message_fb = view.findViewById(R.id.message_fb);
        message_fb.setOnClickListener(this);

        information_ll_1.setOnClickListener(this);
        information_ll_2.setOnClickListener(this);
        information_ll_3.setOnClickListener(this);
        information_ll_4.setOnClickListener(this);


    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.information_ll_1:
                type = "1";
                information_ll_5.setVisibility(View.VISIBLE);
                information_ll_6.setVisibility(View.INVISIBLE);
                information_ll_7.setVisibility(View.INVISIBLE);
                information_ll_8.setVisibility(View.INVISIBLE);
                transaction.show(dynamicFragment);
                transaction.hide(noticeFragment);
                transaction.hide(guestRoomFragment);
                transaction.hide(goodNewsFragment);
                break;
            case R.id.information_ll_2:
                type = "2";
                information_ll_5.setVisibility(View.INVISIBLE);
                information_ll_6.setVisibility(View.VISIBLE);
                information_ll_7.setVisibility(View.INVISIBLE);
                information_ll_8.setVisibility(View.INVISIBLE);
                transaction.show(noticeFragment);
                transaction.hide(dynamicFragment);
                transaction.hide(guestRoomFragment);
                transaction.hide(goodNewsFragment);
                break;
            case R.id.information_ll_3:
                type = "3";
                information_ll_5.setVisibility(View.INVISIBLE);
                information_ll_6.setVisibility(View.INVISIBLE);
                information_ll_7.setVisibility(View.VISIBLE);
                information_ll_8.setVisibility(View.INVISIBLE);
                transaction.show(guestRoomFragment);
                transaction.hide(dynamicFragment);
                transaction.hide(noticeFragment);
                transaction.hide(goodNewsFragment);
                break;
            case R.id.information_ll_4:
                type = "4";
                information_ll_5.setVisibility(View.INVISIBLE);
                information_ll_6.setVisibility(View.INVISIBLE);
                information_ll_7.setVisibility(View.INVISIBLE);
                information_ll_8.setVisibility(View.VISIBLE);
                transaction.show(goodNewsFragment);
                transaction.hide(dynamicFragment);
                transaction.hide(guestRoomFragment);
                transaction.hide(noticeFragment);
                break;
            case R.id.message_fb:
                Intent intent = new Intent(getContext(), MessageIssueActivity.class);
                startActivity(intent);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (FinalContents.isHidden()) {             //TODO 非消息内部
            if(hidden){
                type = "1";
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    message_fb.setVisibility(View.VISIBLE);
                }else {
                    message_fb.setVisibility(View.GONE);
                }
                //TODO now visible to user 不显示fragment
            } else {
                onResume();
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    message_fb.setVisibility(View.VISIBLE);
                }else {
                    message_fb.setVisibility(View.GONE);
                }
                //TODO now invisible to user 显示fragment
            }
        }else {                                     //TODO  消息内部
            if(hidden){
                //TODO now visible to user 不显示fragment
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    message_fb.setVisibility(View.VISIBLE);
                }else {
                    message_fb.setVisibility(View.GONE);
                }
            } else {
                onResume();
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    message_fb.setVisibility(View.VISIBLE);
                }else {
                    message_fb.setVisibility(View.GONE);
                }
                //TODO now invisible to user 显示fragment
            }
        }
    }
}
