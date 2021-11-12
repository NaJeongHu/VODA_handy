package com.example.voda_handy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailedMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvBack, mIvMenu, mIvMinus, mIvPlus;
    private Button mBtnOrder;
    private TextView mTvMenuName, mTvMenuDetail, mTvPrice, mTvTotalPrice, mTvMenuNumber, mTvMenuOrderNumber;
    private CheckBox mCbNormal, mCbMore;

    private String storeName;
    private Menu menu;
    private int price, number;

    private FirebaseUser mFirebaseUser;     // 파이어베이스 인증
    private StorageReference mFirebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_menu);
        init();
    }

    private void init() {
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mFirebaseStorage = FirebaseStorage.getInstance().getReference();

        storeName = getIntent().getStringExtra("store");
        menu = (Menu) getIntent().getSerializableExtra("menu");
        price = menu.getPrice(); // 처음 가격 가져오기
        number = 1;

        mIvBack = findViewById(R.id.iv_detail_back);
        mIvMenu = findViewById(R.id.iv_detail_menu);
        mBtnOrder = findViewById(R.id.btn_detail_order);
        mTvMenuName = findViewById(R.id.tv_detail_menu_name);
        mTvMenuNumber = findViewById(R.id.tv_detail_number);
        mTvMenuOrderNumber = findViewById(R.id.tv_detail_order_number);
        mTvMenuDetail = findViewById(R.id.tv_detail_menu_detail);
//        mTvPrice = findViewById(R.id.tv_detail_price);
        mTvTotalPrice = findViewById(R.id.tv_detail_total_price);
        mIvPlus = findViewById(R.id.iv_detail_plus);
        mIvMinus = findViewById(R.id.iv_detail_minus);
        mIvMinus.setColorFilter(R.color.gray);
        mCbNormal = findViewById(R.id.cb_detail_normal);
        mCbMore = findViewById(R.id.cb_detail_more);

        mTvMenuName.setText(menu.getMenuname());
        mTvMenuDetail.setText(menu.getExplanation());
//        mTvPrice.setText(changeNumberFormat(price) + "원");
        mTvTotalPrice.setText(changeNumberFormat(price) + "원");
        mIvMenu.setImageURI(Uri.parse(menu.getImageurl()));
        Log.d("Debug", "imageURL: " + menu.getImageurl());
        //TODO: Image 가져오기
//        mFirebaseStorage.child("").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                mIvMenu.setImageURI(uri);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.e("Debug", "Fail to download image url");
//            }
//        });

        mIvBack.setOnClickListener(this);
        mBtnOrder.setOnClickListener(this);
        mIvMinus.setOnClickListener(this);
        mIvPlus.setOnClickListener(this);
        mCbNormal.setOnClickListener(this);
        mCbMore.setOnClickListener(this);

        mTvMenuNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strNumber = s.toString();
                if(strNumber.equals("1")){
                    mIvMinus.setColorFilter(R.color.gray);
                } else {
                    mIvMinus.clearColorFilter();
                }
                mTvMenuOrderNumber.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_detail_back:
                finish();
                break;

            case R.id.btn_detail_order:
                ShoppingList shoppingList = ShoppingList.getShoppingList();
                shoppingList.setStoreName(storeName);
                shoppingList.addShoppingList(new Menu(menu.getMenuname(), price, number));
                finish();
                break;

            case R.id.iv_detail_plus:
                number = number + 1;
                mTvMenuNumber.setText(number + "");
                mTvMenuOrderNumber.setText(number + "");
                mTvTotalPrice.setText(changeNumberFormat(number * price) + "원");
                break;

            case R.id.iv_detail_minus:
                if(number >= 2) {
                    number = number - 1;
                    mTvMenuNumber.setText(number + "");
                    mTvMenuOrderNumber.setText(number + "");
                    mTvTotalPrice.setText(changeNumberFormat(number * price) + "원");
                }
                break;

            case R.id.cb_detail_normal:
                if(mCbMore.isChecked()) {
                    mCbMore.setChecked(false);
                    mCbNormal.setChecked(true);
                }
                break;

            case R.id.cb_detail_more:
                if(mCbNormal.isChecked()) {
                    mCbNormal.setChecked(false);
                    mCbMore.setChecked(true);
                }
                break;
        }
    }

    private String changeNumberFormat(int number) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(number);
    }
}