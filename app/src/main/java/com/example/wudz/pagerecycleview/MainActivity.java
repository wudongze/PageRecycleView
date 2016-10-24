package com.example.wudz.pagerecycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                    break;
                case R.id.btn2:
                    startActivity(new Intent(MainActivity.this, PageListViewActivity.class));
                    break;
                case R.id.btn3:
                    startActivity(new Intent(MainActivity.this, GridListViewActivity.class));
                    break;
                case R.id.btn4:
                    startActivity(new Intent(MainActivity.this, StaListViewActivity.class));
                    break;
            }
        }
    };
}
