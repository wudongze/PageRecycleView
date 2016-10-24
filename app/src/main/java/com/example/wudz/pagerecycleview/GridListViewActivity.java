package com.example.wudz.pagerecycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wudz.adapter.MyListAdapter;
import com.example.wudz.recycleview.EndlessRecyclerOnScrollListener;
import com.example.wudz.recycleview.HeaderAndFooterRecyclerViewAdapter;
import com.example.wudz.recycleview.HeaderSpanSizeLookup;
import com.example.wudz.recycleview.RecyclerViewStateUtils;
import com.example.wudz.widget.DividerItemDecoration;
import com.example.wudz.widget.LoadingFooter;

import java.util.ArrayList;
import java.util.List;

/**
 * USER：wudz on 2016/10/24 16:18
 * <p>
 * EMAIL：wudz@qianbaocard.com
 * <p>
 * 分页的
 */

public class GridListViewActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<String>();
    /**服务器端一共多少条数据*/
    private static final int TOTAL_COUNTER = 80;

    /**每一页展示多少条数据*/
    private static final int REQUEST_COUNT = 10;

    /**已经获取到多少条数据了*/
    private int mCurrentCounter = 0;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_listview);
        initView();
    }

    private void initView() {
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }
        mCurrentCounter = list.size();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        recyclerView = (RecyclerView) findViewById(R.id.page_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        MyListAdapter adapter = new MyListAdapter(this,list);
        headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
        GridLayoutManager manager = new GridLayoutManager(this,3);

        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) recyclerView.getAdapter(), manager.getSpanCount()));
        recyclerView.setLayoutManager(manager);
// recyclerView.setLayoutManager(new StaggeredGridLayoutManager(this,3));
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    list.clear();
                    for (int i = 0; i < 20; i++) {
                        list.add(i + "");
                    }
                    headerAndFooterRecyclerViewAdapter.notifyDataSetChanged();
                    mCurrentCounter = list.size();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 3000);

        }
    };
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(){
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(recyclerView);
            if(state == LoadingFooter.State.Loading) {
                return;
            }
            if (mCurrentCounter < TOTAL_COUNTER) {
                // loading more
                RecyclerViewStateUtils.setFooterViewState(GridListViewActivity.this, recyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            list.add(i + "---");
                        }
                        headerAndFooterRecyclerViewAdapter.notifyDataSetChanged();
                        mCurrentCounter = list.size();
                        RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Normal);
                    }
                }, 3000);
            } else {
                //the end
                RecyclerViewStateUtils.setFooterViewState(GridListViewActivity.this, recyclerView, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
            }
        }
    };
}
