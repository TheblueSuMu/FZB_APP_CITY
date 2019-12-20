package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.utils.DataBase;
import com.xcy.fzbcity.all.utils.DataBaseUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class ATestActivity extends AppCompatActivity {

    Button dataBase_1;
    Button dataBase_2;
    Button dataBase_3;
    EditText dataBase_4;
    EditText dataBase_7;
    EditText dataBase_8;
    EditText dataBase_9;
    EditText dataBase_10;
    EditText dataBase_11;
    Button dataBase_5;
    TextView dataBase_6;
    TextView dataBase_66;

    //TODO 数据库使用数据
    private List<DataBase> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atest);

        initNewData();

    }

    private void initNewData() {

        dataBase_1 = findViewById(R.id.dataBase_1);
        dataBase_2 = findViewById(R.id.dataBase_2);
        dataBase_3 = findViewById(R.id.dataBase_3);
        dataBase_4 = findViewById(R.id.dataBase_4);
        dataBase_7 = findViewById(R.id.dataBase_7);
        dataBase_8 = findViewById(R.id.dataBase_8);
        dataBase_9 = findViewById(R.id.dataBase_9);
        dataBase_10 = findViewById(R.id.dataBase_10);
        dataBase_11 = findViewById(R.id.dataBase_11);
        dataBase_5 = findViewById(R.id.dataBase_5);
        dataBase_6 = findViewById(R.id.dataBase_6);
        dataBase_66 = findViewById(R.id.dataBase_66);

//        TODO 增
        dataBase_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = dataBase_7.getText().toString();
                String s = dataBase_8.getText().toString();

                long index = DataBaseUtil.initAdd(ATestActivity.this, s1, s);
                if (index != -1) {
                    ToastUtil.showToast(ATestActivity.this,"插入成功");
                }
                int i = DataBaseUtil.initSize(ATestActivity.this);
                Log.i("数据库","数据库长度：" + i);
            }
        });

//        TODO 删
        dataBase_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = dataBase_9.getText().toString();

                int numbers = DataBaseUtil.initDelete(ATestActivity.this, s);
                if (numbers == 0) {
                    ToastUtil.showToast(ATestActivity.this,"没有找到符合条件的");
                } else {
                    ToastUtil.showToast(ATestActivity.this,"删除成功");
                }
                int i = DataBaseUtil.initSize(ATestActivity.this);
                Log.i("数据库","数据库长度：" + i);
            }
        });

//        TODO 改
        dataBase_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = dataBase_10.getText().toString();
                String s1 = dataBase_11.getText().toString();
                int affectNum = DataBaseUtil.initUpData(ATestActivity.this, s1, s);
                if (affectNum == 0) {
                    ToastUtil.showToast(ATestActivity.this,"没有找到符合条件的，没有修改");
                } else {
                    ToastUtil.showToast(ATestActivity.this,"修改成功");
                }
                int i = DataBaseUtil.initSize(ATestActivity.this);
                Log.i("数据库","数据库长度：" + i);
            }
        });

//        TODO 查
        dataBase_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = dataBase_4.getText().toString();
                list.clear();
                list = DataBaseUtil.initSelect(ATestActivity.this, s);
                if(list.size() == 0){
                    ToastUtil.showToast(ATestActivity.this,"没有找到相关数据");
                }else {
                    for (int i = 0; i < list.size(); ++i){
                        Log.i("数据库","数据-->" + list.get(i).getUserName() + "***" + list.get(i).getUserPassword());
                    }
                }
                int i = DataBaseUtil.initSize(ATestActivity.this);
                Log.i("数据库","数据库长度：" + i);
            }
        });

    }
}
