package com.xcy.fzbcity.all.myim.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xcy.fzbcity.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectView extends AppCompatActivity {

    private TextView project_view_name;
    private TextView project_view_type;
    private TextView project_view_price;
    private TextView project_view_square;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);

        initData();

    }

    private void initData() {

        project_view_name = findViewById(R.id.project_view_name);
        project_view_type = findViewById(R.id.project_view_type);
        project_view_price = findViewById(R.id.project_view_price);
        project_view_square = findViewById(R.id.project_view_square);

        Intent intent = new Intent();
        startActivity(intent);

    }
}
