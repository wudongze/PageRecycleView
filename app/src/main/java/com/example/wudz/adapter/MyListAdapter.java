package com.example.wudz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wudz.pagerecycleview.R;
import com.example.wudz.recycleview.HeaderAndFooterRecyclerViewAdapter;

import java.util.List;

/**
 * USER：wudz on 2016/10/24 15:31
 * <p>
 * EMAIL：wudz@qianbaocard.com
 * <p>
 * LitView的adapter
 */

public class MyListAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<String> list;
    public MyListAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
