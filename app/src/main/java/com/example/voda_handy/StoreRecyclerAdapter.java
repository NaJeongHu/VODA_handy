package com.example.voda_handy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StoreRecyclerAdapter extends RecyclerView.Adapter<StoreRecyclerAdapter.CustomViewHolder> {
    
    private ArrayList<StoreInfo> stores;
    private LayoutInflater mInflate;
    private Context context;

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private CardView card_item_list_titleimg;
        private TextView storename, star, waiting;
        private ImageView iv_item_list_title;

        public CustomViewHolder(View view) {
            super(view);
            this.storename = view.findViewById(R.id.tv_item_list_name);
            this.star = view.findViewById(R.id.tv_item_list_star);
            this.waiting = view.findViewById(R.id.tv_item_list_waiting);
            this.card_item_list_titleimg = view.findViewById(R.id.card_item_list_titleimg);
            this.iv_item_list_title = view.findViewById(R.id.iv_item_list_title);
        }
    }

    public StoreRecyclerAdapter(ArrayList<StoreInfo> stores, Context context) {
        this.stores = stores;
        this.mInflate = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item_store, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.storename.setText(stores.get(position).getStorename());
        holder.star.setText(String.valueOf(stores.get(position).getStar()));
        holder.waiting.setText(String.valueOf(stores.get(position).getWaiting()));
//        Glide.with(context).load(stores.get(position).getImageurl()).into(holder.iv_item_list_title);
    }

    @Override
    public int getItemCount() {
        return (null != stores ? stores.size() : 0);
    }
    
}
