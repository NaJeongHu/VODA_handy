package com.example.voda_handy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvMypage, mIvSearch;
    private LinearLayout mLlKorean, mLlWestern, mLlJapanese, mLlChinese, mLlMidnight, mLlFastfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        mIvMypage = findViewById(R.id.iv_mypage);
        mIvSearch = findViewById(R.id.iv_search);
        mLlKorean = findViewById(R.id.ll_korean);
        mLlWestern = findViewById(R.id.ll_western);
        mLlJapanese = findViewById(R.id.ll_Japanese);
        mLlChinese = findViewById(R.id.ll_chinese);
        mLlMidnight = findViewById(R.id.ll_midnight);
        mLlFastfood = findViewById(R.id.ll_fastfood);

        mIvMypage.setOnClickListener(this);
        mIvSearch.setOnClickListener(this);
        mLlKorean.setOnClickListener(this);
        mLlWestern.setOnClickListener(this);
        mLlJapanese.setOnClickListener(this);
        mLlChinese.setOnClickListener(this);
        mLlMidnight.setOnClickListener(this);
        mLlFastfood.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_mypage:
                Intent intent = new Intent(MainActivity.this, MypageActivity.class);
                startActivity(intent);
                break;

            case R.id.iv_search:
                Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent1);
                break;

            case R.id.ll_korean:
                Intent intent2 = new Intent(MainActivity.this, StoreListActivity.class);
                intent2.putExtra("category","korean");
                startActivity(intent2);
                break;

            case R.id.ll_western:
                Intent intent3 = new Intent(MainActivity.this, StoreListActivity.class);
                intent3.putExtra("category","western");
                startActivity(intent3);
                break;

            case R.id.ll_Japanese:
                Intent intent4 = new Intent(MainActivity.this, StoreListActivity.class);
                intent4.putExtra("category","japanese");
                startActivity(intent4);
                break;

            case R.id.ll_chinese:
                Intent intent5 = new Intent(MainActivity.this, StoreListActivity.class);
                intent5.putExtra("category","chinese");
                startActivity(intent5);
                break;

            case R.id.ll_midnight:
                Intent intent6 = new Intent(MainActivity.this, StoreListActivity.class);
                intent6.putExtra("category","midnight");
                startActivity(intent6);
                break;

            case R.id.ll_fastfood:
                Intent intent7 = new Intent(MainActivity.this, StoreListActivity.class);
                intent7.putExtra("category","fastfood");
                startActivity(intent7);
                break;

            default:
                break;
        }
    }
}