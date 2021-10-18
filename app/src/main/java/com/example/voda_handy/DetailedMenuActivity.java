package com.example.voda_handy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class DetailedMenuActivity extends AppCompatActivity {

    private Menu menu;
    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_menu);
        init();
    }

    private void init() {

        Intent intent = getIntent();
        menu = (Menu) intent.getSerializableExtra("menu");      // 지금은 정보가 별로 없지만, 더 담길 예정

    }
}