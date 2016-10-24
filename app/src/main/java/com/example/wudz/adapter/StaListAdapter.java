package com.example.wudz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wudz.pagerecycleview.R;

import java.util.List;

/**
 * USER：wudz on 2016/10/24 15:31
 * <p>
 * EMAIL：wudz@qianbaocard.com
 * <p>
 * LitView的adapter
 */

public class StaListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;
    private int largeCardHeight, smallCardHeight;

    public StaListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        largeCardHeight = (int) context.getResources().getDisplayMetrics().density * 300;
        smallCardHeight = (int) context.getResources().getDisplayMetrics().density * 200;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sta_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textView.setText(list.get(position));
        if(position % 3 == 0){
            ((ViewHolder) holder).textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }else if(position % 3 == 1){
            ((ViewHolder) holder).textView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }else if(position % 3 == 2){
            ((ViewHolder) holder).textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent1));
        }
        //修改高度，模拟交错效果
        ((ViewHolder) holder).textView.getLayoutParams().height = position % 2 != 0 ? largeCardHeight : smallCardHeight;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
