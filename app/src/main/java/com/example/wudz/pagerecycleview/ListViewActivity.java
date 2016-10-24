package com.example.wudz.pagerecycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wudz.adapter.MyListAdapter;
import com.example.wudz.recycleview.HeaderAndFooterRecyclerViewAdapter;
import com.example.wudz.recycleview.OnItemClickListener;
import com.example.wudz.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * USER：wudz on 2016/10/24 15:27
 * <p>
 * EMAIL：wudz@qianbaocard.com
 * <p>
 * 普通的listView
 */

public class ListViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_layout);
        initView();
    }

    private void initView() {
        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            list.add(i + "");
        }
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        MyListAdapter adapter = new MyListAdapter(this, list);
        HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        headerAndFooterRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(View v, int position) {
                Toast.makeText(ListViewActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
