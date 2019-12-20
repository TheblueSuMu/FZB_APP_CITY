package com.xcy.fzbcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzbcity.Login.LoginActivity;
import com.xcy.fzbcity.all.api.FinalContents;

public class FinMag extends AppCompatActivity {
    private TextView ensure;
    private TextView cancle;
    private EditText baseUrl;
    private LinearLayout finmag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseurl);
        init();
    }

    private void init(){
        ensure = findViewById(R.id.login_ensure);
        cancle = findViewById(R.id.login_cancle);
        baseUrl = findViewById(R.id.baseUrl);
        finmag = findViewById(R.id.finmag);
        finmag.setVisibility(View.VISIBLE);
        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setImageUrl(baseUrl.getText().toString());
                FinalContents.setBaseUrl(baseUrl.getText().toString()+"/fangfang/app/v1/");
                Intent intent = new Intent(FinMag.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setImageUrl("http://yanshi.fangzuobiao.com:88");
                FinalContents.setBaseUrl("http://yanshi.fangzuobiao.com:88/fangfang/app/v1/");
                Intent intent = new Intent(FinMag.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
