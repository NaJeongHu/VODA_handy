package com.example.voda_handy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingBasketActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvAllRemove, mTvMore, mTvTotalPrice;
    private RecyclerView mRvMenu;
    private ImageView mIvBack;
    private Button mBtnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);

        init();
    }

    private void init() {
        //TODO: firebase에서 주문정보 가져오기

        mTvAllRemove = findViewById(R.id.tv_basket_remove);
        mTvMore = findViewById(R.id.tv_basket_more);
        mTvTotalPrice = findViewById(R.id.basket_menu_total_price);
        mRvMenu = findViewById(R.id.rv_basket_order_list);
        mIvBack = findViewById(R.id.iv_basket_back);
        mBtnOrder = findViewById(R.id.btn_basket_order);

        mTvAllRemove.setOnClickListener(this);
        mTvMore.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mBtnOrder.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_basket_remove:
                //dialog 띄우기
                AlertDialog removeDialog = new AlertDialog.Builder(this)
                        .setView(R.layout.dialog_shopping_basket_remove)
                        .create();

                TextView mTvNo = findViewById(R.id.dialog_basket_no);
                mTvNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeDialog.dismiss();
                    }
                });
                TextView mTvYes = findViewById(R.id.dialog_basket_yes);
                mTvYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeAll();
                        removeDialog.dismiss();
                    }
                });
                break;

            case R.id.tv_basket_more:
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra("storeName", "가게이름"); //TODO: 가게이름 넣기
                startActivity(intent);
                finish();
                break;

            case R.id.iv_basket_back:
                finish();
                break;

            case R.id.btn_basket_order:
                break;
        }
    }

    private void removeAll() {
        //TODO: firebase에 주문정보 지우기
        //TODO: 텅빈 장바구니로 이동
    }
}