package com.example.voda_handy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ShoppingBasketAdapter extends RecyclerView.Adapter<ShoppingBasketAdapter.CustomViewHolder>{

    private Context mContext;
    private ArrayList<Menu> mList;

    public ShoppingBasketAdapter(Context context, ArrayList<Menu> list) {
        this.mContext = context;
        this.mList = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        private TextView menuName;
        private TextView menuPrice;
        private TextView menuNumber;
        private TextView totalPrice;
        private ImageView cancel;
        private ImageView minus;
        private ImageView plus;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            menuName = itemView.findViewById(R.id.basket_menu_name);
            menuPrice = itemView.findViewById(R.id.basket_menu_price);
            totalPrice = itemView.findViewById(R.id.basket_menu_total_price);
            menuNumber = itemView.findViewById(R.id.tv_basket_number);
            cancel = itemView.findViewById(R.id.basket_cancel);
            minus = itemView.findViewById(R.id.iv_basket_number_minus);
            plus = itemView.findViewById(R.id.iv_basket_number_plus);
        }
    }

    @NonNull
    @Override
    public ShoppingBasketAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shopping_basket, parent, false);
        ShoppingBasketAdapter.CustomViewHolder viewHolder = new ShoppingBasketAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingBasketAdapter.CustomViewHolder holder, int position) {
        Menu menu = mList.get(position);

        holder.menuName.setText(menu.getMenuname());
        holder.menuPrice.setText("가격: " + changeNumberFormat(menu.getPrice()) + "원");
        holder.menuNumber.setText(menu.getNumber() + "개");
        holder.totalPrice.setText(changeNumberFormat(menu.getPrice() * menu.getNumber()) + "원");

        holder.menuNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Integer number = Integer.parseInt(s.subSequence(0,1).toString());
                if(number == 1){
                    holder.minus.setColorFilter(R.color.gray);
                } else {
                    holder.minus.clearColorFilter();
                }

                holder.totalPrice.setText(changeNumberFormat(menu.getPrice() * number) + "원");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number = Integer.parseInt(holder.menuNumber.getText().subSequence(0,1).toString());
                if(number >= 2) {
                    holder.menuNumber.setText((number - 1) + "개");
                    menu.setNumber(number - 1);
                    ShoppingList.getShoppingList().reviseShoppingList(menu);
                }
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer number = Integer.parseInt(holder.menuNumber.getText().subSequence(0,1).toString());
                holder.menuNumber.setText((number + 1) + "개");
                menu.setNumber(number + 1);
                ShoppingList.getShoppingList().reviseShoppingList(menu);
            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
                notifyDataSetChanged();
                if(mList.isEmpty()){
                    Intent intent = new Intent(mContext, ShoppingBasketEmptyActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    //TODO: ShoppingBasketActivity 지우기
                }
//                AlertDialog alertDialog = new AlertDialog.Builder(mContext)
//                        .setView(R.layout.dialog_shopping_basket_remove)
//                        .create();
//                TextView mTvAlert = alertDialog.getListView().findViewById(R.id.dialog_basket_alert);
//                mTvAlert.setText(holder.menuName + "을 삭제하시겠습니까?");
//
//                TextView mTvNo = alertDialog.getListView().findViewById(R.id.dialog_basket_no);
//                mTvNo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alertDialog.dismiss();
//                    }
//                });
//                TextView mTvYes = alertDialog.getListView().findViewById(R.id.dialog_basket_yes);
//                mTvYes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mList.remove(position);
//                        alertDialog.dismiss();
//                    }
//                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList == null) return 0;
        else return mList.size();
    }

    private String changeNumberFormat(int number) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(number);
    }
}
