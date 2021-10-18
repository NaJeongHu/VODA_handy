package com.example.voda_handy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailedMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvBack, mIvMinus, mIvPlus;
    private Button mBtnOrder;
    private TextView mTvMenuName, mTvMenuDetail, mTvTotalPrice, mTvMenuNumber;

    private int price, number;

    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_menu);
        init();
    }

    private void init() {
        mIvBack = findViewById(R.id.iv_detail_back);
        mBtnOrder = findViewById(R.id.btn_detail_order);
        mTvMenuName = findViewById(R.id.tv_detail_menu_name);
        mTvMenuNumber = findViewById(R.id.tv_detail_number2);
        mTvMenuDetail = findViewById(R.id.tv_detail_menu_detail);
        mTvTotalPrice = findViewById(R.id.tv_detail_total_price);
        mIvPlus = findViewById(R.id.iv_detail_number_plus);
        mIvMinus = findViewById(R.id.iv_detail_number_minus);
        mIvMinus.setColorFilter(R.color.gray);

        mIvBack.setOnClickListener(this);
        mBtnOrder.setOnClickListener(this);
        mIvMinus.setOnClickListener(this);
        mIvPlus.setOnClickListener(this);

        mTvMenuNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strNumber = s.toString();
                if(strNumber.equals("1개")){
                    mIvMinus.setColorFilter(R.color.gray);
                } else {
                    mIvMinus.clearColorFilter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //TODO: firebase에서 가격, 추가조건 가져오기
        price = 23000; // 처음 가격 가져오기
        number = 1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_detail_back:
                finish();
                break;

            case R.id.btn_detail_order:
                //TODO: firebase에 update
                finish();
                break;

            case R.id.iv_detail_number_plus:
                Log.d("Debug", "plus...");
                number = number + 1;
                mTvMenuNumber.setText(number + "개");
                mBtnOrder.setText(number + "개 담기");
                mTvTotalPrice.setText(changeNumberFormat(number * price) + "원");
                break;

            case R.id.iv_detail_number_minus:
                Log.d("Debug", "minus...");
                if(number >= 2) {
                    number = number - 1;
                    mTvMenuNumber.setText(number + "개");
                    mBtnOrder.setText(number + "개 담기");
                    mTvTotalPrice.setText(changeNumberFormat(number * price) + "원");
                }
                break;
        }
    }

    private String changeNumberFormat(int number) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(number);
    }
}