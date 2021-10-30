package com.example.voda_handy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class ShoppingBasketActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvAllRemove, mTvMore, mTvTotalPrice, mTvStoreName;
    private RecyclerView mRvMenu;
    private ImageView mIvBack;
    private Button mBtnOrder;

    private ShoppingBasketAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);
        init();
    }

    private void init() {
        ShoppingList shoppingList = ShoppingList.getShoppingList();

        mTvStoreName = findViewById(R.id.tv_basket_store_name);
        mTvAllRemove = findViewById(R.id.tv_basket_remove);
        mTvMore = findViewById(R.id.tv_basket_more);
        mTvTotalPrice = findViewById(R.id.tv_basket_total_price);
        mRvMenu = findViewById(R.id.rv_basket_order_list);
        mIvBack = findViewById(R.id.iv_basket_back);
        mBtnOrder = findViewById(R.id.btn_basket_order);

        mTvStoreName.setText(shoppingList.getStoreName());
        mTvTotalPrice.setText(changeNumberFormat(shoppingList.getTotalPrice()) + "원");
        mBtnOrder.setText(changeNumberFormat(shoppingList.getTotalPrice()) + "원 주문하기");

        mTvAllRemove.setOnClickListener(this);
        mTvMore.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mBtnOrder.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRvMenu.setLayoutManager(layoutManager);
        mAdapter = new ShoppingBasketAdapter(getApplicationContext(), shoppingList.getMenus());
        mRvMenu.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_basket_remove:
                ShoppingList.getShoppingList().removeShoppingList();
//                mAdapter.notifyDataSetChanged();
                Intent intent = new Intent(ShoppingBasketActivity.this, ShoppingBasketEmptyActivity.class);
                startActivity(intent);
                finish();
                //dialog 띄우기
//                AlertDialog removeDialog = new AlertDialog.Builder(this)
//                        .setView(R.layout.dialog_shopping_basket_remove)
//                        .create();
//
//                TextView mTvNo = removeDialog.getListView().findViewById(R.id.dialog_basket_no);
//                mTvNo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        removeDialog.dismiss();
//                    }
//                });
//                TextView mTvYes = removeDialog.getListView().findViewById(R.id.dialog_basket_yes);
//                mTvYes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        removeDialog.dismiss();
//                        ShoppingList.getShoppingList().removeShoppingList();
//                        Intent intent = new Intent(ShoppingBasketActivity.this, ShoppingBasketEmptyActivity.class);
//                        startActivity(intent);
//                    }
//                });
                break;

//            case R.id.tv_basket_more:
//                Intent intent = new Intent(this, MenuActivity.class);
//                intent.putExtra("storeName", "가게이름"); //TODO: 가게이름 넣기
//                startActivity(intent);
//                finish();
//                break;

            case R.id.iv_basket_back:
                finish();
                break;

            case R.id.btn_basket_order:
                break;
        }
    }

    private String changeNumberFormat(int number) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(number);
    }
}