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

import java.util.ArrayList;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.CustomViewHolder> {

    private ArrayList<Menu> menu;
    private LayoutInflater mInflate;
    private Context context;

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private CardView card_item_list_titleimg;
        private TextView menuname, price;
        private ImageView iv_item_list_title;

        public CustomViewHolder(View view) {
            super(view);
            this.menuname = view.findViewById(R.id.tv_item_list_name);
            this.price = view.findViewById(R.id.tv_item_list_price);
            this.card_item_list_titleimg = view.findViewById(R.id.card_item_list_titleimg);
            this.iv_item_list_title = view.findViewById(R.id.iv_item_list_title);
        }
    }

    public MenuRecyclerAdapter(ArrayList<Menu> menu, Context context) {
        this.menu = menu;
        this.mInflate = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MenuRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item_menu, parent, false);
        MenuRecyclerAdapter.CustomViewHolder viewHolder = new MenuRecyclerAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerAdapter.CustomViewHolder holder, int position) {
        holder.menuname.setText(menu.get(position).getMenuname());
        holder.price.setText(String.valueOf(menu.get(position).getPrice()));
//        Glide.with(context).load(menu.get(position).getImageurl()).into(holder.iv_item_list_title);
    }

    @Override
    public int getItemCount() {
        return (null != menu ? menu.size() : 0);
    }
}
