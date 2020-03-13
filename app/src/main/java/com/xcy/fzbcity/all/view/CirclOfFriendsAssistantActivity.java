package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.broker.fragment.CircleOfFriendsAssistantCaptionFragment;
import com.xcy.fzbcity.broker.fragment.CircleOfFriendsAssistantIllustrationFragment;


public class CirclOfFriendsAssistantActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout circle_of_friends_assistant_return;
    private TextView circle_of_friends_assistant_illustration;
    private TextView circle_of_friends_assistant_caption;
    private View circle_of_friends_assistant_illustration_view;
    private View circle_of_friends_assistant_caption_view;
    private FrameLayout circle_of_friends_assistant_framelayout;
    private LinearLayout circle_of_friends_assistant_illustration_linear;
    private LinearLayout circle_of_friends_assistant_caption_linear;
    private CircleOfFriendsAssistantCaptionFragment circleOfFriendsAssistantCaptionFragment;
    private CircleOfFriendsAssistantIllustrationFragment circleOfFriendsAssistantIllustrationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_of_friends_assistant);
        initfvb();
    }
    
    private void initfvb(){
        circle_of_friends_assistant_return = findViewById(R.id.circle_of_friends_assistant_return);
        circle_of_friends_assistant_illustration = findViewById(R.id.circle_of_friends_assistant_illustration);
        circle_of_friends_assistant_caption = findViewById(R.id.circle_of_friends_assistant_caption);
        circle_of_friends_assistant_illustration_view = findViewById(R.id.circle_of_friends_assistant_illustration_view);
        circle_of_friends_assistant_caption_view = findViewById(R.id.circle_of_friends_assistant_caption_view);
        circle_of_friends_assistant_framelayout = findViewById(R.id.circle_of_friends_assistant_framelayout);
        circle_of_friends_assistant_illustration_linear = findViewById(R.id.circle_of_friends_assistant_illustration_linear);
        circle_of_friends_assistant_caption_linear = findViewById(R.id.circle_of_friends_assistant_caption_linear);

        circle_of_friends_assistant_illustration_linear.setOnClickListener(this);
        circle_of_friends_assistant_caption_linear.setOnClickListener(this);
        circle_of_friends_assistant_return.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        circleOfFriendsAssistantIllustrationFragment = new CircleOfFriendsAssistantIllustrationFragment();
        circleOfFriendsAssistantCaptionFragment = new CircleOfFriendsAssistantCaptionFragment();

        transaction.replace(R.id.circle_of_friends_assistant_framelayout, circleOfFriendsAssistantIllustrationFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.circle_of_friends_assistant_return :
                //  TODO    返回
                finish();
                break;
            case R.id.circle_of_friends_assistant_caption_linear :
                //  TODO    朋友圈配文
                transaction.replace(R.id.circle_of_friends_assistant_framelayout, circleOfFriendsAssistantCaptionFragment);
                break;
            case R.id.circle_of_friends_assistant_illustration_linear :
                //  TODO    朋友圈配图
                transaction.replace(R.id.circle_of_friends_assistant_framelayout, circleOfFriendsAssistantIllustrationFragment);
                break;

        }
        transaction.commit();
    }
}
